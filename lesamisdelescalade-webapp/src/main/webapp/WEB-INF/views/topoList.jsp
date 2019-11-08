<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
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

	<!-- Modal  Topos disponible-->
	<div class="modal fade" id="reservationModal" tabindex="-1"
		role="dialog" aria-labelledby="reservationModalModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="reservationModalModalLabel">Réserver un topo</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<h5>Propriétaires:</h5>
					<hr>
					<c:forEach items="${liste_topos_disponibles}" var="topo_disponible">
						<div class="row mb-3 topoOwner topoId_${ topo_disponible.topo_id}">
							<div class="col-9 text-center">
								<c:out value="${topo_disponible.possesseur_nom}"></c:out>
							</div>
							<div class="col-3">
								<a class="btn btn-primary"
									href='<c:url value="/processTopoReservationRequest/${topo_disponible.topo_id}/${topo_disponible.possesseur_id}/${sessionUtilisateur.id}"></c:url>'
									role="button">Réserver</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	

	<div class="container d-flex flex-column">
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<div style="min-height:500px;">
		
		<div class="row justify-content-center">
		

		<!-- Display login Success message -->
		<c:if test="${!empty messageReservationSuccess }">
			<div class="col-10 my-2 alert alert-success text-center" role="alert">
				<c:out value="${messageReservationSuccess}"></c:out>
			</div>
		</c:if>

		<!-- Display login Error message -->
		<c:if test="${!empty messageReservationError }">
			<div class="col-10 my-2 alert alert-danger text-center" role="alert">
				<c:out value="${messageReservationError}"></c:out>
			</div>
		</c:if>
		
		</div>

		<h1>Liste des topos:</h1>

		<div class="table-responsive ">
		<table class="table table-bordered" style="min-width:992px;">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Code postal</th>
					<th scope="col">Département</th>
					<th scope="col">Nom</th>
					<th scope="col">Site</th>
					<th scope="col">Description</th>
					<th scope="col">Date de parution</th>
					<th scope="col">Disponibilité</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${liste_topos }" var="topo">
					<tr>
						<td>${topo.code_postal }</td>
						<td>${topo.departement }</td>
						<td>${topo.topo_nom}</td>
						<td>${topo.site }</td>
						<td>${topo.description }</td>
						<td>${topo.dateParution }</td>
						<td class="text-center"><c:set var="topoAvalaibleForThisTopoId" value="false"></c:set>
							<c:if test="${avalaibleTopoIdList.contains(topo.topo_id) }">
								<c:set var="topoAvalaibleForThisTopoId" value="true"></c:set>
							</c:if> <c:if test="${topoAvalaibleForThisTopoId}">
								<button type="button" id="${topo.topo_id }_topoId"
									name="singlebutton"
									class="consultAvalaibleTopo btn btn-success"
									data-toggle="modal" data-target="#reservationModal">Voir</button>
							</c:if> <c:if test="${!topoAvalaibleForThisTopoId}">
								<p class="text-danger">Non disponible</p>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		</div>
	
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

	<script>
		$(document).ready(function() {
			$(".consultAvalaibleTopo").click(function() {
				$(".topoOwner").hide();
				var topoId = "topoId_" + $(this).attr("id").split("_", 1);
				$("." + topoId).show();
			});
		});
	</script>


</body>
</html>
