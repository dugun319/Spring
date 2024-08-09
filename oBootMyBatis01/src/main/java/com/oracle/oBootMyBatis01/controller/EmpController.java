package com.oracle.oBootMyBatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.service.EmpService;
import com.oracle.oBootMyBatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService empService;
	
	
	@GetMapping(value = {"listEmpStart", "listEmp"})
	public String listEmp(Emp emp, Model model) {
		
		System.err.println("EmpController listEmp() emp -> " + emp);
		System.err.println("EmpController listEmp() model.getAttribute(\"currentPage\") -> " + emp.getCurrentPage());	
		log.info("EmpController listEmp() is started");
		
		int totEmpCnt = empService.totalEmp();
		
		String currentPage = "1";
		
		if(emp.getCurrentPage() != null) {
			currentPage = emp.getCurrentPage();
		}			
		
		Paging paging = new Paging(totEmpCnt, currentPage);
		emp.setStart(paging.getStart());
		emp.setEnd(paging.getEnd());
		
		List<Emp> listEmp = empService.listEmp(emp);
		
		System.out.println("EmpController listEmp.size() -> " + listEmp.size());
		
		model.addAttribute("totalEmp", totEmpCnt);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", paging);
		return "list";
	}
	
	@GetMapping(value = "/detailEmp")
	private String detailEmp(Emp emp, Model model) {
		
		log.info("EmpController detailEmp() is started");
		System.out.println("EmpController detailEmp() emp.getEmpno() -> " + emp.getEmpno());
		
		Emp detailEmp = empService.detailEmp(emp.getEmpno());
		model.addAttribute("detailEmp", detailEmp);
		
		return "detailEmp";
	}
	
	
	@GetMapping(value = "/updateFormEmp")
	private String updateFormEmp(Emp emp, Model model) {
		
		log.info("EmpController updateFormEmp() is started");
		
		Emp detailEmp = empService.detailEmp(emp.getEmpno());
		System.out.println("EmpController updateFormEmp() detailEmp -> " + detailEmp);
		
		String tempDate = "";
		
		if(detailEmp.getHiredate() != null) {
			tempDate = detailEmp.getHiredate().substring(0, 10);
			detailEmp.setHiredate(tempDate);
		}
		System.out.println("EmpController updateFormEmp() tempDate -> " + tempDate);
		
		model.addAttribute("detailEmp", detailEmp);
		
		return "updateFormEmp";
	}
	
	@PostMapping(value = "/updateEmp")
	private String updateEmp(Emp emp, Model model) {
		
		int updateCnt;
		log.info("EmpController updateEmp() is started");
		updateCnt = empService.updateEmp(emp);
		System.out.println("EmpController updateEmp() updateCnt -> " + updateCnt);
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("MessageTest", "MessageTest");
	
		return "redirect:/listEmp";
	}
	
	
	
	
}
