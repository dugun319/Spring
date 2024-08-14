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
		<h2>Information Of Member</h2>
		<form action="<%=request.getContextPath()%>/memberJpa/memberUpdate" method="get">
			<input type="hidden" name="id" 			value="${member.id }"/>
			<input type="hidden" name="password" 	value="${member.password }"/>
			
			<table>
			
				<tr>
					<th>ID</th>
					<td>${member.id }</td>
				</tr>
				
				<tr>
					<th>NAME</th>
					<td>
						<input type="text" name="name" required="required" value="${member.name }"/>
					</td>
				</tr>

				<tr>
					<td colspan="5">
						<input type="submit" value="Confirm"> 
					</td>		
				</tr>
								
			</table>		
		</form>	
	</body>
</html>