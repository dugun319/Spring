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
		<h2>Inquire the Member Information</h2>
		<h4><c:if test="${msg != null }">${msg }</c:if></h4>
			<form action="interceptor" name="frm">
				<table>
					<tr>
						<th>Employee No.</th>
						<td><input type="text" 		name="id"			required="required"/></td>
					</tr>
				
					<tr>
						<td colspan="2">
						<input type="submit" value="Confirm">
						</td>
					</tr>
				
				</table>			
			</form>
	</body>
</html>