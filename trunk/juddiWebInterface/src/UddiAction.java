

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.uddi.client.UddiDataSource;
import org.simpl.uddi.client.UddiDataWriter;

/**
 * Servlet implementation class UddiAction
 */
public class UddiAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UddiAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String delete = request.getParameter("delete");
		String edit = request.getParameter("edit");
		String newsource = request.getParameter("new");
		String save = request.getParameter("save");
		UddiDataWriter dataWriter = UddiDataWriter.getInstance();
		
		if (delete != null) {
			dataWriter.deleteDatasource(request.getParameter("uddi"));
			response.sendRedirect("index.jsp");
		} else if (edit != null || newsource != null) {
			String nextJSP = "/form.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);

		} else if (save != null) {
			if (FormValidator.validateForm(request)) {
				UddiDataSource dataSource = new UddiDataSource("uddi:juddi.apache.org:simpl");
				dataSource.setName(request.getParameter("name"));
				dataSource.setAddress(request.getParameter("address"));
				dataSource.addAttribute("type", request.getParameter("type"), "uddi:juddi.apache.org:type");
				dataSource.addAttribute("subtype", request.getParameter("subtype"), "uddi:juddi.apache.org:subtype");
				dataSource.addAttribute("wspolicy", request.getParameter("policy"), "uddi:juddi.apache.org:wspolicy");
				dataSource.setKey(request.getParameter("key"));
				dataWriter.writeDatasource(dataSource);
				request.setAttribute("Message", "Datenquelle erfolgreich hinzugef�gt");
				String nextJSP = "/index.jsp?message="+"Datasource Added";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				
				//TODO gegebenfalls �ndern
			} else {
				String nextJSP = "/form.jsp?message="+"All fields need content";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				
			}
		}
		else {
			out.println("Unbekannte Aktion");
		}

		
		
		
	}

}
