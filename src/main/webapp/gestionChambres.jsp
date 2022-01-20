<%@page import="services.UserService"%>
<%@page import="entities.Chambre"%>
<%@page import="services.ChambreService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Insert title here</title>
</head>
<body style="background-image: url('images/chambra.jpg');background-size: cover">
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

	<form action="ChambreController" method="post"
		enctype="multipart/form-data">
		
		<div style="display: flex; flex-direction: row; text-align: left,margin: 0 auto;margin:10px; background-color: #d9b3ff;border:2px solid;justify-content: space-around;">
			<a href="gestionUtilisateurs.jsp" > gestion des utilisateurs </a> <a
				href="gestionChambres.jsp"> gestion des chambres </a> <a
				href="gestionReservation.jsp"> gestion des reservations</a>
		</div>
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto; background-color: #d9b3ff;border:1px solid">

			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>numero:</p>
				<input type="text" name="num">
			</div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>type:</p>
				<select name="type" style="width: 177px">
					<option value="simple">simple</option>
					<option value="double">double</option>
					<option value="familiale">familiale</option>
				</select>
			</div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>prix:</p>
				<input type="text" name="prix">
			</div>
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
			<input type="file" name="file">
			</div>
			 <input type="submit"
				value="ajouter"> <input type="hidden" value="ajouter"
				name="action">
		</div>
	</form>

	<p>Liste des chambres</p>
	<%
	ChambreService cs = new ChambreService();
	List<Chambre> chambres = cs.findAll();
	for (Chambre chambre : chambres) {
	%>


	<form action="ChambreController" method="post">
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;background-color: #d9b3ff;;border:1px solid">
			<img alt="erreur" src="<%="images/"+chambre.getType()+".jpg"%>">
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>id:</p>
				
			<input type="text" name="id" value="<%=chambre.getId()%>"readonly="readonly">
			</div>
			
			<div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>numero:</p>
				 <input type="text" name="num" value="<%=chambre.getNum()%>"> 
				 </div>
				 <div
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>type:</p>
				<input type="text"
				name="type" value="<%=chambre.getType()%>">
				</div>
				<div 
				style="display: flex; flex-direction: row; width: 300px; justify-content: space-between; margin: 2px 0">
				<p>id :</p>
				 <input
				type="text" name="prix" value="<%=chambre.getPrix()%>">
				</div>
				 <input
				type="submit" value="modifier"> <input type="hidden"
				value="modifier" name="action">
		
	</form>
	<form action="ChambreController" method="post">
		<div
			style="display: flex; flex-direction: column; width: 400px; margin: 0 auto;">
			<input type="hidden" name="id" value="<%=chambre.getId()%>"
				readonly="readonly"> <input type="submit" value="supprimer">
			<input type="hidden" value="supprimer" name="action">
		</div> </div>
	</form>
	<%
	}
	%>

</body>
</html>