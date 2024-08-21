<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	// Before Modify
	function getEmpListUpdateTest(){
		alert("getEmpListUpdateTest() is called");
		
 		var arr = new Array();
		var item;
		
		<c:forEach var="item" items="${listEmp}">
			arr.push({
						empno:  "${item.empno}",
						ename:  "${item.ename}",
						deptno: "${item.deptno}"				
			});			
		</c:forEach>
		
		for(let i = 0 ; i < arr.length ;){
			alert("arr.empno -> " + i + " : " + arr[i].empno + "  :::  " 
					+ "arr.ename -> " + i + " : " + arr[i].ename);
			i++;
			if(i>2) {
				return;
			}
		} 
	}
	
	//After Modify
	function getEmpListUpdate(){
		alert("getEmpListUpdate() is called");
		
		var empList = [];
		
		const inputs = document.querySelectorAll('input[name="empno"], input[name="ename"], input[name="deptno"]');
		
		for(let i = 0 ; i < inputs.length ; i +=3){
			
			const empno = inputs[i+1].value;
			const ename = inputs[i+2].value;
			const deptno = inputs[i+3].value;
			
			//JSON 객체로 변환
			const empItem = {"empno":empno, "ename":ename, "deptno":deptno};
			
			empList.push(empItem);
			
			if(i>5) {
				break;
			}
		}
		
		alert("JSON.stringify(empList) -> " + JSON.stringify(empList));
		
		if(empList.length > 0){
			$.ajax({
					url: "<%=request.getContextPath()%>/empListUpdate",
					method: 'POST',
					contentType: 'application/json;CHARSET=UTF-8',
					data: JSON.stringify(empList),
					dataType: 'json',
					success: function(response){
						console.log(response.updateResult);
					}
				
			
			});
			alert("Ajax empListUpdate is completed")
		}
	}	
	
	
</script>
</head>
	<body>
		<h1>Employee Information</h1>
		<table id="empList">
		
			<tr>
				<th>Num</th>
				<th>Employee No.</th>
				<th>Name</th>
				<th>Job</th>
				<th>Department</th>
			</tr>
			
			<c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="empListRow">
				<td>emp${status.index }</td>
				
				<td>
					<input type="hidden"	id="deptno"	name="deptno"	value="${emp.deptno }"/>
					<input type="text" 		id="empno" 	name="empno" 	value="${emp.empno }"/> 
				</td>
				<td>
					<input type="text" 		id="ename" 	name="ename"	value="${emp.ename }"/> 
				</td>				
				<td>${emp.job }</td>
				<td>
					${emp.deptno }
				</td>	
			</tr>		
			</c:forEach>	
							
		</table>
		
		<h5>RestController LIST VO3 : 
			<input type="button" id="btn_Dept3" value="empLIST Test 전송" onclick="getEmpListUpdateTest()">
		</h5>
	                                   
		<h5>RestController LIST VO3 : 
			<input type="button" id="btn_Dept3" value="empLIST 전송" onclick="getEmpListUpdate()">
		</h5>	
	</body>
</html>