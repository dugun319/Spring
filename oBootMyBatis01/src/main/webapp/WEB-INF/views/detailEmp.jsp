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
		<h1>Employee Information</h1>
		<table>
			<tr>
				<th>Emp No.</th>
				<td>${detailEmp.empno }</td>
			</tr>
			
			<tr>				
				<th>Name</th>
				<td>${detailEmp.ename }</td>
			</tr>
			
			<tr>				
				<th>Job</th>
				<td>${detailEmp.job }</td>
			</tr>
			
			<tr>				
				<th>Salary</th>
				<td>${detailEmp.sal }</td>
			</tr>
			
			<tr>				
				<th>HireDate</th>
				<td>${detailEmp.hiredate }</td>
			</tr>
			
			<tr>				
				<th>Compensation</th>
				<td>${detailEmp.comm }</td>
			</tr>
			
			<tr>				
				<th>Manager</th>
				<td>${detailEmp.mgr }</td>
			</tr>
			
			<tr>				
				<th>Dept Code</th>
				<td>${detailEmp.deptno }</td>
			</tr>
			
			<tr>
				<td colspan="5">
					<input type="button" value="LIST"	onclick="location.href='/listEmp'"/> &nbsp;
					<input type="button" value="MODIFY"	onclick="location.href='/updateFormEmp?empno=${detailEmp.empno }'"/> &nbsp; 
					<input type="button" value="DELETE"	onclick="location.href='/deleteEmp?empno=${detailEmp.empno }'"/> 					
				</td>
			</tr>	
								
		</table>
	</body>
</html>