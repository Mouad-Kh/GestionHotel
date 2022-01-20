package controllers;
import java.io.IOException;

import org.apache.coyote.Request;

import entities.Chambre;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import services.ChambreService;


/**
 * Servlet implementation class ChambreController
 */

//@WebServlet("/ChambreController")
//@MultipartConfig
public class ChambreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChambreController() {
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
		if (request.getParameter("action") != null && request.getParameter("action").equals("ajouter")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String type = request.getParameter("type");
			int prix = Integer.parseInt(request.getParameter("prix"));
			//**
			Part filePart = request.getPart("file");
            String fileName = "room-"+num;
            int i=0;
            for (Part part : request.getParts()) {
            	i++;
            	System.out.println("haha"+request.getParts().size());
            	System.out.println("hoho"+request.getParts());
            	System.out.println("hihi"+i);
                 // part.write("C:\\Users\\asus\\eclipse-workspace\\GestionHotel\\src\\main\\webapp\\images\\chambres\\" + fileName);
                }
			//**
			ChambreService cs=new ChambreService();
			if (cs.create(new Chambre(num, type, prix))) {

				response.sendRedirect("/GestionHotel/gestionChambres.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionChambres.jsp");
			}
			
			}
		else if (request.getParameter("action") != null && request.getParameter("action").equals("modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int num = Integer.parseInt(request.getParameter("num"));
			String type = request.getParameter("type");
			int prix = Integer.parseInt(request.getParameter("prix"));
			

			ChambreService cs=new ChambreService();
			Chambre c = new Chambre(id, num, type, prix);

			if (cs.update(c)) {

				response.sendRedirect("/GestionHotel/gestionChambres.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionChambres.jsp");
			}
	}
		else if (request.getParameter("action") != null && request.getParameter("action").equals("supprimer")) {
			ChambreService cs = new ChambreService();
			int id = Integer.parseInt(request.getParameter("id"));
			if (cs.delete(cs.findById(id))) {

				response.sendRedirect("/GestionHotel/gestionChambres.jsp");

			} else {
				response.sendRedirect("/GestionHotel/gestionChambres.jsp");

			}

}
}
}
