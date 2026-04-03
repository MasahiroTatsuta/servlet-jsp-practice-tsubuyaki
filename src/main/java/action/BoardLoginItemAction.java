package action;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardLoginItemAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginId = request.getParameter("loginId");
		BoardDAO dao = new BoardDAO();
		List<Map<String, String>> contentsList = dao.viewContents(loginId);		
		ServletContext application = request.getServletContext();
		application.setAttribute("board", contentsList);
				
		return "/WEB-INF/board-out-loginId.jsp";
	}

}