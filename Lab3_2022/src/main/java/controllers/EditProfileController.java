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
@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		ManageUsers userManager = new ManageUsers();
		User user = (User) session.getAttribute("user");
		User toUpdate = new User();
		user = userManager.getUser(user.getId());
		
		try {
			BeanUtils.populate(toUpdate, request.getParameterMap());

			if(toUpdate.getPwd1().equals(toUpdate.getPwd2())) { //Re-comprovem al servidor que hagi escrit la nova password correctament als dos fields
				userManager.updateUser(user.getId(), toUpdate.getPwd1(), toUpdate.getNickname(), toUpdate.getGender(), toUpdate.getBirth());
				
			}
	   
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
