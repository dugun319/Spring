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
		<h1>Update Information</h1>
		<form action="/updateEmp" method="post">
			<input type="hidden" name="empno" value="${detailEmp.empno }">
			<table>
				<tr>
					<th>Emp No.</th>
					<td>${detailEmp.empno }</td>
				</tr>
				
				<tr>				
					<th>Name</th>
					<td><input type="text"	 name="ename"	required="required"	value="${detailEmp.ename }"/></td>
				</tr>
				
				<tr>				
					<th>Job</th>
					<td><input type="text"	 name="job"	required="required"	value="${detailEmp.job }"/></td>
				</tr>
				
				<tr>				
					<th>Salary</th>
					<td><input type="number" name="sal"	required="required"	value="${detailEmp.sal }"/></td>
				</tr>
				
				<tr>				
					<th>HireDate</th>
					<td><input type="date"	 name="hiredate"					value="${detailEmp.hiredate }"/></td>
				</tr>
				
				<tr>				
					<th>Compensation</th>
					<td><input type="number" name="comm"	required="required"	value="${detailEmp.comm }"/></td>
				</tr>
				
				<tr>				
					<th>Manager</th>
					<td><input type="text"	 name="mgr"						value="${detailEmp.mgr }"/></td>
				</tr>
				
				<tr>				
					<th>Dept Code</th>
					<td><input type="text"	 name="deptno"	required="required"	value="${detailEmp.deptno }"/></td>
				</tr>
				
				<tr>
					<td colspan="5">
						<input type="submit" value="Confirm"/>		
					</td>
				</tr>	
									
			</table>		
		</form>
	</body>
</html>