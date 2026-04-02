import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardDisLikeSortAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boardPage = request.getParameter("boardPage");
		ServletContext application = request.getServletContext();
		List<Map<String, Object>> boardList = (List<Map<String, Object>>)application.getAttribute("board");
		
		if(boardList != null) {
			List<Map<String, Object>> sortedList = new ArrayList<>(boardList);
			String sortOrderString = request.getParameter("sort");
			
			if ("asc".equals(sortOrderString)) {
		        // 昇順（少ない順）
				sortedList.sort(Comparator.comparingInt(map -> 
	            Integer.parseInt(String.valueOf(map.get("disLikes")))));
		    } else {
		        // 降順（多い順）: デフォルト
		    	sortedList.sort(Comparator.comparingInt((Map<String, Object> map) -> 
	            Integer.parseInt(String.valueOf(map.get("disLikes")))).reversed());
			
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
