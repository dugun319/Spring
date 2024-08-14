<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chk() {
		if (!frm.deptno.value) {
			alert("부서번호를 입력한 후에 확인하세요");
			frm.deptno.focus();
			return false;
		} else {
			location.href="/confirmDeptno?deptno="+frm.deptno.value;
		}
	}
</script>
</head>
	<body>
	
	 <h2>부서정보 입력</h2>
	 
		<form action="writeDept" method="post" name="frm">
			<table>
				<tr>
					<th>부서번호</th>
					<td><input type="number" name="deptno"	required="required" maxlength="2"/>
						<input type="button" value="중복확인" onclick="chk()"/>&nbsp; &nbsp;
						<c:if test="${msg!=null}">${msg}</c:if>
					</td>
				</tr>
				
				<tr><th>부서이름</th><td><input type="text" name="dname" 
					required="required"> </td></tr>
				<tr><th>부서위치</th><td><input type="text" name="loc" 
					required="required"></td></tr>
			
				<tr><td colspan="2">
				<input type="submit" value="확인"></td></tr>
				
			</table>
			입력된 부서번호 :<c:if test="${deptvo.odeptno!=null}">${deptvo.odeptno}</c:if><p>
		         입력된 부서명   :<c:if test="${deptvo.odname!=null}">${deptvo.odname}</c:if><p> 
		         입력된 부서위치 :<c:if test="${deptvo.oloc!=null}">${deptvo.oloc}</c:if><p> 
		        <%-- ${deptVO.Oloc} --%>
		        
		   <h2>부서 정보 List</h2>
		   <table>
			<tr><th>부서명</th><th>부서이름</th><th>근무지</th></tr>
			<c:forEach var="deptList" items="${deptList}">
				<tr><td>${deptList.deptno }</td> 
				    <td>${deptList.dname }</td>
					<td>${deptList.loc }</td>
				</tr>
			</c:forEach>
		   </table>     
		        
		</form>   
		
	</body>
</html>