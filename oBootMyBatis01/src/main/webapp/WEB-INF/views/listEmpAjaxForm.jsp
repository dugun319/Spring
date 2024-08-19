<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function getDeptName(pDeptno) {
		console.log(pDeptno);
		//alert("pDeptno -> " + pDeptno);
		
		$.ajax(
				{
					// empController의 getDeptName 호출
					url: "<%=request.getContextPath()%>/getDeptName",
					data: {deptno: pDeptno},
					dataType: 'text',
					success: function(deptName){
						// id = deptName value 담기
						$('#deptName').val(deptName);
						
						// id = msg value 담기
						$('#msg').html(deptName);
					}
			
				}
		);
	}
	
	// sample/sendVO2
	// id: restDept 결과 값 넣어주기
	function getDept(pDeptno){
		console.log(pDeptno);
		//alert("pDeptno -> " + pDeptno);
		
		$.ajax(
				{
					// empController의 /getDeptName 호출
					url: "<%=request.getContextPath()%>/sample/sendVO2",
					data: {deptno: pDeptno},
					dataType: 'json',
					success: function(sampleVO){
						resultStr = sampleVO.lastName + " " + sampleVO.firstName + " " + sampleVO.mno;						
						alert("ajax getDept resultStr -> " + resultStr);
						$('#RestDept').val(resultStr);
						
					}
			
				}
		);
	}
	
	function empWriteBtn(){
		var empno		= $('#empno').val();
		// var sendData	= JSON.stringify($('#empTrans').serialize());
		var sendData	= $('#empTrans').serializeArray();
		//json data conversion;
		var sendData3 	= jsonParse(sendData);
		
		
		alert("sendData3 -> " + sendData3);
		console.log("sendData3 -> " + sendData3);
		
		$.ajax(
				{
					// empController의 /empSerializeWrite 호출
					url: "<%=request.getContextPath()%>/empSerializeWrite",
					type: 'POST',
					contentType: 'application/json;CHARSET=UTF-8',
					data: JSON.stringify(sendData3),
					dataType: 'json',
					success: function(response){
						if(response.writeResult > 0) {
							alert("Success");
						}
						
					}
				}	
		
		);
		
	}
	
	function jsonParse(sendData2){
		obj = {};
		
		if(sendData2){
			jQuery.each(sendData2, function(){
				obj[this.name] = this.value;
				//alert("this.name -> " + this.name);
				alert("this.value -> " + this.value);
			}
					
			);
			
		}
		return obj;
	}
	
</script>
</head>
	<body>
		<table>
		
			<tr>
				<th>Employee No.</th>
				<th>Name</th>
				<th>Job</th>
				<th>Department</th>
			</tr>
			
			<c:forEach var="emp" items="${listEmp}" >
			<tr>
				<td>${emp.empno }</td>
				<td>${emp.ename }</td>				
				<td>${emp.job }</td>
				<td>
					${emp.deptno }
					<input type="button" value="Dept Name" onmouseover="getDeptName(${emp.deptno})"/>
				</td>	
			</tr>		
			</c:forEach>	
							
		</table>
		<h3>deptName:  <input type="text" id="deptName"  readonly="readonly"></h3>
   		<h3>Message :  <span id="msg"></span></h3>
    
    	<h5>RestController sendVO2: 
    		<input type="text" id="RestDept"    readonly="readonly"></h5>
		<h5>RestController sendVO2: sendVO2
			<input type="button" id="btn_Dept"  value="부서명"  onclick="getDept(10)"></h5>
		
		
		<h2>Serialize Test</h2>
		    <form  name="empTrans"   id="empTrans">
		    	<input type="hidden"  id="empno"  name="empno"    value="1234"/>
		    	<input type="hidden"  id="ename"  name="ename"    value="시리얼"/>
		    	<input type="hidden"  id="sal"    name="sal"      value="1000"/>
		        <input type="button"  value="Ajax Serialize 확인" onclick="empWriteBtn()"/>
	     	</form>		
	</body>
</html>