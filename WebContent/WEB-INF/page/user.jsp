<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show User</title>
</head>
<body>
	<form action="saveUser.html" method="post">
		<label>${msg}</label> Email:<input type="text" name="email">
		Name: <input type="text" name="name"> <input type="submit"
			value="save">
		<div>
			<table>
				<tr>
					<td>id</td>
					<td>name</td>
					<td>Email</td>
				</tr>
				<c:forEach var="v" items="${ userList}">
					<tr>
						<td>${v.id }</td>
						<td>${v.name }</td>
						<td>${v.email }</td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</form>
</body>
</html>