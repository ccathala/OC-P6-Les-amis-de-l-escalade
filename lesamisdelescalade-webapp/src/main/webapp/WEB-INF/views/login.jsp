<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<!-- Import header -->
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">

		<!-- Login Form -->
		<form:form action="processLogin" method="POST"
			modelAttribute="utilisateur">

			<!-- Email Input -->
			<div class="form-group">
				<label>Email:</label>
				<form:input path="email" cssClass="form-control"
					placeholder="Entrer email" />
				<small><form:errors path="email" cssClass="errors" /></small>
			</div>

			<!-- Password Input -->
			<div class="form-group">
				<label>Mot de passe:</label>
				<form:input type="password" path="password" cssClass="form-control"
					placeholder="Enter mot de passe" />
				<small><form:errors path="password" cssClass="errors" /></small>
			</div>

			<!-- Connection button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="singlebutton" type="submit" name="singlebutton"
						class="btn btn-primary">Connexion</button>
				</div>
			</div>
		</form:form>

		<!-- Display login error messages -->
		<c:if test="${!empty erreur_login }">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="${erreur_login}"></c:out>
			</div>
		</c:if>
	</div>
</body>
</html>
