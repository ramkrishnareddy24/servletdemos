<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>
</head>
<body>
	<form action="updateStudentServlet" method="POST">
	
	
		<input type="hidden" name="studentId" value="${student.id }"/>
	
		<table>
			<tr>
				<td>FirstName</td>
				<td><input name="firstName" value="${student.firstName}"/></td>
			</tr>
			
			<tr>
				<td>LastName</td>
				<td><input name="lastName" value="${student.lastName}"/></td>
			</tr>
			
			<tr>
				<td>Email</td>
				<td><input name="email" value="${student.email}"/></td>
			</tr>
			
			
			<tr>
				<td></td>
				<td><input type="submit" value="Save"/></td>
			</tr>
		</table>
	</form>
	<a href="index.html">Back to list</a>
</body>
</html>