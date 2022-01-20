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
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null && request.getParameter("action").equals("ajouter")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			String etat = request.getParameter("etat");
			String role = request.getParameter("role");

			UserService us = new UserService();
			if (us.create(new User(nom, prenom, email, pw, etat, role))) {

				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");
			}

		} else if (request.getParameter("action") != null && request.getParameter("action").equals("modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			String etat = request.getParameter("etat");
			String role = request.getParameter("role");

			UserService us = new UserService();
			User u = new User(id, nom, prenom, email, pw, etat, role);

			if (us.update(u)) {

				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");
			}

		} else if (request.getParameter("action") != null && request.getParameter("action").equals("supprimer")) {
			UserService us = new UserService();
			int id = Integer.parseInt(request.getParameter("id"));
			if (us.delete(us.findById(id))) {

				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionUtilisateurs.jsp");

			}
		}

	}

}
