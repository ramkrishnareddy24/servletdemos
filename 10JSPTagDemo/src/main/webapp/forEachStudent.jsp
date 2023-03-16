<%@ page import="java.util.*, com.jsp.tagdemo.Student" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
List<Student> students = new ArrayList<>();
students.add(new Student("Mary", "Jones", false));
students.add(new Student("Snow", "Jhon", true));
pageContext.setAttribute("mystudents", students);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<table border="2">
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Discount</th>

		</tr>
		<c:forEach var="student" items="${mystudents }">
			<tr>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>
					<c:if test="${student.above90Percent }">
					Yes
					</c:if>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>