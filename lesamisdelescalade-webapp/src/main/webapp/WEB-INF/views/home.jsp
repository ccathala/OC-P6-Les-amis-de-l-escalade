<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>

<title>Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<P>${webapp}= ok</P>
	<P>${business}= ok</P>
	<P>${consumer}= ok</P>
	
	<div id="ListUtilisateurs">
		<table class="table1">
			<tr>
				<th>ID</th>
				<th>NOM</th>
				<th>EMAIL</th>
				<th>PASSWORD</th>
				<th>ROLE_ID</th>
			</tr>
			<c:forEach items="${utilisateurs}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.nom}</td>
					<td>${u.email}</td>
					<td>${u.password}</td>
					<td>${u.role_id}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
