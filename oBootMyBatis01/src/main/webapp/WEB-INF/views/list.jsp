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
		<h1>회원관리</h1>
		
		<h3>Total Number of Employee : ${totalEmp }</h3>
		<c:set var="num" value="${page.total - page.start + 1 }"></c:set>
		
		<table>
			<tr>
				<th>Number</th>
				<th>Emp No.</th>
				<th>Name</th>
				<th>Job</th>
				<th>Salary</th>
			</tr>
			<c:forEach var="emp" items="${listEmp}" >
			<tr>
				<td>${num }</td>
				<td>${emp.empno }</td>
				<td>
					<a href="/detailEmp?empno=${emp.empno }">${emp.ename }</a>
				</td>				
				<td>${emp.job }</td>
				<td>${emp.sal }</td>			
			</tr>
			<c:set var="num" value="${num - 1 }"></c:set>			
			</c:forEach>					
		</table>
		
		<c:if test="${page.startPage > page.pageBlock }">
			<a href="/listEmp?currentPage=${page.startPage - page.pageBlock }">[Previous]</a>
		</c:if>
		
		<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
			<a href="/listEmp?currentPage=${i }">[${i }]</a>
		</c:forEach>
		
		<c:if test="${page.startPage < page.pageBlock }">
			<a href="/listEmp?currentPage=${page.startPage + page.pageBlock }">[Next]</a>
		</c:if>
				
	</body>
</html>