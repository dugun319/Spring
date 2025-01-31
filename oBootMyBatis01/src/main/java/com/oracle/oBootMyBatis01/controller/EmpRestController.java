package com.oracle.oBootMyBatis01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.SampleVO;
import com.oracle.oBootMyBatis01.service.EmpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//@Controller + @ResponseBody
@RestController
@Slf4j
@RequiredArgsConstructor
public class EmpRestController {

	private final EmpService empService;
	
	@GetMapping(value = "/helloText")
	public String helloText() {
		
		log.info("EmpRestController helloText() is started");
		String hello = "HELLO TEXT";
		
		return hello;
	}
	
	@GetMapping(value = "/sample/sendVO2")
	public SampleVO sendVO2(Dept dept) {
		log.info("EmpRestController sendVO2() is started");
		System.out.println("EmpRestController sendVO2() dept.getDeptno() -> " + dept.getDeptno());
		
		SampleVO vo	= new SampleVO();
		
		vo.setFirstName("GilDong");
		vo.setLastName("Hong");
		vo.setMno(dept.getDeptno());
		
		return vo;
	}
	
	@GetMapping(value = "/sendVO3")
	public List<Dept> sendVO3() {
		log.info("EmpRestController sendVO3() is started");		
		List<Dept> deptList = empService.deptSelect();
		
		return deptList;
	}
	
	
	@GetMapping(value = "/empnoDelete")
	public Map<String, Object> empnoDelete(Emp emp) {
		
		log.info("EmpController empnoDelete() is started");
		System.out.println("EmpController empnoDelete() emp -> " + emp);
				
		int deleteResult = empService.deleteEmp(emp.getEmpno());		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("deleteResult", deleteResult);		
		return resultMap;		
		
		/*
		System.out.println("EmpController empnoDelete() deleteResult -> " + deleteResult);
		String strDeleteResult = Integer.toString(deleteResult);		
		return strDeleteResult;
		*/
	}
}
