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

	<div class="container">

		<jsp:include page="header.jsp"></jsp:include>

		<h1>Se connecter</h1>

		<!-- Login Form -->
		<form:form action="processLogin" method="POST"
			modelAttribute="loginUtilisateur">

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

		<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/60efee8a0b.js"
		crossorigin="anonymous"></script>
</body>
</html>
