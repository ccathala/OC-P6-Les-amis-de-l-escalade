<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 id="longueur">Ajouter une Longueur</h2>

<!-- Choix département -->
<form action="processChooseDepartementLongueur#longueur" method="GET">

	<!-- Selectionner le département  -->
	<div class="form-group">
		<label>Département:</label> <select name="departementIdLongueur">
			<c:forEach items="${departements}" var="departement">
				<option value="${departement.id }"
					<c:if test="${departement.id == departementIdLongueur }">selected</c:if>>${departement.code }-
					${departement.nom }</option>
			</c:forEach>
		</select>

	</div>

	<!-- Valid Departement button -->
	<div class="form-group">
		<div class="col-md-4">
			<button id="validDepartementLongueur" type="submit"
				name="validDepartementLongueur" class="btn btn-info">Valider</button>
		</div>
	</div>
</form>

<c:if test="${!empty departementIdLongueur }">

	<!-- Choix site -->
	<form action="processChooseSiteLongueur#longueur" method="GET">

		<c:if test="${!empty sites }">

			<!-- Selectionner le site -->
			<div class="form-group">
				<label>Site:</label> <select name="siteIdLongueur">
					<c:forEach items="${sites}" var="site">
						<option value="${site.id }"
							<c:if test="${site.id == siteIdVoie }">selected</c:if>>${site.nom }</option>
					</c:forEach>
				</select>

			</div>

			<!-- Valid Site button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="validSiteLongueur" type="submit"
						name="validSiteLongueur" class="btn btn-info">Valider</button>
				</div>
			</div>

		</c:if>

		<c:if test="${empty sites }">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="Aucun site n'est répertorié dans ce département"></c:out>
			</div>

		</c:if>
	</form>

	<c:if test="${!empty siteIdLongueur }">

		<!-- Choix secteur -->
		<form action="processChooseSecteurLongueur#longueur" method="GET">

			<c:if test="${!empty secteurs }">

				<!-- Selectionner le site -->
				<div class="form-group">
					<label>Secteur:</label> <select name="secteurIdLongueur">
						<c:forEach items="${secteurs}" var="secteur">
							<option value="${secteur.id }"
								<c:if test="${secteur.id == secteurIdLongueur }">selected</c:if>>${secteur.nom }</option>
						</c:forEach>
					</select>

				</div>

				<!-- Valid Site button -->
				<div class="form-group">
					<div class="col-md-4">
						<button id="validSecteurLongueur" type="submit"
							name="validSecteurLongueur" class="btn btn-info">Valider</button>
					</div>
				</div>

			</c:if>

			<c:if test="${empty secteurs }">
				<div class="col-md-5 alert alert-danger text-center" role="alert">
					<c:out value="Aucun secteur n'est répertorié sur ce site"></c:out>
				</div>

			</c:if>
		</form>

		<c:if test="${!empty secteurIdLongueur }">

			<!-- Formulaire ajouter une longueur -->
			<form:form action="processAddLongueur#longueur" method="POST"
				modelAttribute="longueur">

				<c:if test="${!empty voies}">

					<!-- Selectionner la voie  -->
					<div class="form-group">
						<label>Voie:</label>
						<form:select path="voie_id">
							<c:forEach items="${voies}" var="voie">
								<option value="${voie.id }">${voie.numero}-${voie.nom}</option>
							</c:forEach>
						</form:select>
					</div>

					<!-- Ajout du numéro de la longueur  -->
					<div class="form-group">
						<label>Numéro:</label>
						<form:input path="numero" cssClass="form-control" />
						<small><form:errors path="numero" cssClass="errors" /></small>
					</div>

					<!-- Selectionner la cotation  -->
					<div class="form-group">
						<label>Cotation:</label>
						<form:select path="cotation_id">
							<c:forEach items="${cotations}" var="cotation">
								<option value="${cotation.id }">${cotation.cotation }</option>
							</c:forEach>
						</form:select>
					</div>

					<!-- Bouton ajouter la longueur -->
					<div class="form-group">
						<div class="col-md-4">
							<button id="addLongueur" type="submit" name=addLongueur
								class="btn btn-info">Ajouter la longeur</button>
						</div>
					</div>
				</c:if>

				<c:if test="${empty voies}">
					<div class="col-md-5 alert alert-danger text-center" role="alert">
						<c:out value="Aucune voie n'est répertorié sur ce secteur"></c:out>
					</div>
				</c:if>

			</form:form>
		</c:if>
	</c:if>
</c:if>

<!-- Display Add Voie error messages -->
<c:if test="${!empty messageErrorLongueur }">
	<div class="col-md-5 alert alert-danger text-center" role="alert">
		<c:out value="${messageErrorLongueur}"></c:out>
	</div>
</c:if>

<!-- Display add voie success messages -->
<c:if test="${!empty messageSuccessLongueur }">
	<div class="col-md-5 alert alert-success text-center" role="alert">
		<c:out value="${messageSuccessLongueur}"></c:out>
	</div>
</c:if>

