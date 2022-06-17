package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageFunnies;
import models.Funny;
import models.User;

















/// TODO --> get your own and your friends (followed) funnies and display (get all so that they are ordered by time)















/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetFollowedFunnies")
public class GetFollowedFunnies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFollowedFunnies() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<Funny> funnies = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			ManageFunnies funnyManager = new ManageFunnies();
			funnies = funnyManager.getUserFunnies(user.getId(),0,4);
			funnyManager.finalize();
		}

		request.setAttribute("funnies",funnies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewFunnies.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

