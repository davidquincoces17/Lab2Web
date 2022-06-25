package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
		List<Integer> funs = new ArrayList<Integer>();
		List<Integer> unfuns = new ArrayList<Integer>();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			ManageFunnies funnyManager = new ManageFunnies();
			funnies = funnyManager.getFollowedFunnies(user.getId(),0,4);
			for (Funny f: funnies) {
				funs.add(funnyManager.getLikes(f.getId()));
				unfuns.add(funnyManager.getDislikes(f.getId()));
			}
			funnyManager.finalize();
		}
		

		request.setAttribute("user",user);
		request.setAttribute("funnies",funnies);
		request.setAttribute("funs",funs);
		request.setAttribute("unfuns",unfuns);
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

