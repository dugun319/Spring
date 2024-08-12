<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- VALIDATION CHECK  -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chk() {
		if(!frm.empno.value) {
			alert("Enter Employee Number");
			frm.empno.focus();
			return false;
		} else {
			location.href="/confirm2?empno="+frm.empno.value;
		}
		
	}
</script>
</head>
	<body>
		<h1>The Information Of Employee(For Validation Check)</h1>
		<h3><c:if test="${msg != null }">${msg }</c:if></h3>
		<form:form action="/writeEmp2" method="post" name="frm" modelAttribute="emp">
			<table>
			<tr>
				<th>Emp No.</th>
				<td>
					<input type="number"	name="empno" 	required="required" 	maxlength="4" 	value="${empno }"/>
					<input type="button"	value="중복확인"	onclick="chk()">
				</td>
			</tr>
			
			<tr>				
				<th>Name</th>
				<td>
					<input type="text"		name="ename"/>
					<form:errors path="ename"/>
				</td>
			</tr>
			
			<tr>				
				<th>Job</th>
				<td><input type="text"		name="job"	 	required="required"/></td>
			</tr>
			
			<tr>				
				<th>Salary</th>
				<td><input type="number"	name="sal"	 	required="required"/></td>
			</tr>
			
			<tr>				
				<th>HireDate</th>
				<td><input type="date"		name="hiredate"	required="required"/></td>
			</tr>
			
			<tr>				
				<th>Compensation</th>
				<td><input type="number"	name="comm" 	required="required"/></td>
			</tr>
			
			<tr>				
				<th>Manager</th>
				<td>	
					<select name="mgr">
						<c:forEach var="emp" items="${empMrgList }">
							<option value="${emp.empno }">${emp.ename }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>				
				<th>Dept Code</th>
				<td>
					<select name="deptno">
						<c:forEach var="dept" items="${deptList }">
							<option value="${dept.deptno }">${dept.dname }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="5">
					<input type="submit" value="Confirm"/>					
				</td>
			</tr>	
								
		</table>
		
		
		</form:form>
	</body>
</html>