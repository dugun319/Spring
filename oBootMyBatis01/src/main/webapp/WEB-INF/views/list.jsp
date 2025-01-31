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
		
		<h4><a href="/writeFormEmp1">WriteFomrEmp1 Input</a></h4>
		<h4><a href="/writeFormEmp2">WriteFomrEmp2 Input(Validation)</a></h4>
		
		<h3>Total Number of Employee : ${totalEmp }</h3>
		
		<h5>updateCnt 수정시 전달 Message : ${updateCnt }</h5>
		<h5>MessageTest 수정시 전달 Message : ${MessageTest }</h5>
		
		<form action="/listSearch">
			<select name="search">
				<option value="s_job" 	>업무조회</option>
				<option value="s_ename" >이름조회</option>
			</select>
			<input 	type="text" name="keyword" placeholder="Enter the keyword">
			<button type="submit">KEYWORD SEARCH</button>		
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
					<a href="/detailEmp?empno=${emp.empno }&currentPage=${currentPage }">${emp.ename }</a>
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