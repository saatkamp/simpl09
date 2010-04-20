

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		out.println(delete);
		UddiDataWriter dataWriter = UddiDataWriter.getInstance();
		
		if (delete != null) {
			dataWriter.deleteDatasource(request.getParameter("uddi"));
			response.sendRedirect("index.jsp");
		} else if (edit != null) {
			out.println("Edit");
		} else {
			out.println("fuck you");
		}

		
		
		
	}

}
