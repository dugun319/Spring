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
		<h2>Member List</h2>
		<h4>Access ID : ${ID}</h4>
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>PASSWORD</th>
					<th>REGISTRATION DATE</th>
				</tr>
				
				<c:forEach var="listMember" items="${listMember}" >
				<tr>
					<td>${listMember.id }</td>
					<td>${listMember.name }</td>				
					<td>${listMember.password }</td>
					<td>${listMember.reg_date }</td>	
				</tr>		
				</c:forEach>	
							
			</table>
		
	</body>
</html>