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
		<form action="reply" method="post">
		
			<input type="hidden" 	name="bId" 		value="${reply_view.bId }" />
			<input type="hidden" 	name="bGroup" 	value="${reply_view.bGroup }"/>
			<input type="hidden" 	name="bStep" 	value="${reply_view.bStep }"/>
			<input type="hidden" 	name="bIndent"	value="${reply_view.bIndent }"/>
			
			<table>
				<tr>
					<th>번호</th><td>${reply_view.bId }</td>
				</tr>
				
				<tr>
					<th>히트</th><td>${reply_view.bHit }</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="bName"		value="${reply_view.bName }"/></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle"	value="[RE] ${reply_view.bTitle }"/></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="100" name="bContent">${reply_view.bContent }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="5">
						<input type="submit" value="REPLY"> &nbsp;&nbsp;
						<input type="reset"  value="RESET"> &nbsp;&nbsp;
						<a href="list">SHOW LIST</a>
					</td>
				</tr>			
			</table>		
		</form>
	</body>
</html>