package action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginAgainAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/index.jsp";
	}

}
