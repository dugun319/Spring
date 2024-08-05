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
		<h1>BOARD ${count}</h1>
		<table>
			<tr>
				<th>Number</th>
				<th>NAME</th>
				<th>TITLE</th>
				<th>DATE</th>
				<th>HIT</th>
			</tr>
			<c:forEach var="mvc_board" items="${boardList}" >
			<tr>
				<td>${mvc_board.bId }</td>
				<td>${mvc_board.bName }</td>
				<td>
					<c:forEach begin="1" end="${mvc_board.bIndent }">-</c:forEach>
					<a href="content_view?bId=${mvc_board.bId }">${mvc_board.bTitle }</a>
				</td>
				<td>${mvc_board.bDate }</td>
				<td>${mvc_board.bHit }</td>			
			</tr>			
			</c:forEach>
			<tr>
				<td colspan="10"><a href="write_view">글쓰기</a></td>
			</tr>					
		</table>
	</body>
</html>