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
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/EditFunnyController")
public class EditFunnyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFunnyController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Funny funny = new Funny();
		System.out.println("EditFunnyController.java");
		ManageFunnies funnyManager = new ManageFunnies();
		
		try {
			BeanUtils.populate(funny, request.getParameterMap());
			System.out.println("Replacing the content of funny with id: "+ funny.getId() + " to content: " + funny.getContent());
			funnyManager.updateFunny(funny.getId(), funny.getContent());
			funnyManager.finalize();
			
//			tweetManager.updateTweet(tweet.getId(),tweet.getContent());
//			tweetManager.finalize();
//	   
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOwnTimeline.jsp"); 
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
