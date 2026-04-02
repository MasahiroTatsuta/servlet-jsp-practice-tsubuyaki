import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("customer") != null) {
			session.removeAttribute("customer");
			return "/WEB-INF/logout-out.jsp";
		}
		return "/WEB-INF/logout-error.jsp";
	}

}
