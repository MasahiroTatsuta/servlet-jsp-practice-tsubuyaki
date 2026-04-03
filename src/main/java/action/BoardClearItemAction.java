package action;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardClearItemAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String boardPage = request.getParameter("boardPage");
		String loginId = request.getParameter("loginId");
		
		if (id != null) {
		    BoardDAO dao = new BoardDAO();
		    dao.deleteBoardItem(id);
		    
		    if("All".equals(boardPage)) {
		    	List<Map<String, String>> contentsList = dao.viewContents();		
				ServletContext application = request.getServletContext();
				application.setAttribute("board", contentsList);
				
				return "/WEB-INF/board-out.jsp";
				
		    } else {
		    	List<Map<String, String>> contentsList = dao.viewContents(loginId);		
				ServletContext application = request.getServletContext();
				application.setAttribute("board", contentsList);
				
				return "/WEB-INF/board-out-loginId.jsp";
				
		    }

		}

		return "/WEB-INF/board-out.jsp";

	}

}
