<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>

<title>Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container" id="addForm">


		<h1>Ajouter un lieu de grimpe</h1>
		<hr>


		<p>

			<!-- Collapse Button Add Site -->
			<button class="btn btn-info" type="button" data-toggle="collapse"
				data-target="#ajouterSite" aria-expanded=<c:choose>
				<c:when test="${collapseAriaSite }">"true"</c:when>
				<c:otherwise>"false"</c:otherwise>
			</c:choose>
				aria-controls="ajouterSite">Ajouter un site</button>


			<!-- Collapse Button Add Secteur -->
			<button class="btn btn-info" type="button" data-toggle="collapse"
				data-target="#ajouterSecteur" aria-expanded=<c:choose>
				<c:when test="${collapseAriaSecteur }">"true"</c:when>
				<c:otherwise>"false"</c:otherwise>
			</c:choose>
				aria-controls="ajouterSecteur">Ajouter une secteur</button>


			<!-- Collapse Button Add Voie -->
			<button class="btn btn-info" type="button" data-toggle="collapse"
				data-target="#ajouterVoie" aria-expanded=<c:choose>
				<c:when test="${collapseAriaVoie }">"true"</c:when>
				<c:otherwise>"false"</c:otherwise>
			</c:choose>
				aria-controls="ajouterVoie">Ajouter une voie</button>


			<!-- Collapse Button Add Longueur -->
			<button class="btn btn-info" type="button" data-toggle="collapse"
				data-target="#ajouterLongueur"
				aria-expanded=<c:choose>
				<c:when test="${collapseAriaLongueur }">"true"</c:when>
				<c:otherwise>"false"</c:otherwise>
			</c:choose>
				aria-controls="ajouterLongueur">Ajouter une longueur</button>
		</p>




		<div class="collapse ${collapseClassSite }" id="ajouterSite" data-parent="#addForm">
			<div class="card card-body">
				<jsp:include page="addSite.jsp"></jsp:include>

			</div>
		</div>



		<!-- Ajouter un secteur ------------------------------------------------------------------------------------------------>

		<div class="collapse ${collapseClassSecteur }" id="ajouterSecteur" data-parent="#addForm">
			<div class="card card-body">
				<jsp:include page="addSecteur.jsp"></jsp:include>

			</div>
		</div>

		<!-- Ajouter une voie ------------------------------------------------------------------------------------------------>

		<div class="collapse ${collapseClassVoie }" id="ajouterVoie" data-parent="#addForm">
			<div class="card card-body">
				<jsp:include page="addVoie.jsp"></jsp:include>

			</div>
		</div>

		<!-- Ajouter une longueur ------------------------------------------------------------------------------------------------>

		<div class="collapse ${collapseClassLongueur }" id="ajouterLongueur"
			data-parent="#addForm">
			<div class="card card-body ">
				<jsp:include page="addLongueur.jsp"></jsp:include>
			</div>
		</div>



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


</body>
</html>
