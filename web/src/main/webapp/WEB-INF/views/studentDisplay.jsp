<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Spring MVC Hello World</title>
</head>

<body>
	<h2>All Students in System</h2>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Sex</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td>${student.age}</td>
				<td>${student.sex}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>