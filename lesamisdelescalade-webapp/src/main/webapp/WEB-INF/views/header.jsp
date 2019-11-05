<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<!-- 	<div class="container"> -->

	<div class="row">
		<div class="col-12">
			<img
				src="${pageContext.request.contextPath}/resources/pictures/banniere_1140.jpg"
				class="img-fluid" alt="Responsive image">
		</div>
	</div>

	<div class="row justify-content-between cadre-navbar bg-dark">
		<div class="col-xl-7 col-lg-5 col-5 pl-0">
			<nav class="navbar navbar-expand-xl navbar-dark bg-dark">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarTogglerDemo01"
					aria-controls="navbarTogglerDemo01" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active"><a class="nav-link"
							href="<c:url value="/"/>">Acceuil <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/siteList"/>">Sites</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/site/ajouter"/>">Ajouter Spot</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/addTopo"/>">Ajouter un Topo</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/topoList"/>">Liste des topos</a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="col-xl-5 col-lg-7 col-7 pr-0">
			<div
				class="row justify-content-end py-2 bouton-navbar bg-dark text-right">
				<div class="col pr-0">
				<c:choose>
					<c:when test="${empty sessionUtilisateur.nom  }">
<!-- 						<div class="col-12 py-2 pr-0"> -->
							<a class="btn btn-info" href="<c:url value="/login"/>"
								role="button">Se connecter</a> <a class="btn btn-info"
								href="<c:url value="/registration"/>" role="button">S'inscrire</a>
<!-- 						</div> -->
					</c:when>
					<c:when test="${!empty sessionUtilisateur.nom }">
<!-- 					<div class="col-12 py-2 pr-0 text-white"> -->
<%-- 					<span class="col-lg-auto col-12 text-center">Bienvenue <c:out value="${sessionUtilisateur.nom }"></c:out></span> --%>
					<span class="text-white">Bienvenue <c:out value="${sessionUtilisateur.nom }"></c:out></span>
					
<!-- 						<a class="col-lg-3 col-sm-7 col-9  mb-1 btn btn-info" -->
						<a class="btn btn-info"
								href="<c:url value="/goToAccountPage"></c:url>" role="button">Profil
								</a> 
<!-- 								<a class="col-lg-4 col-sm-7 col-9  mb-1 btn btn-info" -->
								<a class="btn btn-info"
								href="<c:url value="/logout"/>" role="button">Déconnexion</a>
<!-- 					</div> -->
						
					
					
					
<!-- 						<div class="col-3 py-2"> -->
<!-- 							<p class="mb-0 text-white "> -->
<!-- 								Bienvenue -->
<%-- 								<c:out value="${sessionUtilisateur.nom }"></c:out> --%>
<!-- 							</p> -->
<!-- 						</div> -->
<!-- 						<div class="col-9 py-2"> -->
<!-- 							<a class="btn btn-info" -->
<%-- 								href="<c:url value="/goToAccountPage"></c:url>" role="button">Mon --%>
<!-- 								Compte</a> <a class="btn btn-info" -->
<%-- 								href="<c:url value="/logout"/>" role="button">Se déconnecter</a> --%>
<!-- 						</div> -->




					</c:when>
				</c:choose>
				</div>
			</div>
		</div>

	</div>

	<!-- 	<div class="row justify-content-between"> -->
	<!-- 		<div class="col-6"> -->
	<!-- 			<nav class="navbar navbar-expand-xl navbar-dark bg-dark"> -->
	<!-- 				<button class="navbar-toggler" type="button" data-toggle="collapse" -->
	<!-- 					data-target="#navbarTogglerDemo01" -->
	<!-- 					aria-controls="navbarTogglerDemo01" aria-expanded="false" -->
	<!-- 					aria-label="Toggle navigation"> -->
	<!-- 					<span class="navbar-toggler-icon"></span> -->
	<!-- 				</button> -->
	<!-- 				<div class="collapse navbar-collapse" id="navbarTogglerDemo01"> -->
	<!-- 					<ul class="navbar-nav mr-auto"> -->
	<!-- 						<li class="nav-item active"><a class="nav-link" -->
	<%-- 							href="<c:url value="/"/>">Acceuil <span class="sr-only">(current)</span> --%>
	<!-- 						</a></li> -->
	<!-- 						<li class="nav-item"><a class="nav-link" -->
	<%-- 							href="<c:url value="/siteList"/>">Sites</a></li> --%>
	<!-- 						<li class="nav-item"><a class="nav-link" -->
	<%-- 							href="<c:url value="/site/ajouter"/>">Ajouter Spot</a></li> --%>
	<!-- 						<li class="nav-item"><a class="nav-link" -->
	<%-- 							href="<c:url value="/addTopo"/>">Ajouter un Topo</a></li> --%>
	<!-- 						<li class="nav-item"><a class="nav-link" -->
	<%-- 							href="<c:url value="/topoList"/>">Liste des topos</a></li> --%>
	<!-- 					</ul> -->
	<!-- 				</div> -->
	<!-- 			</nav> -->

	<!-- 		</div> -->
	<!-- 		<div class="col-6 "> -->
	<!-- 			<div class="row bouton-navbar bg-dark text-right "> -->

	<%-- 				<c:choose> --%>
	<%-- 					<c:when test="${empty sessionUtilisateur.nom  }"> --%>
	<!-- 					<div class="col-12 p-2"> -->
	<%-- 					<a class="btn btn-info" href="<c:url value="/login"/>" --%>
	<!-- 							role="button">Se connecter</a> -->
	<%-- 						<a class="btn btn-info" href="<c:url value="/registration"/>" --%>
	<!-- 							role="button">S'inscrire</a> -->
	<!-- 					</div> -->

	<%-- 					</c:when> --%>
	<%-- 					<c:when test="${!empty sessionUtilisateur.nom }"> --%>
	<!-- 						<div class=col-auto> -->
	<!-- 							<p class="text-white mb-0"> -->
	<!-- 								Bienvenue -->
	<%-- 								<c:out value="${sessionUtilisateur.nom }"></c:out> --%>
	<!-- 							</p> -->
	<!-- 						</div> -->

	<!-- 						<a class="btn btn-info" -->
	<%-- 							href="<c:url value="/goToAccountPage"></c:url>" role="button">Mon --%>
	<!-- 							Compte</a> -->
	<%-- 						<a class="btn btn-info" href="<c:url value="/logout"/>" --%>
	<!-- 							role="button">Se déconnecter</a> -->
	<%-- 					</c:when> --%>
	<%-- 				</c:choose> --%>
	<!-- 			</div> -->

	<!-- 		</div> -->
	<!-- 	</div> -->



	<!-- 	</div> -->
</header>