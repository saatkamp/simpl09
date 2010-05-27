import javax.servlet.http.HttpServletRequest;


public class FormValidator {
	public static String validateForm(HttpServletRequest request) {
		String message = "";
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		String subtype = request.getParameter("subtype");
		String language = request.getParameter("language");
		
		if (name.equals("")) {
			message = message + "Field name must not be empty <br>";
		}
		
		if (address.equals("")) {
			message = message + "Field address must not be empty <br>";
		}
		
		if (type.equals("")) {
			message = message + "Field type must not be empty <br>";
		}
		
		if (subtype.equals("")) {
			message = message + "Field subtype must not be empty <br>";
		}
		
		if (language.equals("")) {
			message = message + "Field language must not be empty <br>";
		}
		
		return message;
	}
}
