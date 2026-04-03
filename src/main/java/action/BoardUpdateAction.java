package action;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Customer2;
import dao.BoardDAO;

public class BoardUpdateAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String contents = request.getParameter("contents");
		
		HttpSession session = request.getSession();
		Customer2 customer = (Customer2)session.getAttribute("customer");
		String loginId = customer.getLoginId();
		
		if(contents.length() <= 50) {
			BoardDAO dao = new BoardDAO();
			dao.insertContents(loginId, contents);
			List<Map<String, String>> contentsList = dao.viewContents();		
			ServletContext application = request.getServletContext();
			application.setAttribute("board", contentsList);
			
			return "/WEB-INF/board-out.jsp";

		} else {
			
			return "/WEB-INF/board-error.jsp";
		}
		
		
		
	}

}
