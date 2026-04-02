import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CustomerDAO;

public class AccountListAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, String>> loginIdList = new ArrayList<>();
		CustomerDAO dao = new CustomerDAO();
		loginIdList = dao.viewAccounts();
		
		loginIdList.forEach(map -> {
		    map.replaceAll((key, value) -> {
		        // "role"カラムだけを日本語に変換
		        if ("role".equals(key)) {
		            return switch (value) {
		                case "ADMIN"   -> "管理者";
		                case "GENERAL" -> "一般ユーザー";
		                default        -> value; // それ以外（null等）はそのまま
		            };
		        }
		        // role以外のloginIdなどはそのまま返す
		        return value;
		    });
		});
		
		request.setAttribute("accounts", loginIdList);
		
		return "/WEB-INF/accountlist-out.jsp";
	}

}
