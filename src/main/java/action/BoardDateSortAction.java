package action;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardDateSortAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boardPage = request.getParameter("boardPage");
		ServletContext application = request.getServletContext();
		List<Map<String, Object>> boardList = (List<Map<String, Object>>)application.getAttribute("board");
		
		if(boardList != null) {
			List<Map<String, Object>> sortedList = new ArrayList<>(boardList);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日 H時m分s秒");
			String sortOrderString = request.getParameter("sort");
			
			if("asc".equals(sortOrderString)) {
				sortedList.sort(Comparator.comparing(map -> 
	            LocalDateTime.parse((String) map.get("myDate"), formatter)));
			} else {
				sortedList.sort(Comparator.comparing((Map<String, Object> map) -> 
	            LocalDateTime.parse((String) map.get("myDate"), formatter)).reversed());
			}
			
			request.setAttribute("board", sortedList);
		}
		
		if("All".equals(boardPage)) {
			return "/WEB-INF/board-out.jsp";
			
		} else {
			return "/WEB-INF/board-out-loginId.jsp";
			
		}
	}
}
