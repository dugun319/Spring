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
		<h1>Information of Department</h1>
		<h4><a href="/mailTransport">Mail Test(SMTP)</a></h4>
	
		<table>
		
			<tr>
				<th>Employee No.</th>
				<th>Name</th>
				<th>Job</th>
				<th>Department</th>
				<th>Location</th>
			</tr>
			
			<c:forEach var="empDept" items="${listEmpDept}" >
			<tr>
				<td>${empDept.empno }</td>
				<td>${empDept.ename }</td>				
				<td>${empDept.job }</td>
				<td>${empDept.deptno }</td>
				<td>${empDept.loc }</td>			
			</tr>		
			</c:forEach>	
							
		</table>

	</body>
</html>