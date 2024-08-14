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
		<h1>MyBatis JPA Member Registration</h1>
		<div class="container">
			<form action="${pageContext.request.contextPath}/memberJpa/save" method="post">
				<table>
					<tr>
						<th>ID</th>
						<td><input type="text" 		name="id"			required="required"/></td>
					</tr>
					
					<tr>
						<th>NAME</th>
						<td><input type="text" 		name="name"			placeholder="Enter your name"></td>
					</tr>
					
					<tr>
						<th>PASSWORD</th>
						<td><input type="password" 	name="password"		placeholder="Enter your password"></td>
					</tr>
				
					<tr>
						<td colspan="2">
						<input type="submit" value="Registration">
						</td>
					</tr>				
				</table>
				<h3><a href="/members">JPA Member List</a></h3>			
			</form>		
		</div>
	</body>
</html>