import javax.servlet.http.HttpServletRequest;


public class FormValidator {
	public static String validateForm(HttpServletRequest request) {
		String message = "";
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		String subtype = request.getParameter("subtype");
		String policy = request.getParameter("policy");
		String key = request.getParameter("key");

		if (name.equals("")) {
			message = message + "Field Name must not be empty <br>";
		}
		
		if (address.equals("")) {
			message = message + "Field Address must not be empty <br>";
		}
		
		if (type.equals("")) {
			message = message + "Field Type must not be empty <br>";
		}
		
		if (subtype.equals("")) {
			message = message + "Field subtype must not be empty <br>";
		}
		
		if (policy.equals("")) {
			message = message + "Field Policy must not be empty <br>";
		}
		
		if (key.equals("")) {
			message = message + "Field Key must not be empty";
		}
		return message;
	}
}
