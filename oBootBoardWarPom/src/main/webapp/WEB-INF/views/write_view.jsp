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
		<form action="write" method="post">			
			<table>
				<tr>
					<th>이름</th>
					<td><input type="text" name="bName"		size="50""/></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle"	size="50""/></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="100" name="bContent"></textarea></td>
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