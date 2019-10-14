<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>

<title>Mon compte</title>
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

		<h2>Topos:</h2>
		<hr>

		<h3>Déclarer un topo en ma possession:</h3>

		<form action="" method="GET">
			<div class="form-group row">
				<label class="col-md-auto col-form-label" for="departementId">Département:</label>
				<div class="col-md-4">
					<select id="departementId" name="departementId"
						class="form-control">
						<option value="0"
							<c:if test="${empty departementId }">selected</c:if>>Sélectionner
							le département</option>
						<c:forEach items="${departements }" var="departement">
							<option value="${departement.id }"
								<c:if test="${departement.id == departementId }">selected</c:if>><c:out
									value='${departement.code} - ${departement.nom }'></c:out></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-4">
					<button id="departementValidationButton"
						name="departementValidationButton" class="btn btn-primary"
						type="submit">Valider</button>
				</div>
			</div>
		</form>

		<c:if test="${!empty topos }">

			<form:form action="processAddOwnedTopo" method="POST"
				modelAttribute="newPossesseurTopo">

				<div class="form-group row">
					<label class="col-md-auto col-form-label" for="topoId">Topo:</label>
					<div class="col-md-4">
						<form:select path="topo_id" class="form-control">
							<option value="0" selected>Sélectionner le topo</option>
							<c:forEach items="${topos }" var="topo">
								<option value="${topo.topo_id }"><c:out
										value='${topo.topo_nom} - ${topo.site_nom } - ${topo.dateParution }'></c:out></option>
							</c:forEach>
						</form:select>

						<form:input path="utilisateur_id" type="hidden"
							value="${sessionUtilisateur.id }" />
					</div>
					<div class="col-md-4">
						<button id="topoValidationButton" name="topoValidationButton"
							class="btn btn-success" type="submit">Ajouter le topo</button>
					</div>
				</div>
			</form:form>

		</c:if>

		<!-- Display add possesseurTopp Success message -->
		<c:if test="${!empty messageAddPossesseurTopoSuccessfully }">
			<div class="col-md-12 alert alert-success text-center" role="alert">
				<c:out value="${messageAddPossesseurTopoSuccessfully}"></c:out>
			</div>
		</c:if>

		<!-- Display add possesseurTopp Error message -->
		<c:if test="${!empty messageAddPossesseurTopoError }">
			<div class="col-md-12 alert alert-danger text-center" role="alert">
				<c:out value="${messageAddPossesseurTopoError}"></c:out>
			</div>
		</c:if>

		<c:if test="${empty topos && !empty departementId}">

			<!-- Display Error message - no topo recorded -->

			<div class="col-md-12 alert alert-danger text-center" role="alert">
				<c:out value="Aucun topo n'est répertorié pour ce site."></c:out>
			</div>
		</c:if>



		<hr>
		<h3>Mes topos:</h3>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Nom du topo</th>
					<th scope="col">Site</th>
					<th scope="col">Département</th>
					<th scope="col">Date de parution</th>
					<th scope="col">Disponibilité</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${myTopos }" var="myTopo">
					<tr>
						<td><c:out value="${myTopo.topo_nom }"></c:out></td>
						<td><c:out value="${myTopo.site }"></c:out></td>
						<td><c:out value="${myTopo.departement }"></c:out></td>
						<td><c:out value="${myTopo.dateParution }"></c:out></td>
						<td><c:choose>
								<c:when test="${myTopo.disponible}">
									<p class="text-success">Disponible</p>
								</c:when>
								<c:when test="${!myTopo.disponible}">
									<p class="text-danger">Non disponible</p>
								</c:when>
							</c:choose>
						<td><c:choose>
								<c:when test="${myTopo.disponible}">
									<a
										href="<c:url value="/setTopoAvailability/${myTopo.topo_id}/${sessionUtilisateur.id }/${!myTopo.disponible}"></c:url>"
										title="Retirer le topo de la liste de prêt"><span
										style="color: red;"><i class="fas fa-eye-slash fa-lg"></i></span></a>
								</c:when>
								<c:when test="${!myTopo.disponible}">
									<a
										href="<c:url value="/setTopoAvailability/${myTopo.topo_id}/${sessionUtilisateur.id }/${!myTopo.disponible}"></c:url>"
										title="Ajouter le topo à la liste de prêt"><span
										style="color: DodgerBlue;"><i class="fas fa-eye fa-lg"></i></span></a>
								</c:when>
							</c:choose> <a
							href="<c:url value="/deleteOwnedTopo/${myTopo.topo_id}/${sessionUtilisateur.id }"></c:url>"
							title="Supprimer le topo"><span style="color: black;"><i
									class="fas fa-trash-alt fa-lg"></i></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<hr>

		<h3>Demandes de prêt reçues:</h3>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Nom du demandeur</th>
					<th scope="col">Nom du topo</th>
					<th scope="col">Site</th>
					<th scope="col">Département</th>
					<th scope="col">Date de parution</th>
					<th scope="col">Status de la demande</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${receivedReservationRequest}" var="request">
					<tr>
						<td>
							<p>
								<c:out value="${request.demandeur_nom }"></c:out>
							</p> <c:if test="${request.status_id ==2 }">
								<p>
									<c:out value="${request.demandeur_email }"></c:out>
								</p>
							</c:if>
						</td>
						<td><c:out value="${request.reservation_topo_nom }"></c:out></td>
						<td><c:out value="${request.site_nom }"></c:out></td>
						<td><c:out value="${request.departement }"></c:out></td>
						<td><c:out value="${request.dateParution }"></c:out></td>
						<td><c:out value="${request.status }"></c:out></td>
						<td><c:choose>
								<c:when test="${request.status_id == 1 }">
									<c:if test="${request.disponible == true }">
										<a
											href="<c:url value="/processUpdateReservationRequestStatusToAccepted/${request.reservation_id }/${request.possesseur_id }"></c:url>"
											title="Accepter la demande de réservation"><span
											style="color: LimeGreen;"><i
												class="far fa-check-circle fa-lg"></i></span></a>
									</c:if>
									<a
										href="<c:url value="/processUpdateReservationRequestStatusToRefused/${request.reservation_id }/${request.possesseur_id }"></c:url>"
										title="Refuser la demande de réservation"><span
										style="color: red;"><i class="fas fa-ban fa-lg"></i></span></a>
								</c:when>
								<c:when test="${request.status_id == 2 }">
									<a
										href="<c:url value="/processUpdateReservationRequestStatusToEnded/${request.reservation_id }/${request.possesseur_id }"></c:url>"
										title="Confirmer le retour du topo"><span
										style="color: blue;"><i
											class="far fa-arrow-alt-circle-left fa-2x"></i></span></a>
								</c:when>
								<c:when
									test="${request.status_id == 5  || request.status_id == 4 || request.status_id == 3}">
									<a
										href="<c:url value="/processSetReservationVisibilityForOwnerToFalse/${request.reservation_id }/${request.possesseur_id }"></c:url>"
										title="Effacer la réservation de la liste"><span
										style="color: black;"><i class="fas fa-trash-alt fa-lg"></i></span></a>
								</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
		<h3>Demandes de prêt envoyées:</h3>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Nom du propriétaire</th>
					<th scope="col">Nom du topo</th>
					<th scope="col">Site</th>
					<th scope="col">Département</th>
					<th scope="col">Date de parution</th>
					<th scope="col">Status de la demande</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sentReservationRequest }" var="request">
					<tr>
						<td>
							<p>
								<c:out value="${request.possesseur_nom }"></c:out>
							</p> <c:if test="${request.status_id == 2 }">
								<p>
									<c:out value="${request.possesseur_email }"></c:out>
								</p>
							</c:if>
						</td>
						<td><c:out value="${request.reservation_topo_nom }"></c:out></td>
						<td><c:out value="${request.site_nom }"></c:out></td>
						<td><c:out value="${request.departement }"></c:out></td>
						<td><c:out value="${request.dateParution }"></c:out></td>
						<td><c:out value="${request.status }"></c:out></td>
						<td><c:choose>
								<c:when test="${request.status_id == 1 }">
									<a
										href="<c:url value="/processUpdateReservationRequestStatusToCancelled/${request.reservation_id }/${request.demandeur_id }"></c:url>"
										title="Annuler la demande de réservation"><span
										style="color: red;"><i class="far fa-window-close fa-2x"></i></span></a>

								</c:when>

								<c:when
									test="${request.status_id == 5  || request.status_id == 4 || request.status_id == 3}">
									<a
										href="<c:url value="/processSetReservationVisibilityForRequesterToFalse/${request.reservation_id }/${request.demandeur_id }"></c:url>"
										title="Effacer la réservation de la liste"><span
										style="color: black;"><i class="fas fa-trash-alt fa-lg"></i></span></a>
								</c:when>
							</c:choose>
						<td>
					</tr>
				</c:forEach>


			</tbody>
		</table>


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
