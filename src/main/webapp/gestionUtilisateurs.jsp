<%@page import="entities.User"%>
<%@page import="java.util.List"%>
<%@page import="services.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-image: url('images/user.jpg');background-size: cover">

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
	<jsp:include page="header.jsp"></jsp:include>
	<div style="display: flex; flex-direction: row; text-align: left,margin: 0 auto;margin:10px; background-color: #d9b3ff;border:2px solid;justify-content: space-around;">
			<a href="gestionUtilisateurs.jsp" > gestion des utilisateurs </a> <a
				href="gestionChambres.jsp"> gestion des chambres </a> <a
				href="gestionReservation.jsp"> gestion des reservations</a>
		</div>
	<div
		style="display: flex; flex-direction: row; margin: 70px auto; width: 1000px;">
		
		<div style="display: flex; flex-direction: column;margin: 0px auto;background-color: #ccb3ff">
			<form action="UserController" method="post">
				<div
					style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;">
					<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>nom:</p>
					<input type="text" name="nom"></div>
					<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>prenom:</p>
					 <input type="text"
						name="prenom"> </div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>email:</p>
						<input type="email" name="email"> </div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>password:</p>
						<input
						type="password" name="password"></div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>etat:</p>
						 <input type="text"
						name="etat"> </div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>role:</p>
						<input type="text" name="role"></div>
						 <input
						type="submit" value="ajouter"> <input type="hidden"
						value="ajouter" name="action">
				</div>
			</form>
			<p style="margin:20px auto">Liste des utilisateurs </p>
			<%
			UserService us = new UserService();
			List<User> users = us.findAll();
			for (User user : users) {
			%>
			<form action="UserController" method="post">
				<div
					style="display: flex; flex-direction: column; width: 400px; margin: 20px 0px auto;">
					<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>id:</p>
					<input type="text" name="id" value="<%=user.getId()%>"
						readonly="readonly"></div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>nom:</p>
						 <input type="text" name="nom"
						value="<%=user.getNom()%>"> </div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>prenom:</p>
						<input type="text"
						name="prenom" value="<%=user.getPrenom()%>"></div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>email:</p>
						 <input
						type="email" name="email" value="<%=user.getEmail()%>"> </div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>password:</p>
						 <input
						type="password" name="password" value="<%=user.getPassword()%>"></div>
						<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>etat:</p>
					<input type="text" name="etat" value="<%=user.getEtat()%>"></div>
					<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>role:</p>
					<input type="text" name="role" value="<%=user.getRole()%>"></div>
					<input type="submit" value="modifier"> <input type="hidden"
						value="modifier" name="action">
				</div>
			</form>

			<form action="UserController" method="post">
				<div
					style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;">
					<input type="hidden" name="id" value="<%=user.getId()%>"
						readonly="readonly"> <input type="submit"
						value="supprimer"> <input type="hidden" value="supprimer"
						name="action">
				</div>
			</form>
			<%
			}
			%>
		</div>
</div>

	


</body>
</html>