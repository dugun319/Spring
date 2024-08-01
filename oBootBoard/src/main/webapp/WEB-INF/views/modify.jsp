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
		<c:if test="${result > 0 }">
			<script type="text/javascript">
				alert("EDIT is completed succesfully!");
				location.href="list";
			</script>
		</c:if>
		
		<c:if test="${result == 0 }">
			<script type="text/javascript">
				alert("EDIT is failed!");
				location.href="content_view?bId=${mvc_board.bId }";
			</script>
		</c:if>		
	</body>
</html>