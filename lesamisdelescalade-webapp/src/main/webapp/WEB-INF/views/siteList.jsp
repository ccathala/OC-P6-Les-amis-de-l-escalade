<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>

<title>Site</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<h1>Rechercher un site</h1>
		<form:form action="searchSite" method="POST" modelAttribute="searchSiteData">

			<div class="form-group">
				<label>Rechercher par nom:</label>
				<form:input path="nom" cssClass="form-control"
			placeholder="Entrer le nom du site" />
			</div>


			<hr>

			<div class="form-group">
				<label>Rechercher par département:</label>
				<form:select path="departementId">
					<option value="0" selected>Sélectionner le département</option>
					<c:forEach items="${departements }" var="departement">
						<option value="${departement.id }">${departement.code }-
							${departement.nom }</option>
					</c:forEach>
				</form:select>
			</div>

			<div class="form-group">
				<label>Rechercher par cotation:</label>
				<form:select path="cotationId">
					<option value="0" selected>Sélectionner la cotation</option>
					<c:forEach items="${cotations }" var="cotation">
						<option value=${cotation.id }>${cotation.cotation }</option>
					</c:forEach>
				</form:select>
			</div>

			<div class="form-group">
			<label>Rechercher par nombre de secteurs:</label>
			<form:select path="secteurCount">
				<option value="0" selected>Sélectionner le nombre de
					secteurs</option>
				<c:forEach items="${secteurCount }" var="count">
					<option value="${count}">${count}</option>
				</c:forEach>
			</form:select>
			</div>
			

			<!-- Search Site button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="singlebutton" type="submit" name="singlebutton"
						class="btn btn-info">Rechercher</button>
				</div>
			</div>
		</form:form>

		<c:forEach items="${sites }" var="site">
			<div class="card mb-3">
				<div class="row no-gutters">
					<div class="col-md-4">
						<img
							src="${pageContext.request.contextPath}/resources/pictures/climbing-icon.jpg"
							class="card-img" alt="symbole montagne">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">
								<c:out value="${site.nom }"></c:out>
							</h5>
							<p class="card-text">
								<c:out value="${site.description }"></c:out>
							</p>
							<a class="btn btn-info"
								href="<c:url value="/site/${site.id }"></c:url>">En savoir
								plus </a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


	</div>
</body>
</html>