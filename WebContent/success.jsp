<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	Win!
	<br>
	<button onclick="location.href = 'index.jsp'">OK</button>
	<center>
		<table border="1">
			<tr>
				<td>Category</td>
				<td>Subcategory</td>
				<td>Name</td>
				<td>Provider</td>
				<td>Model</td>
				<td>Date of Issue</td>
				<td>Color</td>
				<td>Price</td>
				<td>Not in Stock</td>
				<td><c:out value="${test}"/></td>
			</tr>
		</table>
	</center>
</body>
</html>