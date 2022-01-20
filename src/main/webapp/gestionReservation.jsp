<%@page import="entities.Chambre"%>
<%@page import="services.ChambreService"%>
<%@page import="entities.User"%>
<%@page import="services.UserService"%>
<%@page import="services.ReservationService"%>
<%@page import="java.util.List"%>
<%@page import="entities.Reservation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-image: url('images/acu.jpg');background-size: cover">
<jsp:include page="header.jsp"></jsp:include>
<%

    UserService uss = new UserService();
    if (session.getAttribute("userid") == null) {
        response.sendRedirect("/GestionHotel/login.jsp");
    } else {
        int id = Integer.parseInt(session.getAttribute("userid").toString());
        if (!uss.findById(id).getRole().toLowerCase().equals("admin")) {
            response.sendRedirect("/GestionHotel/index.jsp");
        }
    }
    %>
    <div style="display: flex; flex-direction: row; text-align: left,margin: 0 auto;margin:10px; background-color: #d9b3ff;border:2px solid;justify-content: space-around;">
			<a href="gestionUtilisateurs.jsp" > gestion des utilisateurs </a> <a
				href="gestionChambres.jsp"> gestion des chambres </a> <a
				href="gestionReservation.jsp"> gestion des reservations</a>
		</div>
	<form action="ReservationController" method="post">
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;">
			
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto; background-color: #d9b3ff;border:1px solid">

			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
			<p>date de debut:</p>
			<input type="date" name="dated"> </div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
			<p>date de fin:</p>
			<input type="date" name="datef"> </div>
				<select name="user">
				<option value="">choisir un client</option>
				<% UserService us=new UserService();
				List<User> users= us.findAll();
				for(User user: users){
				%>
				<option value="<%=user.getId() %>"><%=user.getNom()%> <%=user.getPrenom()%> </option>
				<%} %>
			</select> 
			 <select name="room">
				<option value="">choisir une chambre</option>
				<% ChambreService cs=new ChambreService();
				List<Chambre> chambres= cs.findAll();
				for(Chambre chambre: chambres){
				%>
				<option value="<%=chambre.getId() %>"><%=chambre.getNum()%> </option>
				<%} %>
			</select>
			<input type="submit" value="ajouter"> <input type="hidden"
				value="ajouter" name="action">
		</div>
		</div>
	</form>

	<p style="margin:20px auto">Liste des reservations </p>
	<%
	ReservationService rs = new ReservationService();
	List<Reservation> reservations = rs.findAll();
	for (Reservation reservation : reservations) {
	%>

	<form action="ReservationController" method="post">
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;background-color:  #ccb3ff">
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>numero:</p>
			<input  type="text" name="id" value="<%=reservation.getId()%>"
				readonly="readonly"> </div>
				<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>date de debut:</p>
				<input type="date" name="dated"
				value="<%=reservation.getDated()%>"></div>
				<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>date de fin:</p>
				 <input type="date"
				name="datef" value="<%=reservation.getDatef()%>"> 
				</div>
				<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>utilisateur:</p>
				<select name="user">
				<option value="<%=reservation.getClient().getId() %>"><%=reservation.getClient().getNom()%> <%=reservation.getClient().getPrenom() %> </option>
				<% 
				
				for(User user: users){
				%>
				<option value="<%=user.getId() %>"><%=user.getNom()%> <%=user.getPrenom()%> </option>
				<%} %>
			</select> </div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>chambre:</p>
			 <select name="room">
				<option value="<%=reservation.getChambre().getId()%>"><%=reservation.getChambre().getNum() %></option>
				<% 
				
				for(Chambre chambre: chambres){
				%>
				<option value="<%=chambre.getId() %>"><%=chambre.getNum()%> </option>
				<%} %>
			</select> </div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>prix:</p>
				<input readonly="readonly" type="text"  value="<%=reservation.getPrix()%>"> </div>

			<input type="submit" value="modifier"> <input type="hidden"
				value="modifier" name="action">
		</div>
	</form>
	<form action="ReservationController" method="post">
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;">
			<input type="hidden" name="id" value="<%=reservation.getId()%>"
				readonly="readonly"> <input type="submit" value="supprimer">
			<input type="hidden" value="supprimer" name="action">
		</div>
	</form>
	<%
	}
	%>
</body>
</html>