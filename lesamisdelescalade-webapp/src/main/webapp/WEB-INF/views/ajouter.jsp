<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>

<title>Home</title>
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


		<h1>Ajouter un lieu de grimpe</h1>
		<hr>



		<div class="form-group">

			<jsp:include page="ajouterSite.jsp"></jsp:include>

			<hr>
			<!-- Ajouter un secteur ------------------------------------------------------------------------------------------------>

			<jsp:include page="ajouterSecteur.jsp"></jsp:include>



			<hr>
			<!-- Ajouter une voie ------------------------------------------------------------------------------------------------>

			<jsp:include page="ajouterVoie.jsp"></jsp:include>
			
			<hr>
			<!-- Ajouter une longueur ------------------------------------------------------------------------------------------------>

			<jsp:include page="ajouterLongueur.jsp"></jsp:include>

			
		</div>
	</div>
</body>
</html>
