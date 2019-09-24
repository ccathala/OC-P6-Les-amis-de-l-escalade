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
							<li class="nav-item"><a class="nav-link" href="<c:url value="/site/1"/>">Sites</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="<c:url value="/site/ajouter"/>">Ajouter</a>
							</li>
						</ul>
						<c:choose>
							<c:when test="${sessionUtilisateur.email == null }">
								<a class="btn btn-info" href="<c:url value="/login"/>" role="button">Se connecter</a> 
								<a class="btn btn-info" href="<c:url value="/registration"/>" role="button">S'inscrire</a>
							</c:when>
							<c:when test="${sessionUtilisateur.email != null}">
								<a class="btn btn-info" href="#" role="button">Mon Compte</a>
								<a class="btn btn-info" href="<c:url value="/logout"/>" role="button">Se déconnecter</a>
							</c:when>
						</c:choose>
					</div>
				</nav>
			</div>
		</div>
	</div>
</header>