package servlet;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import action.Action;
import bean.Customer2;
/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns=("*.action"))
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// セッションタイムアウトの判定
			HttpSession session = request.getSession(false);
			if(session == null || session.isNew()) {
				response.sendRedirect("session-error.jsp");
				return;
			}
			
			// セッション情報Customerを読み取る			
			Customer2 customer = (Customer2)session.getAttribute("customer");
			
			// Login.action -> LoginActionに変換
			String path = request.getServletPath().substring(1);
			String name = "action." + path.replace(".action", "Action").replace('/', '.');
			
			// Admin用Actionリスト
			List<String> loginActions = Arrays.asList("action.LoginAction", "action.LoginAgainAction");
			
			// ログイン中か否か
			if(customer == null && !loginActions.contains(name)) {
				response.sendRedirect("invalid-access.jsp");
				return;
			}
			
			// AccountList Regist RegistFormは管理者のみアクセス出来るようにする
			
			// Admin用Actionリスト
			List<String> adminActions = Arrays.asList("action.AccountListAction", "action.RegistAction", "action.RegistFormAction");
			
			// 「.action」で終わり、かつ除外リストに含まれていない場合の処理
			if(path.endsWith(".action") && !adminActions.contains(name)) {
				
				// アクションインスタンス生成
				Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();
				
				// アクションインスタンス実行
				String url = action.execute(request, response);
				
				// viewへフォワード
				request.getRequestDispatcher(url).forward(request, response);
				
				return;
			
			// 管理者で、かつ管理者用Actionにアクセスした場合の処理
			} else if("ADMIN".equals(customer.getRole()) && adminActions.contains(name)) {
				
				// アクションインスタンス生成
				Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();
				
				// アクションインスタンス実行
				String url = action.execute(request, response);
				
				// viewへフォワード
				request.getRequestDispatcher(url).forward(request, response);
				
				return;
				
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				
				return;
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
