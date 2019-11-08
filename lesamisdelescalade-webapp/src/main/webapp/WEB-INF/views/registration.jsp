<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Registration</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>


	<div class="container">

		<!-- Import header -->
		<jsp:include page="header.jsp"></jsp:include>
		
		<div style="min-height:500px;">		
		
		<h1>Inscription</h1>

		<!-- Login Form -->
		<form:form action="processRegistration" method="POST"
			modelAttribute="registrationUtilisateur">

			<!-- Nom Input -->
			<div class="col-12 col-sm-6 col-md-4 form-group">
				<label>Nom d'utilisateur:</label>
				<form:input path="nom" cssClass="form-control"
					placeholder="Entrer nom d'utilisateur" />
				<small><form:errors path="nom" cssClass="errors" /></small>
			</div>

			<!-- Email Input -->
			<div class="col-12 col-sm-6 col-md-4 form-group">
				<label>Email:</label>
				<form:input path="email" cssClass="form-control"
					placeholder="Entrer email" />
				<small><form:errors path="email" cssClass="errors" /></small>
			</div>

			<!-- Password Input -->
			<div class="col-12 col-sm-6 col-md-4 form-group">
				<label>Mot de passe:</label>
				<form:input type="password" path="password" cssClass="form-control"
					placeholder="Entrer mot de passe" />
				<small><form:errors path="password" cssClass="errors" /></small>
			</div>

			<!-- Password confirmation Input -->
			<div class="col-12 col-sm-6 col-md-4 form-group">
				<label>Confirmation mot de passe:</label>
				<form:input type="password" path="confirmPassword"
					cssClass="form-control" placeholder="Entrer mot de passe" />
				<small><form:errors path="confirmPassword" cssClass="errors" /></small>
			</div>

			<!-- Register button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="singlebutton" type="submit" name="singlebutton"
						class="btn btn-primary">S'inscrire</button>
				</div>
			</div>
		</form:form>

		<!-- Display login error messages -->
		<c:if test="${!empty messageError }">
			<div class="col-md-6 alert alert-danger text-center" role="alert">
				<c:out value="${messageError}"></c:out>
			</div>
		</c:if>
		
		</div>

		<!-- Import footer -->
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
