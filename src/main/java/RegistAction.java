
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Customer2;
import dao.CustomerDAO;

public class RegistAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Customer2 customer = (Customer2)session.getAttribute("customer");
		
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		CustomerDAO dao = new CustomerDAO();
		customer = dao.search(loginId);
		
		if(customer == null) {
			dao.register(loginId, password, role);
			return "/WEB-INF/regist-out.jsp";
		}
		
		return "/WEB-INF/regist-error.jsp";
	}

}
