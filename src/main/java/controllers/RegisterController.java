package controllers;

import java.io.IOException;

import org.apache.coyote.Request;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegisterController() {
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
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String pw = request.getParameter("password");
		System.out.println("nom "+nom+" prenom: "+prenom+" email: "+email+" password: "+pw);
		
		UserService us = new UserService() ;
		if(us.create(new User(nom,prenom,email,pw,"En attente","client"))) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", us.findByEmail(email).getId());
			
			response.sendRedirect("/GestionHotel/login.jsp"); 
			
			
			
		}	
		else {	
			response.getWriter().println("ERREUR!");
			}
			
		
		
		
		
			
		
	}

}
