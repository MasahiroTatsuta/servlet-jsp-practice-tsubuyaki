package action;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardClearAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginId = request.getParameter("loginId");
		String boardPage = request.getParameter("boardPage");
		String contentsLoginId = request.getParameter("contentsLoginId");
		
		if (loginId != null) {
		    BoardDAO dao = new BoardDAO();
		    // 削除実行
		    dao.deleteBoard(loginId);
		    
		    if("All".equals(boardPage)) {
		    	List<Map<String, String>> contentsList = dao.viewContents();		
				ServletContext application = request.getServletContext();
				application.setAttribute("board", contentsList);
				
				return "/WEB-INF/board-out.jsp";
				
		    } else {
		    	List<Map<String, String>> contentsList = dao.viewContents(contentsLoginId);		
				ServletContext application = request.getServletContext();
				application.setAttribute("board", contentsList);
				
				return "/WEB-INF/board-out-loginId.jsp";
				
		    }

		}

		return "/WEB-INF/board-out.jsp";

	}

}


