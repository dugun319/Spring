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
	function getListDept(){
		alert("getListDept() is called");
		
		var str1 = "";
		var str2 = "";
		
		$.ajax(
				{
					url: "<%=request.getContextPath()%>/sendVO3",
					dataType: 'json',
					success: function(deptList){
						var jsonStr = JSON.stringify(deptList);
						alert("jsonStr -> " + jsonStr);
						$('#dept_list_str').append(jsonStr);
						
						str1 += "<h5><select name='dept'>";
						
						$(deptList).each(
								function(){
									str2 = "<option value='" 
											+ this.deptno 
											+ "'>" 
											+ this.dname
											+ "</option>";
									str1 += str2;									
								}					
						)
						str1 +="</select></h5>"
						alert("ComboBox str1 -> " + str1);
						$('#dept_list_combobox').append(str1);						
					}			
				}		
		);		
	}
	
	
	function getEmpnoDelete(pIndex){
		var_empno = $("#empno" + pIndex).val();
		alert("var_empno -> " + var_empno);
		
		//결과를 객체로 받음
		$.ajax(
				{
					//sending
					url: "<%=request.getContextPath()%>/empnoDelete",
					data: {empno: var_empno},
					
					//receiving
					dataType: 'json',
					success: function(response){
						alert("response -> " + response.deleteResult);
						if(response.deleteResult == '1') {
							$("#emp" + pIndex).remove();
							alert("Success");
						}
						
					}
				}
		);
		
<%-- 	결과를 TEXT로 받음	
		$.ajax(
				{
					url: "<%=request.getContextPath()%>/empnoDelete",
					data: {empno: var_empno},
					dataType: 'text',
					success: function(result){
						alert("result -> " + result);
						if(result == '1') {
							$("#emp" + pIndex).remove();
							alert("Success");
						}
						
					}
				}
		); --%>
		
	}
</script>

</head>
	<body>
		<table>
		
			<tr>
				<th>Num</th>
				<th>Employee No.</th>
				<th>Name</th>
				<th>Job</th>
				<th>Department</th>
			</tr>
			
			<c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="emp${status.index }">
				<td>emp${status.index }</td>
				
				<td>
					<input type="hidden"	id="deptno${status.index }" value="${emp.deptno }"/>
					<input type="text" 		id="empno${status.index }" 	value="${emp.empno }"/> 
				</td>
				<td>
					<input type="text" 		id="ename${status.index }" 	value="${emp.ename }"/> 
				</td>				
				<td>${emp.job }</td>
				<td>
					${emp.deptno }
					<input 	type="button" 	
							id="btn_idCheck2"	
							value="Empolyee Row Delete" 
							onclick="getEmpnoDelete(${status.index })"/>
				</td>	
			</tr>		
			</c:forEach>	
							
		</table>
		
		<h5>RestController LIST VO3 : 
			<input type="button" id="btn_Dept3" value="부서명 LIST" onclick="getListDept()">
		</h5>
	                                   
		<h5>★ dept_list_str ★</h5>	
		<div id="dept_list_str"></div>
	
		<h5>★ dept_list_combobox ★</h5>
		<div id="dept_list_combobox"></div>
		
		<h1>The End </h1>			
	</body>
</html>