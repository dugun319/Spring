<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h4>Upload Image : 
			<img alt="Upload Image" src="${pageContext.request.contextPath }/upload/${savedName}">
		</h4>
		
		<form action="/uploadForm"	id="form1" 	method="post" enctype="multipart/form-data">
			<h5><input type="file" name="file1"/></h5>
			<h5><input type="text" name="title"/></h5>
			<h5><input type="submit"/></h5>
		</form>
	</body>
</html>