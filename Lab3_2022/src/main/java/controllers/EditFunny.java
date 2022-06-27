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

import managers.ManageFunnies;
import managers.ManageUsers;
import models.Funny;
import models.User;

/**
 * Servlet implementation class LikeFunny
 */
@WebServlet("/EditFunny")
public class EditFunny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFunny() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		Funny funny = new Funny();
		ManageFunnies funnyManager = new ManageFunnies();
		ManageUsers userManager = new ManageUsers();
		
		try {
			
			if (session != null || user != null)
				BeanUtils.populate(funny, request.getParameterMap());
				funny = funnyManager.getFunny(funny.getId());
				user = userManager.getUser(user.getId());
				if(user.getId() == funny.getAuthorId()) {
					System.out.println("You can edit this tweet!");
				}else {
					System.out.println("You cannot edit this tweet because its not yours!");
				}
				funnyManager.finalize();
				userManager.finalize();


		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		request.setAttribute("funny", funny);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewEditTweet.jsp"); 
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}