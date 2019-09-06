<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<h1>Site</h1>

		<div class="row justify-content-start my-xl-3">
			<div class=col-2>
				<img
					src="${pageContext.request.contextPath}/resources/pictures/climbing-icon.jpg"
					class="img-fluid" alt="symbole montagne">
			</div>
			<div class="col-9">

				<h2>
					<c:out value="${site.nom }"></c:out>
				</h2>
				<p class="text-justify"><c:out value="${site.description }"></c:out></p>
			</div>
		</div>

		<h2>Secteurs:</h2>

		<c:forEach items="${secteurs }" var="secteur">
			<div class="row my-xl-5 shadow-lg p-3 mb-5 bg-white rounded">
				<div class="col">
					<div class="row justify-content-between">
						<div class="col">
							<h3>${secteur.nom }</h3>
						</div>
						<div class="col-2 ">
							<a class="btn btn-info" data-toggle="collapse"
								href="#collapseTable${secteur.id }" role="button"
								aria-expanded="false"
								aria-controls="collapseTable${secteur.id }"> Voir plus </a>
						</div>
					</div>
					<div class="row">
						<div class="col-3">Nombre de voies: ${secteur.voiesCount }</div>
						<div class="col-3">Cotation min: ${secteur.cotationMin }</div>
						<div class="col-3">Cotation max: ${secteur.cotationMax }</div>
					</div>
					<div class="collapse" id="collapseTable${secteur.id }">
						<div class="row ">
							<div class="col">
								<table class="table ">
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
															L${longueur.numero}: ${longueur.cotation }
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
