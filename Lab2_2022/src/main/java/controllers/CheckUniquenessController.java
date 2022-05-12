package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ManageUsers;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/CheckUniquenessController")
public class CheckUniquenessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUniquenessController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String columnName = request.getParameter("columnName");
		String columnValue = request.getParameter("columnValue");
		
		String regex = "^[a-zA-Z0-9_!#$%@&*+/=?{|}~^.-]+$";
		Pattern pattern = Pattern.compile(regex);
		
		response.setContentType("text");
		PrintWriter writer = response.getWriter();
		
		if(columnName == null || columnValue == null ||	!pattern.matcher(columnName).matches() || !pattern.matcher(columnValue).matches()) {
			writer.append("unknown");
			return;
		}
		
		ManageUsers manager = new ManageUsers();
		Boolean unique = manager.requestUniqueness(columnName, columnValue);
		
		if(unique) {
			writer.append("unique");
		} else {
			writer.append("not-unique");			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
