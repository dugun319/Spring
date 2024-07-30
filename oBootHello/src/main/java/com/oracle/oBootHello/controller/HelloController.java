package com.oracle.oBootHello.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.dto.Emp;

@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("hello")
	// -> controller 뒤에 붙어서 root 다음에 나올 이름
	public String hello(Model model) {
		System.out.println("HelloController String hello() is started successfully");
		logger.info("start logger.info...");
		model.addAttribute("parameter", " boot start ...");
		
		return "hello";		
	}
	
	@ResponseBody
	@GetMapping("ajaxString")
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName -> " + aName);
		
		return aName;
	}
	
	@ResponseBody
	@GetMapping("ajaxEmp")
	public Emp ajaxEmp(@RequestParam("empno") String empno, 
					   @RequestParam("ename") String ename) {
		
		System.out.println("HelloController Emp ajaxEmp() empno -> " + empno);
		logger.info("ename -> {}", ename);
		
		Emp emp = new Emp();
		
		emp.setEmpno(empno);
		emp.setEname(ename);
		
		return emp;
	}
	
	
	
}
