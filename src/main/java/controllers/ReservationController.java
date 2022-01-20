package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.coyote.Request;

import entities.Chambre;
import entities.Reservation;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.ChambreService;
import services.ReservationService;
import services.UserService;

/**
 * Servlet implementation class ReservationController
 */
@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChambreService cs = new ChambreService();
	ReservationService rs = new ReservationService();
	UserService us = new UserService();

	/**
	 * Default constructor.
	 */
	public ReservationController() {
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
			int clientId = Integer.parseInt(request.getParameter("user"));
			int roomId = Integer.parseInt(request.getParameter("room"));
			User client = us.findById(clientId);
			Chambre chambre = cs.findById(roomId);
			/*Date dated = null;
			try {
				dated = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("dated")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date datef = null;
			try {
				datef = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("datef")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			Date dated=new Date(request.getParameter("dated").replace("-", "/"));
			Date datef=new Date(request.getParameter("datef").replace("-", "/"));
			long diffInMillies = Math.abs(datef.getTime() - dated.getTime());
			int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			int price = chambre.getPrix() * diff;

			if (rs.create(new Reservation(dated, datef, price, client, chambre))) {

				if(request.getParameter("from") != null && request.getParameter("from").equals("client") ) {
                    response.sendRedirect("/GestionHotel/index.jsp");
                }else {
                    response.sendRedirect("/GestionHotel/GestionReservation.jsp");
                }

			} else {
				response.sendRedirect("/GestionHotel/gestionReservation.jsp");
			}

		} else if (request.getParameter("action") != null && request.getParameter("action").equals("modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int clientId = Integer.parseInt(request.getParameter("user"));
			int roomId = Integer.parseInt(request.getParameter("room"));
			User client = us.findById(clientId);
			Chambre chambre = cs.findById(roomId);
			Date dated = null;
			try {
				dated = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("dated")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date datef = null;
			try {
				datef = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("datef")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long diffInMillies = Math.abs(datef.getTime() - dated.getTime());
			int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			int price = chambre.getPrix() * diff;

			if (rs.update(new Reservation(id,dated, datef, price, client, chambre))) {

				response.sendRedirect("/GestionHotel/gestionReservation.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionReservation.jsp");
			}
		} else if (request.getParameter("action") != null && request.getParameter("action").equals("supprimer")) {
			ReservationService rs = new ReservationService();
			int id = Integer.parseInt(request.getParameter("id"));
			if (rs.delete(rs.findById(id))) {

				response.sendRedirect("/GestionHotel/gestionReservation.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionReservation.jsp");

			}

		}
	}
}
