package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.tuple.Pair;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("LoginController: ");
		
		User user = new User();
		ManageUsers manager = new ManageUsers();
		Pair<Boolean,User> pair = null;
		
	    try {
			
	    	BeanUtils.populate(user, request.getParameterMap());
			
	    	if (manager.isLoginComplete(user) && manager.canLogin(user).getLeft()) {
	    		pair = manager.canLogin(user);
	    		
	    		if (pair.getLeft()) {
	    			System.out.println("login OK, forwarding to ViewLoginDone ");
			    	HttpSession session = request.getSession();
			    	session.setAttribute("user", pair.getRight());
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
				    dispatcher.forward(request, response);
	    		}
	    		
		    } 
			else {
		     
				System.out.println("user is not logged, forwarding to ViewLoginForm ");
			    request.setAttribute("user", user);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			    dispatcher.forward(request, response);
		    	
		    }
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

