<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 id="voie">Ajouter une voie</h2>

<!-- Choix département -->
<form action="processChooseDepartementAddVoie#voie" method="GET">

	<!-- Selectionner le département  -->
	<div class="form-group">
		<label>Département:</label> <select class="col-12 col-sm-7 col-md-5 col-lg-4 col-xl-3 form-control" name="departementIdVoie">
		<option value="0" <c:if test="${empty departementIdVoie}">selected</c:if>>Sélectionner le département</option>
			<c:forEach items="${departements}" var="departement">
				<option value="${departement.id }"
					<c:if test="${departement.id == departementIdVoie }">selected</c:if>>${departement.code }-
					${departement.nom }</option>
			</c:forEach>
		</select>

	</div>

	<!-- Valid Departement button -->
	<div class="form-group">
		<div class="col-md-4">
			<button id="validDepartementVoie" type="submit"
				name="validDepartementVoie" class="btn btn-info">Valider</button>
		</div>
	</div>
</form>

<c:if test="${!empty departementIdVoie }">



	<!-- Choix site -->
	<form action="processChooseSiteAddVoie#voie" method="GET">

		<c:if test="${!empty sites }">

			<!-- Selectionner le site -->
			<div class="form-group">
				<label>Site:</label> <select class="col-12 col-sm-7 col-md-5 col-lg-4 col-xl-3 form-control" name="siteIdVoie">
				<option value="0" <c:if test="${empty siteIdVoie}">selected</c:if>>Sélectionner un site</option>
					<c:forEach items="${sites}" var="site">
						<option value="${site.id }"
							<c:if test="${site.id == siteIdVoie }">selected</c:if>>${site.nom }</option>
					</c:forEach>
				</select>

			</div>

			<!-- Valid Site button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="validSiteVoie" type="submit" name="validSiteVoie"
						class="btn btn-info">Valider</button>
				</div>
			</div>

		</c:if>

		<c:if test="${empty sites }">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="Aucun site n'est répertorié dans ce département"></c:out>
			</div>

		</c:if>
	</form>

	<c:if test="${!empty siteIdVoie}">

		

		<c:if test="${!empty secteurs}">

			<!-- Choix du secteur -->
			<form action="processChooseSecteurAddVoie#voie" method="GET">
				<c:if test="${!empty secteurs}">
					<!-- Selectionner le secteur  -->
					<div class="form-group">
						<label>Secteur:</label> <select class="col-12 col-sm-7 col-md-5 col-lg-4 col-xl-3 form-control" name="secteurIdVoie">
						<option value="0" <c:if test="${empty secteurIdVoie}">selected</c:if>>Sélectionner un secteur</option>
							<c:forEach items="${secteurs}" var="secteur">
								<option value="${secteur.id }"
									<c:if test="${secteur.id == secteurIdVoie }">selected</c:if>>${secteur.nom }</option>
							</c:forEach>
						</select>

						<!-- Valid Site button -->
						<div class="form-group">
							<div class="col-md-4 mt-3">
								<button id="validSecteurVoie" type="submit"
									name="validSecteurVoie" class="btn btn-info">Valider</button>
							</div>
						</div>
					</div>
				</c:if>
			</form>

		</c:if>

		<c:if test="${!empty secteurIdVoie }">

			<!-- Formulaire ajouter une voie -->
			<form:form action="processAddVoie#voie" method="POST"
				modelAttribute="voie">

				<c:if test="${!empty voies }">
					<h3>Voies existantes:</h3>

					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Nom</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${voies }" var="voie">
								<tr>
									<td>${voie.numero }</td>
									<td>${voie.nom }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				
				<c:if test="${empty voies }">
					
						<h3>Aucune voie répertoriée dans ce secteur</h3>
					
					</c:if>



				<!-- Selectionner le secteur  -->
				<div class="form-group">
					<form:hidden path="secteur_id" value="${secteurIdVoie }" />

				</div>

				<!-- Ajout du numéro de la voie  -->
				<div class="form-group">
					<label>Numéro:</label>
					<form:input path="numero" class="col-2 col-md-1  form-control" />
					<small><form:errors path="numero" cssClass="errors" /></small>
				</div>

				<!-- Ajout du nom de la voie -->
				<div class=" form-group">
					<label>Nom:</label>
					<form:input path="nom" class="col-8 col-sm-7 col-md-6 col-lg-5 col-xl-4 form-control"
						placeholder="Entrer le nom de la voie" />
					<small><form:errors path="nom" cssClass="errors" /></small>
				</div>

				<!-- Bouton ajouter la voie -->
				<div class="form-group">
					<div class="col-md-4">
						<button id="addVoie" type="submit" name=addVoie
							class="btn btn-info">Ajouter la voie</button>
					</div>
				</div>

			</form:form>


		</c:if>

		<c:if test="${empty secteurs}">
			<div class="col-md-5 alert alert-danger text-center" role="alert">
				<c:out value="Aucun secteur n'est répertorié sur ce site"></c:out>
			</div>
		</c:if>
	</c:if>
</c:if>

<!-- Display Add Voie error messages -->
<c:if test="${!empty messageErrorVoie }">
	<div class="col-md-5 alert alert-danger text-center" role="alert">
		<c:out value="${messageErrorVoie}"></c:out>
	</div>
</c:if>

<!-- Display add voie success messages -->
<c:if test="${!empty messageSuccessVoie }">
	<div class="col-md-5 alert alert-success text-center" role="alert">
		<c:out value="${messageSuccessVoie}"></c:out>
	</div>
</c:if>