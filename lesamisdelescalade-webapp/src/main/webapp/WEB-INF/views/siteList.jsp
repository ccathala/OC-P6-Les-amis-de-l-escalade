<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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


		<c:forEach items="${sites }" var="site">
			<div class="card mb-3">
				<div class="row no-gutters">
					<div class="col-md-4">
						<img src="${pageContext.request.contextPath}/resources/pictures/climbing-icon.jpg" class="card-img" alt="symbole montagne">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title"><c:out value="${site.nom }"></c:out></h5>
							<p class="card-text"><c:out value="${site.description }"></c:out></p>
							<a class="btn btn-info"
									href="<c:url value="/site/${site.id }"></c:url>">En savoir plus </a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


	</div>
</body>
</html>