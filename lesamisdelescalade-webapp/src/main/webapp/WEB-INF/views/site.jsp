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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/coliff/bootstrap-rfs/bootstrap-rfs.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<!-- Modal update commentaire form-->
	<div class="modal fade" id="updateCommentaireModal" tabindex="-1"
		role="dialog" aria-labelledby="updateCommentaireModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="updateCommentaireModalLabel">Modifier
						le commentaire</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form:form id="updateCommentaireForm" class="mb-0" action=""
						method="POST" modelAttribute="commentaire">
						<div class="form-group">
							<form:textarea path="texte" cssClass="form-control mt-3"
								placeholder="Modifier le commentaire" />
							<small><form:errors path="texte" cssClass="errors" /></small>
						</div>

						<form:input path="utilisateur_id" type="hidden"
							value="${sessionUtilisateur.id }" />
						<form:input path="status_id" type="hidden" value="2" />
						<form:input path="site_id" type="hidden" value="${site.id }" />
						<div class="">
							<button type="submit" class="btn btn-primary">Modifier</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal delete commentaire-->
	<div class="modal fade" id="deleteCommentaireModal" tabindex="-1"
		role="dialog" aria-labelledby="deleteCommentaireModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteCommentaireModalLabel">Supprimer
						le commentaire</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Confirmer la suppression du
					commentaire:</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<a id="deleteCommentaireButton" class="btn btn-primary" href="">Supprimer</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal modify picture -->
	<div class="modal fade" id="modifyPicture" tabindex="-1" role="dialog"
		aria-labelledby="modifyPictureLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modifyPictureLabel">Modifier la photo du site</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="processEditPicture/${site.id}" method="get">
						<div class="form-group">
							<label class="col control-label" for="textinput">Lien
								de la photo :</label>
							<div class="col-md-12">
								<input id="pictureUrl" name="pictureUrl" type="text"
									placeholder="Saisir l'url de la photo"
									class="form-control input-md">
							</div>
							<div class="col-md-12 mt-2 text-right">
								<button id="processModifyPictureButton" name="processModifyPictureButton"
									class="btn btn-primary">Modifier la photo</button>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<jsp:include page="header.jsp"></jsp:include>

		<div class="row ">
			<div class="col-12 mt-3">
				<h2>
					<c:out
						value="${site.nom } (${departements.get(site.departement_id).getCode() } - ${departements.get(site.departement_id).getNom() })"></c:out>
				</h2>
			</div>
			<div class="col-12 mb-2">
				<c:if test="${site.tag_id == 2 }">
					<span class="badge badge-info">Officiel les amis de
						l'escalade</span>
				</c:if>
			</div>

			<div class="col-12">
				<c:if test="${sessionUtilisateur.role_id == 1 }">
					<c:if test="${site.tag_id == 1 }">
						<a class="my-2 btn btn-success"
							href="<c:url value="/addTag/${site.id }"></c:url>">Taguer
							"Officiel"</a>
					</c:if>
					<c:if test="${site.tag_id == 2 }">
						<a class="my-2 btn btn-danger"
							href="<c:url value="/deleteTag/${site.id }"></c:url>">Retirer
							le tag "Officiel"</a>
					</c:if>
					<button id="modifyPictureButton" type="button" class="my-2 btn btn-info"
					data-toggle="modal" data-target="#modifyPicture">Modifier la photo</button>
					<a class="my-2 btn btn-danger"
							href="<c:url value="/site/processDeleteSitePicture/${site.id }"></c:url>">Supprimer la photo</a>
				</c:if>
				
			</div>
		</div>
		<hr>

		<div class="row justify-content-start my-xl-3">
			<div class="col">
				<c:choose>
						<c:when test="${site.picture != null && !empty site.picture }">
						<img
							src="${site.picture }"
							class="card-img" alt="photo du site <c:out value="${site.nom }"></c:out>">
						</c:when>
						<c:when test="${site.picture == null || empty site.picture }">
						<img
							src="${pageContext.request.contextPath}/resources/pictures/generic_mountain.jpg"
							class="card-img" alt="image de montagne">
						</c:when>
					</c:choose>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				<h3>Description:</h3>
				<p class="text-justify">
					<c:out value="${site.description }"></c:out>
				</p>
				<hr>
			</div>
		</div>

		<h2>Secteurs:</h2>

		<c:forEach items="${secteurs }" var="secteur">
			<div
				class="row row-secteur my-xl-5 shadow-lg py-3 mb-5 bg-white rounded">
				<div class="col">
					<div class="row justify-content-between">
						<div class="col">
							<h3>${secteur.nom }</h3>
						</div>
						<div class="col-4 col-lg-2 text-right">
							<a class="btn btn-info" data-toggle="collapse"
								href="#collapseTable${secteur.id }" role="button"
								aria-expanded="false"
								aria-controls="collapseTable${secteur.id }"> Voir plus </a>
						</div>
					</div>
					<div class="row">
						<div class="col-4 col-lg-3 text-center">Total voies:
							${secteur.voiesCount }</div>
						<div class="col-4 col-lg-3 text-center">Cotation min:
							${secteur.cotationMin }</div>
						<div class="col-4 col-lg-3 text-center">Cotation max:
							${secteur.cotationMax }</div>
					</div>
					<div class="collapse" id="collapseTable${secteur.id }">
						<div class="row ">
							<div class="col">
								<table class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>Nom</th>
											<th>Cotations</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${voies }" var="voie">
											<c:if test="${secteur.id == voie.secteur_id}">
												<tr>
													<td>${voie.numero}</td>
													<td>${voie.nom }</td>
													<td><c:forEach items="${longueurs }" var="longueur">
															<c:if test="${voie.id == longueur.voie_id }">
															<c:out value="L${longueur.numero}: ${longueur.cotation }"></c:out>
														</c:if>
														</c:forEach></td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
		</c:forEach>

		<!-- Commentaires -->

		<h3>Commentaires:</h3>

		<c:forEach items="${commentaires }" var="commentaire">
			<div class="row border border-info align-items-center mb-3">
				<div class="col-2 col-lg-1">
					<img
						src="${pageContext.request.contextPath}/resources/pictures/profile_small.png"
						class="img-fluid" alt="image profil">
				</div>
				<div class="col-10 col-lg-11">

					<div class="row justify-content-between align-items-center">
						<div class="col-auto p-0">
							<h6 class="my-1">${utilisateurs.get(commentaire.utilisateur_id) }</h6>
						</div>
						<div class="col-auto ">
							<p class="my-1">
								Posté le:
								<c:out value="${commentaire.date }"></c:out>
							</p>
						</div>
					</div>

					<div class="row">
						<div class="col bg-light text-dark mr-2 mb-1 rounded-lg border">
							<p class="mb-0" style="min-height: 75px">
								<c:choose>
									<c:when test="${commentaire.status_id !=3 }">
										<c:out value="${commentaire.texte }" escapeXml="false"></c:out>
									</c:when>
									<c:when test="${commentaire.status_id ==3 }">** Message supprimé **</c:when>
								</c:choose>

							</p>
						</div>
					</div>
					<c:if test="${sessionUtilisateur.role_id == 1 }">
						<div class="row justify-content-end mb-1">
							<c:if test="${commentaire.status_id != 3 }">
								<div class="col-auto p-0 ">
									<!-- Button trigger modal -->
									<button id="${commentaire.id }_buttonModifierCommentaire"
										type="button" class="boutonModifier btn btn-info btn-sm"
										data-toggle="modal" data-target="#updateCommentaireModal">Modifier</button>
								</div>

								<div class="col-auto p-0 ">
									<!-- Button trigger modal -->
									<button id="${commentaire.id }_buttonSupprimerCommentaire"
										type="button" class="boutonSupprimer btn btn-info btn-sm"
										data-toggle="modal" data-target="#deleteCommentaireModal">Supprimer</button>
								</div>
							</c:if>
						</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
		<c:if test="${!empty sessionUtilisateur.email }">
			<form:form action="processAddCommentaire" method="POST"
				modelAttribute="commentaire">
				<div class="form-group">
					<form:textarea path="texte" cssClass="form-control mt-3"
						placeholder="Saisir un commentaire" />
					<small><form:errors path="texte" cssClass="errors" /></small>
				</div>

				<form:input path="utilisateur_id" type="hidden"
					value="${sessionUtilisateur.id }" />
				<form:input path="status_id" type="hidden" value="1" />
				<form:input path="site_id" type="hidden" value="${site.id }" />


				<div class="form-group">
					<div class="col-md-4">
						<button id="addCommentairebutton" type="submit"
							name="addCommentairebutton" class="btn btn-info">Poster
							le commentaire</button>
					</div>
				</div>

			</form:form>
		</c:if>

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
		$(document).ready(
				function() {
					$(".boutonModifier").click(
							function() {
								var comment_id = $(this).attr("id").split("_",
										1);
								$("#updateCommentaireForm").attr(
										"action",
										"processUpdateCommentaire/"
												+ comment_id);
							});
					$(".boutonSupprimer").click(
							function() {
								var comment_id = $(this).attr("id").split("_",
										1);
								$("#deleteCommentaireButton").attr(
										"href",
										"processDeleteCommentaire/"
												+ "${site.id}" + "/"
												+ comment_id);
							});
				});
	</script>

</body>
</html>
