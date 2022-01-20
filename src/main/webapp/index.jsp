<%@page import="java.util.ArrayList"%>
<%@page import="entities.Chambre"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login V18</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body style="background-color: #666666;">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form " action="SearchController"
					method="get">
					<span class="login100-form-title p-b-43"> Reservez votre
						chambre </span>


					<div class="wrap-input100 validate-input"">
						<input class="input100" type="text" name="prix"> <span
							class="focus-input100"></span> <span class="label-input100">prix</span>
					</div>

					<div class="wrap-input100 validate-input">
						<select class="input100" type="text" name="type">
							<option value="simple">simple</option>
							<option value="double">double</option>
							<option value="familiale">familiale</option>
						</select> <span class="focus-input100"></span> <span class="label-input100">type</span>
					</div>

					<div class="wrap-input100 validate-input">
						<input class="input100" type="date" name="dated"> <span
							class="focus-input100"></span> <span class="label-input100">Date
							de début :</span>
					</div>
					<div class="wrap-input100 validate-input">
						<input class="input100" type="date" name="datef"> <span
							class="focus-input100"></span> <span class="label-input100">Date
							de fin :</span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-32">
						<div class="contact100-form-checkbox"></div>

					</div>


					<div class="container-login100-form-btn">
						<input class="login100-form-btn" type="submit" value="rechercher">


					</div>


				</form>

				<div class="login100-more"
					style="background-image: url('images/reserve.jpg');"></div>
			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("results") != null) {
		List<Chambre> chambres = (ArrayList<Chambre>) request.getAttribute("results");
		for (Chambre chambre : chambres) {
	%>
	<div
		style="width: 300px; height: 400px; display: flex; flex-direction: column; background-color: #d1b3ff
		; box-shadow: 5px 5px 5px black">
		<img alt="image hrere" src="images/hotel.jpg"
			style="width: 300px; height: 250px; box-shadow: 3px 3px 3px black">
		<div
			style="display: flex; flex-direction: column; padding: 16px; align-items: center; justify-content: center;">
			<div
				style="display: flex; flex-direction: row; width: 70%; justify-content: space-between; padding: 0 12px; color: white; font-weight: bold; font-size: large; background-color: #aa80ff; margin-top: 4px; border-bottom: 3px; border-right: 3px; border-color: #00e64d; border-style: solid; border-top: 0px; border-left: 0px">
				<p>Numero:</p>
				<p><%=chambre.getNum()%></p>
			</div>
			<div
				style="display: flex; flex-direction: row; width: 70%; justify-content: space-between; padding: 0 12px; color: white; font-weight: bold; font-size: large; background-color: #aa80ff; margin-top: 4px; border-bottom: 3px; border-right: 3px; border-color: #00e64d; border-style: solid; border-top: 0px; border-left: 0px">
				<p>Type:</p>
				<p><%=chambre.getType()%></p>
			</div>
			<div
				style="display: flex; flex-direction: row; width: 70%; justify-content: space-between; padding: 0 12px; color: white; font-weight: bold; font-size: large; background-color: #aa80ff; margin-top: 4px; border-bottom: 3px; border-right: 3px; border-color: #00e64d; border-style: solid; border-top: 0px; border-left: 0px">
				<p>Prix:</p>
				<p><%=chambre.getPrix() + "Dhs"%></p>
			</div>
			<form action="ReservationController" method="post">
				<input type="hidden" name="room" value="<%=chambre.getId()%>">
				<input type="hidden" name="action" value="ajouter"> <input
					type="hidden" name="from" value="client"> <input
					type="hidden" name="user"
					value="<%=session.getAttribute("userid") == null ? "" : session.getAttribute("userid").toString()%>">
				<input type="hidden" name="dated"
					value="<%=request.getParameter("dated").toString().replace("-", "/")%>">
				<input type="hidden" name="datef"
					value="<%=request.getParameter("datef").toString().replace("-", "/")%>">
				<%
				if (session.getAttribute("userid") == null) {
				%>
				<a
					style="text-decoration: none; color: white; text-align: center; background-color: #aa80ff; padding: 6px; margin: 8px"
					href="/GestionHotel/login.jsp">Se connecter pour réserver</a>
				<%
				} else {
				%>

				<input
					style="background-color: green; padding: 8px; color: white; cursor: pointer;"
					type="submit" value="Réserver">
				<%
				}
				%>
			</form>
		</div>
	</div>
	<%
	}
	}
	%>





	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>