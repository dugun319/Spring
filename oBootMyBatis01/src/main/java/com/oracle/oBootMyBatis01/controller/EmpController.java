package com.oracle.oBootMyBatis01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.service.EmpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService empService;
	
	@GetMapping(value = "listEmpStart")
	public String listEmp(Emp emp, Model model) {
		
		log.info("EmpController listEmp() is started");
		
		int totEmpCnt = empService.totalEmp();
		
		model.addAttribute("totalEmp", totEmpCnt);
		return "list";
	}
}
