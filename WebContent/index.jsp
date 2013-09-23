<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/application-style.css" />
</head>
<body>
	<br>
	<fmt:setBundle basename="com.epam.xml.sychou.resource.Content" />
	<center>
		<h1>
			<fmt:message key="message.welcome" />
		</h1>
		<br> <br>
		<table>
			<tr>
				<td><fmt:message key="message.choose.parser" /></td>
			</tr>
			<tr>
				<td><a href="Parse?parserType=SAX" ><fmt:message
							key="link.sax" /></a></td>
			</tr>
			<tr>
				<td><a href="Parse?parserType=DOM"><fmt:message
							key="link.dom" /></a></td>
			</tr>
			<tr>
				<td><a href="Parse?parserType=StAX"><fmt:message
							key="link.stax" /></a></td>
			</tr>
		</table>
	</center>
</body>
</html>