package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageFunnies;
import models.Funny;
import models.User;

/**
 * Servlet implementation class AddFunnyFromUser
 */
@WebServlet("/AddFunny")
public class AddFunny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFunny() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Funny funny = new Funny();
		ManageFunnies funnyManager = new ManageFunnies();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		try {
			
			if (session != null || user != null)
				BeanUtils.populate(funny, request.getParameterMap());
				funny.setAuthorId(user.getId());
				funny.setAuthorUsername(user.getUsername());
				funny.setTimestamp(new Timestamp(System.currentTimeMillis()));
				funnyManager.addFunny(funny);
				funnyManager.finalize();

		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}