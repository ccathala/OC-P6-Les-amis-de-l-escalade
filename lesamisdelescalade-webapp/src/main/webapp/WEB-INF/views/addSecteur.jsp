<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 id="secteur">Ajouter un secteur</h2>

<form action="processChooseDepartement#secteur" method="GET">

	<!-- Selectionner le département  -->
	<div class="form-group">
		<label>Département:</label> <select name="departementIdSecteur">
			<option value="0"<c:if test="${empty departementIdSecteur}">selected</c:if>>Sélectionnez le département</option>
			<c:forEach items="${departements}" var="departement">
				<option value="${departement.id }"
					<c:if test="${departement.id == departementIdSecteur }">selected</c:if>>${departement.code }-
					${departement.nom }</option>
			</c:forEach>
		</select>
		
	</div>

	<!-- Valid Departement button -->
	<div class="form-group">
		<div class="col-md-4">
			<button id="validDepartement" type="submit" name="validDepartement"
				class="btn btn-info">Valider</button>
		</div>
	</div>
</form>

<c:if test="${!empty departementIdSecteur }">
	<form:form action="processAddSecteur#secteur" method="POST"
		modelAttribute="secteur">

		<c:if test="${!empty sites }">

			<!-- Selectionner le site  -->
			<div class="form-group">
				<label>Site:</label>
				<form:select path="site_id">
				<option <c:if test="${empty siteIdSecteur}">selected</c:if>>Sélectionner le site</option> 
					<c:forEach items="${sites}" var="site">
						<option value="${site.id }" <c:if test="${site.id == siteIdSecteur}">selected</c:if> >${site.nom}</option>
					</c:forEach>
				</form:select>
			</div>



			<!-- Ajout nom du secteur -->
			<div class="form-group">
				<label>Nom:</label>
				<form:input path="nom" cssClass="form-control"
					placeholder="Entrer le nom du secteur" />
				<small><form:errors path="nom" cssClass="errors" /></small>
			</div>

		<!-- Ajout description du secteur -->
			<div class="form-group">
				<label>Description:</label>
				<form:input path="description" cssClass="form-control"
					placeholder="Entrer une description du secteur, 30 caractères minimum" />
				<small><form:errors path="description" cssClass="errors" /></small>
			</div> 

			<!-- Bouton ajouter le secteur -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="addSecteur" type="submit" name=addSecteur
						class="btn btn-info">Ajouter Secteur</button>
				</div>
			</div>
		</c:if>

		<c:if test="${empty sites }">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="Aucun site n'est répertorié dans ce département"></c:out>
			</div>
		</c:if>

	</form:form>
</c:if>


<!-- Display login error messages -->
<c:if test="${!empty messageErrorSecteur }">
	<div class="col-md-5 alert alert-danger text-center" role="alert">
		<c:out value="${messageErrorSecteur}"></c:out>
	</div>
</c:if>

<!-- Display login error messages -->
<c:if test="${!empty messageSuccessSecteur }">
	<div class="col-md-5 alert alert-success text-center" role="alert">
		<c:out value="${messageSuccessSecteur}"></c:out>
	</div>
</c:if>