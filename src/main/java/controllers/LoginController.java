package controllers;

import java.io.IOException;


import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String pw = request.getParameter("password");
		
		UserService us = new UserService() ;
		User user=us.login(email, pw);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userid",user.getId());
			session.setAttribute("userRole",user.getRole());
			
			if(user.getRole().equals("admin")) {
				response.sendRedirect("/GestionHotel/admin.jsp"); 

			}
			else {
				response.sendRedirect("/GestionHotel/"); 
			}

			
			
		}	
		else {	
			response.getWriter().println("ERREUR!");
			}
	}

}
