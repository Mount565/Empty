<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<form action="index" method="post">
		  <label>${msg}</label>
		ID:<input type="text" name="id"> Name: <input type="text" name="name">
		<input type="submit">
		<div>
			<table>
				<tr>
					<td>id</td>
					<td>name</td>
				</tr>
				<c:forEach var="v" items="${ rsList}">
					<tr>
						<td>${v.id }</td>
						<td>${v.userName }</td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</form>

	<hr>

	<form action="sendMail.html" method="post">
	    
		<label>${msg }</label>
		<table>
			<tr>
				<td>Recipient</td>
				<td><input type="text" name="recipient">
				</td>
			</tr>
			<tr>
				<td>Subject</td>
				<td><input type="text" name="subject">
				</td>
			</tr>
			<tr>
				<td>Mail Content</td>
				<td><textarea rows="10" cols="60" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Send Mail">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>