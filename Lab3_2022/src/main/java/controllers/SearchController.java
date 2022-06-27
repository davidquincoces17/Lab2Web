package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageFunnies;
import models.Funny;
import models.Search;
import models.User;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
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
		Search search = new Search();
		
		
		if (session != null || user != null) {
			try {
				BeanUtils.populate(search, request.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("SEARCH PREPARED IS TRUE");
			ManageFunnies funnyManager = new ManageFunnies();
			String toSearch = "%"+search.getSearchValue()+"%";
			System.out.println("--------> " + toSearch);

			
			funnies = funnyManager.getFunnySearch(toSearch,0,20);
			
			
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
		
		
		request.setAttribute("funnies",funnies);
		
		request.setAttribute("user",user);
		request.setAttribute("funnies",funnies);
		request.setAttribute("funs",funs);
		request.setAttribute("unfuns",unfuns);
		request.setAttribute("imgStateFun",imgStateFun);
		request.setAttribute("imgStateUnfun",imgStateUnfun);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewSearch.jsp"); 
		dispatcher.forward(request,response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
