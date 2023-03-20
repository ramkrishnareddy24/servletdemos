<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Students</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
		</tr>
		<c:forEach var="student" items="${student_list}">
			<tr>
				<td><a href="loadStudentServlet?studentId=${student.id}">${student.id}</a></td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.email}</td>
				<td><a href="deleteStudentServlet?studentId=${student.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>