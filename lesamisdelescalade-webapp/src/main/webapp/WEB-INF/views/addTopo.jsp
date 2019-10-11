<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Ajouter un topo</title>
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

		<h1>Ajouter un topo</h1>

		<form action="processSelectDepartementAtAddTopoPage" method="GET">
			<div class="form-group">
				<label>Département:</label> <select name="departementId">
					<option value="0"
						<c:if test="${departementId == null }">selected</c:if>>Sélectionner
						le département</option>
					<c:forEach items="${departements }" var="departement">
						<option value="${departement.id }"
							<c:if test="${departementId == departement.id }">selected</c:if>>${departement.code }-${departement.nom}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<div class="col-md-4">
					<button id="selectDepartementButton" name="selectDepartementButton"
						class="btn btn-info" type="submit">Valider</button>
				</div>
			</div>

		</form>

		<c:if test="${!empty sites}">
			<form:form action="processAddTopo" method="POST"
				modelAttribute="newTopo">
				
				
				
				<div class="form-group">
					<label>Sélectionner le site</label>
					<form:select path="site_id">
						<option value="0" <c:if test="${empty siteId }">selected</c:if>>Sélectionner
							le site</option>
						<c:forEach items="${sites }" var="site">
							<option value="${site.id }"
								<c:if test="${site.id == siteId }">selected</c:if>><c:out
									value="${site.nom }"></c:out></option>
						</c:forEach>
					</form:select>
				</div>

				<div class="form-group">
					<label>Nom:</label>
					<form:input path="nom" cssClass="form-control" />
					<small><form:errors path="nom" cssClass="errors" /></small>
				</div>

				<div class="form-group">
					<label>Description:</label>
					<form:input path="description" cssClass="form-control"/>
					<small><form:errors path="description" cssClass="errors" /></small>
				</div>

				<div class="form-group">
					<label>Date de parution:</label>
					<form:input path="date_parution" cssClass="form-control" placeholder="Saisir l'année au format 'aaaa'"/>
					<small><form:errors path="date_parution" cssClass="errors" /></small>
				</div>

				<div class="form-group">
					<div class="col-md-4">
						<button id="addTopoButton" name="addTopoButton"
							class="btn btn-info" type="submit">Ajouter le topo</button>
					</div>
				</div>
				
				
			</form:form>
			</c:if>
			
			<c:if test="${empty sites && departementId != null }">
				<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="Aucun site répertorié dans ce département"></c:out>
			</div>
			</c:if>
		

		<!-- Display Add Topo error messages -->
		<c:if test="${!empty messageErrorTopo }">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="${messageErrorTopo}"></c:out>
			</div>
		</c:if>

		<!-- Display add Topo success messages -->
		<c:if test="${!empty messageSuccessTopo }">
			<div class="col-md-5 alert alert-success text-center" role="alert">
				<c:out value="${messageSuccessTopo}"></c:out>
			</div>
		</c:if>
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
