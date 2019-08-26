package kurs.carrental.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kurs.carrental.commands.AddBookingCommand;
import kurs.carrental.commands.Command;
import kurs.carrental.commands.GetBookingsCommand;
import kurs.carrental.commands.InitBookingCommand;
import kurs.carrental.commands.SearchBookingsCommand;

/**
 * Servlet implementation class PageController
 */
@WebServlet("/PageController")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Command> commands = new HashMap<String, Command>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageController() {
		super();
		// insert commands into hash map
		commands.put("init", new InitBookingCommand("initBooking.jsp"));
		commands.put("addBooking", new AddBookingCommand("addBooking.jsp"));
		commands.put("searchBooking", new SearchBookingsCommand("searchBookings.jsp"));
		commands.put("getBookings", new GetBookingsCommand("getBookings.jsp"));
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// get context parameters
			String dbUrl = getServletContext().getInitParameter("DbUrl");
			String dbUser = getServletContext().getInitParameter("DbUser");
			String dbPswd = getServletContext().getInitParameter("DbPswd");
			// get 'cmd' parameter from the request
			String cmd = request.getParameter("cmd");			
			// get relevant command
			Command comm = (cmd != null) ? commands.get(cmd) : new InitBookingCommand("initBooking.jsp");
			// set DB params
			comm.setDbUrl(dbUrl);
			comm.setDbUser(dbUser);
			comm.setDbPswd(dbPswd);
			// set the request
			comm.setRequest(request);
			// execute the command and get the JSP page for handling the request
			String nextPage = comm.execute();
			// forward the request
			request.getRequestDispatcher(nextPage).forward(request, response);
		} catch (Exception e) {
			// serialize exception's stack trace into a string
			StringWriter sw = new StringWriter();
		    e.printStackTrace(new PrintWriter(sw));
		    // fill the stack trace in the request
			request.setAttribute("error", sw.toString());
			// forward the exception to the error page
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
