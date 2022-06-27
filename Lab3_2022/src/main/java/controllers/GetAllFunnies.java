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
import managers.ManageFunnies;
import managers.ManageUsers;
import models.Funny;

@WebServlet("/GetAllFunnies")
public class GetAllFunnies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFunnies() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Funny> funnies = Collections.emptyList();
		List<Integer> funs = new ArrayList<Integer>();
		List<Integer> unfuns = new ArrayList<Integer>();
		List<String> imgStateFun = new ArrayList<String>();
		List<String> imgStateUnfun = new ArrayList<String>();
		boolean isAdmin = false;
		
		ManageUsers userManager = new ManageUsers();
		
		ManageFunnies funnyManager = new ManageFunnies();
		funnies = funnyManager.getFunnySearch("%%",0,20);
		
		Integer value = 0;
		for (Funny f: funnies) {
			funs.add(funnyManager.getLikes(f.getId()));
			unfuns.add(funnyManager.getDislikes(f.getId()));
			
			if(value == 0) {
				imgStateFun.add("imgs/fun0.png");
				imgStateUnfun.add("imgs/unfun1.png");
			} else if(value == 1) {
				imgStateFun.add("imgs/fun1.png");
				imgStateUnfun.add("imgs/unfun0.png");
			} else {
				imgStateFun.add("imgs/fun0.png");
				imgStateUnfun.add("imgs/unfun0.png");
			}

		}
		funnyManager.finalize();
		userManager.finalize();
		
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("funnies",funnies);
		request.setAttribute("funs",funs);
		request.setAttribute("unfuns",unfuns);
		request.setAttribute("imgStateFun",imgStateFun);
		request.setAttribute("imgStateUnfun",imgStateUnfun);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewPublicFunnies.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

