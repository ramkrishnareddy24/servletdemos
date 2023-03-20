<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addStudentServlet" method="POST">
	
	
	
		<table>
			<tr>
				<td>FirstName</td>
				<td><input name="firstName" /></td>
			</tr>
			
			<tr>
				<td>LastName</td>
				<td><input name="lastName" /></td>
			</tr>
			
			<tr>
				<td>Email</td>
				<td><input name="email" /></td>
			</tr>
			
			
			<tr>
				<td></td>
				<td><input type="submit" value="Add"/></td>
			</tr>
		</table>
	</form>
	<a href="index.html">Back to list</a>
</body>
</html>