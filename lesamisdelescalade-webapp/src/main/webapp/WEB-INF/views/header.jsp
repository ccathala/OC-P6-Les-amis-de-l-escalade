<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="container">
		<div class="row" id="banniere">
			<img src="${pageContext.request.contextPath}/resources/pictures/banniere_1110.jpg" class="img-fluid"
				alt="Responsive image">
		</div>
		<div class="row">
			<div class="col">
				<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
					<div class="navbar-collapse">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link" href="<c:url value="/"/>">Acceuil
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Sites</a>
							</li>
						</ul>
						<c:choose>
							<c:when test="${empty cUtilisateur }">
								<a class="btn btn-info" href="<c:url value="/login"/>" role="button">Se connecter</a> 
								<a class="btn btn-info" href="#" role="button">S'inscrire</a>
							</c:when>
							<c:when test="${!empty cUtilisateur }">
								<a class="text-white" href="#" role="button"><c:out value="${cUtilisateur.nom }"></c:out></a>
							</c:when>
						</c:choose>
					</div>
				</nav>
			</div>
		</div>
	</div>
</header>