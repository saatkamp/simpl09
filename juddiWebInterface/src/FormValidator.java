import javax.servlet.http.HttpServletRequest;


public class FormValidator {
	public static boolean validateForm(HttpServletRequest request) {
		boolean valid = true;
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		String subtype = request.getParameter("subtype");
		String policy = request.getParameter("policy");
		String key = request.getParameter("key");

		if (name.equals("") || address.equals("") || type.equals("") || subtype.equals("") || policy.equals("") || key.equals("")) {
			valid = false;
		}
		return valid;
	}
}
