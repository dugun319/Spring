<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h1>Result of e-Mail Transportation</h1>
		<c:if test="${check == 1 }">e-Mail Transportation is completed</c:if>
		<c:if test="${check != 1 }">e-Mail Transportation is failed</c:if>
		<c:if test="${check ==  null}">e-Mail Transportation is failed</c:if>
	</body>
</html>