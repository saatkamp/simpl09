

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.simpl.uddi.CreateDatabase;
import org.simpl.uddi.UddiWebConfig;
import org.simpl.uddi.client.UddiBusinessReader;
import org.simpl.uddi.client.UddiDataSource;
import org.simpl.uddi.client.UddiDataWriter;
import org.uddi.v3_service.UDDISecurityPortType;

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
		String saveConfig = request.getParameter("saveconfig");
		
		UddiWebConfig config = UddiWebConfig.getInstance();
		UddiDataWriter dataWriter = null;
		
		if (delete != null) {
			if (request.getParameter("uddi") != null) {
				dataWriter = UddiDataWriter.getInstance(config.getAddress(), config.getUsername(), config.getPassword());
				dataWriter.deleteDatasource(request.getParameter("uddi"));
				response.sendRedirect("list.jsp?message=Datasource deleted successfully");
			} else {
				response.sendRedirect("list.jsp?message=No datasource selected");
			}
			
		} else if (edit != null) {
			String nextJSP = "";
			if (request.getParameter("uddi") == null) {
				nextJSP = "/list.jsp";
			} else {
				nextJSP = "/form.jsp";
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else if (newsource != null) {
			String nextJSP = null;
			if (UddiDataWriter.getInstance(config.getAddress(), config.getUsername(), config.getPassword()) != null) {
				nextJSP = "/form.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
			} else {
				nextJSP = "/form.jsp?message="+"The given UDDI address is not valid";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
			}
			

		} else if (save != null) {
			String message = FormValidator.validateForm(request);
			if (message.equals("")) {
				
				UddiDataSource dataSource = new UddiDataSource("uddi:juddi.apache.org:simpl", request.getParameter("key"));
				dataWriter = UddiDataWriter.getInstance(config.getAddress(), config.getUsername(), config.getPassword());
				dataSource.setName(request.getParameter("name"));
				dataSource.setAddress(request.getParameter("address"));
				dataSource.addAttribute("type", request.getParameter("type"), "uddi:juddi.apache.org:type");
				dataSource.addAttribute("subtype", request.getParameter("subtype"), "uddi:juddi.apache.org:subtype");
				dataSource.addAttribute("wspolicy", request.getParameter("policy"), "uddi:juddi.apache.org:wspolicy");
				dataSource.addAttribute("username", request.getParameter("username"), "uddi:juddi.apache.org:username");
				dataSource.addAttribute("password", request.getParameter("password"), "uddi:juddi.apache.org:password");
				dataSource.addAttribute("language", request.getParameter("language"), "uddi:juddi.apache.org:language");
				dataSource.addAttribute("dataformat", request.getParameter("dataformat"), "uddi:juddi.apache.org:dataformat");
				if (dataWriter.writeDatasource(dataSource) == true) {
					String nextJSP = "/list.jsp?message="+"New datasource added successfully";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
					dispatcher.forward(request,response);
				} else {
					String nextJSP = "/list.jsp?message="+"Error: The UDDI registry is not available";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
					dispatcher.forward(request,response);
				}
				
			} else {
				String nextJSP = "/form.jsp?message="+message;
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				
			}
		} else if (saveConfig != null) {
						
			config.setAddress(request.getParameter("address"));
			
			config.setUsername(request.getParameter("username"));
			
			config.setPassword(request.getParameter("password"));
			
			config.writeConfig();
			
			Transport transport = new JAXWSTransport("default");
			
			try {
				UDDISecurityPortType security = transport.getUDDISecurityService(config.getAddress() + "/services/security?wsdl");
				if (!UddiBusinessReader.getInstance(config.getAddress()).simplBusinessExsists()) {
					CreateDatabase.create();
				}
				String nextJSP = "/index.jsp?message="+"Configuration saved";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				
			} catch (TransportException e) {
				String nextJSP = "/index.jsp?message="+"UDDI registry not found";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
			}
			
			
		} else {
			out.println("Not a valid action");
		}

		
		
		
	}

}
