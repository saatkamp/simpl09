import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UddiAction
 */
public class UddiAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String TMP_DIR_PATH = "webapps\\juddiweb\\WEB-INF\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "webapps\\juddiweb\\WEB-INF\\files";
	private File destinationDir;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UddiAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);

		if (!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		// String realPath =
		// getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(DESTINATION_DIR_PATH);
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String delete = request.getParameter("delete");
		String edit = request.getParameter("edit");
		String newsource = request.getParameter("new");
		String save = request.getParameter("save");
		String saveConfig = request.getParameter("saveconfig");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		out.println(Boolean.toString(isMultipart));

		UddiWebConfig config = UddiWebConfig.getInstance();
		UddiDataWriter dataWriter = null;

		out.println("save: " + save);
		out.println("save: " + request.getParameter("save"));

		out.println(tmpDir.getAbsolutePath());

		if (isMultipart) {
			// String message = FormValidator.validateForm(request);
			String message = "";
			if (message.equals("")) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				FileItem fileItem = null;
				List items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				Iterator itr = items.iterator();
				HashMap<String, String> fields = new HashMap<String, String>();
				ArrayList<String> rows = null;
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						fields.put(item.getFieldName(), item.getString());										

					} else {
						fileItem = item;

						if (fileItem != null) {

							if (fileItem.getSize() > 0) {

								BufferedReader in = new BufferedReader(
										new InputStreamReader(fileItem
												.getInputStream()));

								rows = new ArrayList<String>();
								while (in.ready()) {
									rows.add(in.readLine());
								}
							}
						}
					}
				}
				if (message.equals("")) {

					UddiDataSource dataSource = new UddiDataSource(
							"uddi:juddi.apache.org:simpl",
							fields.get("key"));
					dataWriter = UddiDataWriter.getInstance(
							config.getAddress(), config
									.getUsername(), config
									.getPassword());
					dataSource.setName(fields.get("name"));
					dataSource.setAddress(fields.get("address"));
					dataSource.addAttribute("type", fields.get("type"),
							"uddi:juddi.apache.org:type");
					dataSource.addAttribute("subtype", fields.get("subtype"),
							"uddi:juddi.apache.org:subtype");
					// dataSource.addAttribute("wspolicy",
					// request.getParameter("policy"),
					// "uddi:juddi.apache.org:wspolicy");
					dataSource.addAttribute("username", fields.get("username"),
							"uddi:juddi.apache.org:username");
					dataSource.addAttribute("password", fields.get("password"),
							"uddi:juddi.apache.org:password");
					dataSource.addAttribute("language", fields.get("language"),
							"uddi:juddi.apache.org:language");
					dataSource.addAttribute("dataformat",
							fields.get("dataformat"),
							"uddi:juddi.apache.org:dataformat");
					
					if (!rows.equals(null)) {
						dataSource.addAttribute("wspolicysize", Integer.toString(rows.size()), "uddi:juddi.apache.org:policysize");
						for (int i = 0; i < rows.size(); i++) {
							dataSource.addAttribute("wspolicy"+i, rows.get(i), "uddi:juddi.apache.org:wspolicy");
						}
					}
					
					
					if (dataWriter.writeDatasource(dataSource) == true) {
						 String nextJSP =
						 "/list.jsp?message="+"New datasource added successfully";
						

						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher(nextJSP);
						dispatcher.forward(request, response);
					} else {
						String nextJSP = "/list.jsp?message="
								+ "Error: The UDDI registry is not available";
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher(nextJSP);
						dispatcher.forward(request, response);
					}

				} else {
					String nextJSP = "/form.jsp?message="
							+ message;
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(nextJSP);
					dispatcher.forward(request, response);

				}

			}

		} else if (delete != null) {
			if (request.getParameter("uddi") != null) {
				dataWriter = UddiDataWriter.getInstance(config.getAddress(),
						config.getUsername(), config.getPassword());
				dataWriter.deleteDatasource(request.getParameter("uddi"));
				response
						.sendRedirect("list.jsp?message=Datasource deleted successfully");
			} else {
				response
						.sendRedirect("list.jsp?message=No datasource selected");
			}

		} else if (edit != null) {
			String nextJSP = "";
			if (request.getParameter("uddi") == null) {
				nextJSP = "/list.jsp";
			} else {
				nextJSP = "/form.jsp";
			}

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} else if (newsource != null) {
			String nextJSP = null;
			if (UddiDataWriter.getInstance(config.getAddress(), config
					.getUsername(), config.getPassword()) != null) {
				nextJSP = "/form.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			} else {
				nextJSP = "/form.jsp?message="
						+ "The given UDDI address is not valid";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			}

		} else if (saveConfig != null) {

			config.setAddress(request.getParameter("address"));

			config.setUsername(request.getParameter("username"));

			config.setPassword(request.getParameter("password"));

			config.writeConfig();

			Transport transport = new JAXWSTransport("default");

			try {
				UDDISecurityPortType security = transport
						.getUDDISecurityService(config.getAddress()
								+ "/services/security?wsdl");
				if (!UddiBusinessReader.getInstance(config.getAddress())
						.simplBusinessExsists()) {
					CreateDatabase.create();
				}
				String nextJSP = "/index.jsp?message=" + "Configuration saved";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);

			} catch (TransportException e) {
				String nextJSP = "/index.jsp?message="
						+ "UDDI registry not found";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			}

		} else {
			out.println("Not a valid action");
		}
	}
}
