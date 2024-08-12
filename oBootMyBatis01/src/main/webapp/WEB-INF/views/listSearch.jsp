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
		<h1>Result Of Searching</h1>
		
		<form action="/listSearch">
			<select name="search">
				<option value="s_job" 	>업무조회</option>
				<option value="s_ename" >이름조회</option>
			</select>
			<input 	type="text" name="keyword" placeholder="Enter the keyword">
			<button type="submit">KEYWORD SEARCH</button> &nbsp; &nbsp; &nbsp;
			<input type="button" value="LIST"	onclick="location.href='/listEmp'"/> &nbsp;		
		</form>
		
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
					<a href="/detailEmp?empno=${emp.empno }&currentPage=1">${emp.ename }</a>
				</td>				
				<td>${emp.job }</td>
				<td>${emp.sal }</td>			
			</tr>
			<c:set var="num" value="${num - 1 }"></c:set>			
			</c:forEach>					
		</table>
		
		<c:if test="${page.startPage > page.pageBlock }">
			<a href="/listSearch?currentPage=${page.startPage - page.pageBlock }&search=${emp.search }&keyword=${emp.keyword}">[Previous]</a>
		</c:if>
		
		<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
			<a href="/listSearch?currentPage=${i }&search=${emp.search }&keyword=${emp.keyword}">[${i }]</a>
		</c:forEach>
		
		<c:if test="${page.startPage < page.pageBlock }">
			<a href="/listSearch?currentPage=${page.startPage + page.pageBlock }&search=${emp.search }&keyword=${emp.keyword}">[Next]</a>
		</c:if>
				
	</body>
</html>