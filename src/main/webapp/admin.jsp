<%@page import="services.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-image: url('images/hotel.jpg');background-size: cover">

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
    <div style="display: flex; flex-direction:column ;text-align: center;align-items: center;margin:5px;">
    <div style="display: flex; flex-direction:column ;background-color: #bf80ff;width: 300px; justify-content: space-between; margin: 20px 0; border:2px solid;padding: 30px 0">
<a href="gestionUtilisateurs.jsp"> gestion des utilisateurs</a></div>
<div style="display: flex; flex-direction:column ;background-color: #bf80ff;width: 300px; justify-content: space-between; margin: 20px 0; border:2px solid;padding: 30px 0">
<a href="gestionChambres.jsp"> gestion des chambres</a></div>
<div style="display: flex; flex-direction:column ;background-color: #bf80ff;width: 300px; justify-content: space-between; margin: 20px 0; border:2px solid;padding: 30px 0">
<a href="gestionReservation.jsp"> gestion des reservations</a></div>
</div>
</body>
</html>