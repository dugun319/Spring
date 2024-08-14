<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h2>JPA Member List</h2><a href="/memberJpa/new">NEW Member</a>		
		<c:set var="num" value="1"></c:set>
		
		<h4><c:if test="${message != null }">${message}</c:if></h4>	
		<table>
			
			<tr>
				<th>Number</th>
				<th>ID</th>
				<th>NAME</th>
				<th>PASSWORD</th>
				<th>HIRE_DATE</th>
			</tr>
			
			<c:forEach var="member" items="${memberList}" >
			<tr>
				<td>${num }</td>
				<td>${member.id }</td>
				<td>
					<a href="/memberJpa/memberUpdateForm?id=${member.id }">${member.name }</a>
				</td>				
				<td>${member.password }</td>
				<td>${member.reg_date }</td>			
			</tr>
			<c:set var="num" value="${num + 1 }"></c:set>			
			</c:forEach>
								
		</table>
		
	</body>
</html>