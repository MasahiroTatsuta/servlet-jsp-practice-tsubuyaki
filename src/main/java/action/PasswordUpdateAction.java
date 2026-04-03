package action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Customer2;
import dao.CustomerDAO;

public class PasswordUpdateAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Customer2 customer = (Customer2)session.getAttribute("customer");
		
		String loginId = customer.getLoginId();
		String password = request.getParameter("password");
		CustomerDAO dao = new CustomerDAO();
		dao.passwordUpdate(loginId, password);
		
		return "/WEB-INF/password-out.jsp";
		
	}

}
