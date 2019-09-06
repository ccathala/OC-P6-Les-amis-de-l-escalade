<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Ajouter une voie</h2>

			<!-- Choix département -->
			<form action="processChooseDepartementVoie" method="GET">

				<!-- Selectionner le département  -->
				<div class="form-group">
					<label>Département:</label> <select name="departementIdVoie">
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
				<form action="processChooseSiteVoie" method="GET">

					<!-- Selectionner le site -->
					<div class="form-group">
						<label>Site:</label> <select name="siteIdVoie">
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
				</form>

				<c:if test="${!empty siteIdVoie}">

					<!-- Formulaire ajouter une voie -->
					<form:form action="processAddVoie" method="POST"
						modelAttribute="voie">

						<!-- Selectionner le secteur  -->
						<div class="form-group">
							<label>Secteur:</label>
							<form:select path="secteur_id">
								<c:forEach items="${secteurs}" var="secteur">
									<option value="${secteur.id }">${secteur.nom}</option>
								</c:forEach>
							</form:select>
						</div>

						<!-- Ajout du numéro de la voie  -->
						<div class="form-group">
							<label>Numéro:</label>
							<form:input path="numero" cssClass="form-control" />
							<small><form:errors path="numero" cssClass="errors" /></small>
						</div>

						<!-- Ajout du nom de la voie -->
						<div class="form-group">
							<label>Nom:</label>
							<form:input path="nom" cssClass="form-control"
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

				</c:if>
			</c:if>