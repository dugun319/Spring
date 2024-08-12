package com.oracle.oBootMyBatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.service.EmpService;
import com.oracle.oBootMyBatis01.service.Paging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService empService;	
	private static String broker_currentPage = "1";
	
	
	@RequestMapping(value = {"listEmpStart", "listEmp"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String listEmp(Emp emp, Model model) {
		
		System.err.println("EmpController listEmp() emp -> " + emp);
		System.err.println("EmpController listEmp() model.getAttribute(\"currentPage\") -> " + model.getAttribute("currentPage"));	
		log.info("EmpController listEmp() is started");
		
		int totEmpCnt = empService.totalEmp();
		
		String currentPage = broker_currentPage;
		
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
		model.addAttribute("currentPage", currentPage);
		return "list";
	}
	
	@GetMapping(value = "/detailEmp")
	private String detailEmp(Emp emp, Model model) {
		
		log.info("EmpController detailEmp() is started");
		System.out.println("EmpController detailEmp() emp.getEmpno() -> " + emp.getEmpno());
		
		Emp detailEmp = empService.detailEmp(emp.getEmpno());
		model.addAttribute("detailEmp", detailEmp);
		model.addAttribute("currentPage", emp.getCurrentPage());
		
		System.err.println("EmpController detailEmp() emp.getCurrentPage() -> " + emp.getCurrentPage());
		
		return "detailEmp";
	}
	
	
	@RequestMapping(value = "/updateFormEmp", method = {RequestMethod.GET, RequestMethod.POST})
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
	
	@RequestMapping(value = "/updateEmp", method = {RequestMethod.GET, RequestMethod.POST})
	private String updateEmp(Emp emp, Model model) {
		
		int updateCnt;
		log.info("EmpController updateEmp() is started");
		updateCnt = empService.updateEmp(emp);
		System.out.println("EmpController updateEmp() updateCnt -> " + updateCnt);
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("MessageTest", "MessageTest");
	
		//return "redirect:/listEmp";
		
		return "forward:/listEmp";
	}
	
	@RequestMapping(value = "/writeFormEmp1", method = {RequestMethod.GET, RequestMethod.POST})
	public String writFormEmp1(Model model) {
		log.info("EmpController writFormEmp1() is started");
		
		List<Emp> empMgrList = empService.listManager();
		
		System.out.println("EmpController writFormEmp1() empMgrList.size() -> " + empMgrList.size());
		model.addAttribute("empMrgList", empMgrList);
		
		List<Dept> deptList = empService.deptSelect();
		model.addAttribute("deptList", deptList);
		System.out.println("EmpController writFormEmp1() deptList.size() -> " + deptList.size());
		
		return "writeFormEmp1";
	}
	
	
	
	@PostMapping(value = "/writeEmp")
	public String writeEmp(Emp emp, Model model) {
		log.info("EmpController writeEmp() is started");
		
		int insertResult = empService.insertEmp(emp);
		if(insertResult > 0) {
			return "redirect:listEmp";
		} else {
			model.addAttribute("msg", "CHECK YOUR PARAMETERS");
			System.out.println("Checking input parameters!");
			return "forward:writeFormEmp1";
		}		
	}
	
	@GetMapping(value = "/confirm")
	public String confirm(Emp raw_emp, Model model) {
		log.info("EmpController confirm() is started");
		
		Emp emp = empService.detailEmp(raw_emp.getEmpno());
		model.addAttribute("empno", raw_emp.getEmpno());
		
		if(emp != null) {
			System.out.println("EmpController confirm() 중복된 사번");
			model.addAttribute("msg", "중복된 사번입니다");
		} else {
			System.out.println("EmpController confirm() 사용가능 사번");
			model.addAttribute("msg", "사용가능한 사번입니다");
		}
		
		return "forward:writeFormEmp";
	}
	
	@RequestMapping(value = "/writeFormEmp2", method = {RequestMethod.GET, RequestMethod.POST})
	public String writFormEmp2(Model model) {
		log.info("EmpController writFormEmp1() is started");
		
		List<Emp> empMgrList = empService.listManager();
		
		System.out.println("EmpController writFormEmp1() empMgrList.size() -> " + empMgrList.size());
		model.addAttribute("empMrgList", empMgrList);
		
		List<Dept> deptList = empService.deptSelect();
		model.addAttribute("deptList", deptList);
		System.out.println("EmpController writFormEmp1() deptList.size() -> " + deptList.size());
		
		return "writeFormEmp2";
	}
	
	@PostMapping(value = "/writeEmp2")
	public String writeEmp2(@ModelAttribute("emp") @Valid Emp emp,
							BindingResult result,			
							Model model) {
		
		log.info("EmpController writeEmp222222222222222222222222() is started");
		
		if(result.hasErrors()) {
			System.out.println("EmpController writeEmp222222222222222222222222() has error");
			model.addAttribute("msg", "BindingResult Check!");
			return "forward:writeFormEmp2";
		}
		
		
		
		int insertResult = empService.insertEmp(emp);
		if(insertResult > 0) {
			return "redirect:listEmp";
		} else {
			model.addAttribute("msg", "CHECK YOUR PARAMETERS");
			System.out.println("Checking input parameters!");
			return "forward:writeFormEmp2";
		}		
	}
	
	
	@GetMapping(value = "/confirm2")
	public String confirm2(Emp raw_emp, Model model) {
		log.info("EmpController confirm() is started");
		
		Emp emp = empService.detailEmp(raw_emp.getEmpno());
		model.addAttribute("empno", raw_emp.getEmpno());
		
		if(emp != null) {
			System.out.println("EmpController confirm() 중복된 사번");
			model.addAttribute("msg", "중복된 사번입니다");
		} else {
			System.out.println("EmpController confirm() 사용가능 사번");
			model.addAttribute("msg", "사용가능한 사번입니다");
		}
		
		return "forward:writeFormEmp2";
	}	
	
	
	@GetMapping(value = "/deleteEmp")
	public String deleteEmp(Emp emp, Model model) {
		
		int deleteCnt;
		System.out.println("EmpController updateEmp() emp.getCurrentPage() -> " + emp.getCurrentPage());
		
		log.info("EmpController deleteEmp() is started");
		deleteCnt = empService.deleteEmp(emp.getEmpno());
		System.out.println("EmpController updateEmp() updateCnt -> " + deleteCnt);
		
		model.addAttribute("deleteCnt", deleteCnt);				
		broker_currentPage = emp.getCurrentPage();
		
		return "redirect:/listEmp";
	}
	
	@GetMapping(value = "/listSearch")
	public String listSearch(Emp emp, Model model) {
		
		log.info("EmpController listSearch() is started");
		System.out.println("EmpController listSearch() emp -> " + emp);
		
		int totalEmp = empService.condTotalEmp(emp);
		System.out.println("EmpController listSearch() totalEmp -> " + totalEmp);
		
		Paging paging = new Paging(totalEmp, emp.getCurrentPage());
		emp.setStart(paging.getStart());
		emp.setEnd(paging.getEnd());
		System.out.println("EmpController listSearch() paging -> " + paging);
		
		List<Emp> searchEmpList = empService.searchEmpList(emp);
		
		System.out.println("EmpController searchEmpList.size() -> " + searchEmpList.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", searchEmpList);
		model.addAttribute("page", paging);
		
		return "listSearch";
	}
	
}
