<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h3>Image : ${savedName }</h3>
		<h5>Upload Image : </h5>
		<img alt="Upload Image" src="${pageContext.request.contextPath }/upload/${savedName}">
		<h5><a href="/uploadFileDelete?delFileName=${savedName}">UPLOAD CANCEL</a></h5>
		
	</body>
</html>