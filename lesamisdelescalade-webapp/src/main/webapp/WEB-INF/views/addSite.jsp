<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Ajouter un site ------------------------------------------------------------------------------------------------>

<h2>Ajouter un site</h2>
<form:form action="processAddSite" method="POST" modelAttribute="site">

	<!-- Ajout nom du site -->
	<div class="form-group">
		<label>Nom:</label>
		<form:input path="nom" class="col-12 col-sm-7 col-md-5 col-lg-4 col-xl-3 form-control"
			placeholder="Entrer le nom du site" />
		<small><form:errors path="nom" cssClass="errors" /></small>
	</div>

	<!-- Ajout description du site -->
	<div class="form-group">
		<label>Description:</label>
		<form:textarea path="description" class="col-12 form-control"
			placeholder="Entrer une description du site, 30 caractères minimum" />
		<small><form:errors path="description" cssClass="errors" /></small>
	</div>

	<!-- Selectionner le département du site -->
	<div class="form-group">
		<label>Département:</label>
		<form:select class="col-12 col-sm-8 col-md-6 col-lg-4 col-xl-4 form-control" path="departement_id">
			<option <c:if test="${empty departementIdSite }">selected</c:if>>Sélectionner
				le département</option>
			<c:forEach items="${departements}" var="departement">
				<option value="${departement.id }"
					<c:if test="${departement.id == departementIdSite }">selected</c:if>>${departement.code }-
					${departement.nom }</option>
			</c:forEach>
		</form:select>

	</div>

	<!-- Add Site button -->
	<div class="form-group">
		<div class="col-md-4">
			<button id="singlebutton" type="submit" name="singlebutton"
				class="btn btn-info">Ajouter un site</button>
		</div>
	</div>
</form:form>

<!-- Display login error messages -->
<c:if test="${!empty messageErrorSite }">
	<div class="col-md-5 alert alert-danger text-center" role="alert">
		<c:out value="${messageErrorSite}"></c:out>
	</div>
</c:if>

<!-- Display login error messages -->
<c:if test="${!empty messageSuccessSite }">
	<div class="col-md-5 alert alert-success text-center" role="alert">
		<c:out value="${messageSuccessSite}"></c:out>
	</div>
</c:if>
