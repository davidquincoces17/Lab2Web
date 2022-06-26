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

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetUserFunnies")
public class GetUserFunnies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserFunnies() {
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
		List<String> imgStateFun = new ArrayList<String>();
		List<String> imgStateUnfun = new ArrayList<String>();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			ManageFunnies funnyManager = new ManageFunnies();
			funnies = funnyManager.getUserFunnies(user.getId(),0,4);
			
			Integer value = 0;
			for (Funny f: funnies) {
				funs.add(funnyManager.getLikes(f.getId()));
				unfuns.add(funnyManager.getDislikes(f.getId()));
				value = funnyManager.getFunnyReaction(user.getId(),f.getId());
				
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
		}

		request.setAttribute("user",user);
		request.setAttribute("funnies",funnies);
		request.setAttribute("funs",funs);
		request.setAttribute("unfuns",unfuns);
		request.setAttribute("imgStateFun",imgStateFun);
		request.setAttribute("imgStateUnfun",imgStateUnfun);
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

