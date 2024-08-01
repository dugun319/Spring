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
		<form action="/modify" method="post">
			<input type="hidden" name="bId" value="${mvc_board.bId }"/>
			<table>
				<tr>
					<th>번호</th><td>${mvc_board.bId }</td>
				</tr>
				
				<tr>
					<th>히트</th><td>${mvc_board.bHit }</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="bName"			value="${mvc_board.bName }"/></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle"		value="${mvc_board.bTitle }"/></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="100" name="bContent">${mvc_board.bContent }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="5">
						<input type="submit" value="EDIT"> &nbsp;&nbsp;
						<a href="list">SHOW LIST</a> &nbsp;&nbsp;
						<a href="delete?bId=${mvc_board.bId }">DELET</a> &nbsp;&nbsp;
						<a href="reply_view?bId=${mvc_board.bId }">REPLY</a>
					</td>
				</tr>			
			</table>		
		</form>
	</body>
</html>