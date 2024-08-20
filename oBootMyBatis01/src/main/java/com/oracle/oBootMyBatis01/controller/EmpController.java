package com.oracle.oBootMyBatis01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.DeptVO;
import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.EmpDept;
import com.oracle.oBootMyBatis01.model.MemberTwo;
import com.oracle.oBootMyBatis01.service.EmpService;
import com.oracle.oBootMyBatis01.service.Paging;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService empService;	
	private static String broker_currentPage = "1";
	private final JavaMailSender javaMailSender;
	
	
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
	
	@GetMapping(value = "/listEmpDept")
	public String listEmpDeptname(Model model) {
		log.info("EmpController listEmpDept() is started");
		
		List<EmpDept> listEmpDept = empService.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
	}
	
	@RequestMapping(value = "/mailTransport", method = {RequestMethod.GET, RequestMethod.POST})
	public String mailTransport(HttpServletRequest httpServletRequest, Model model) {
		
		log.info("EmpController mailTransport() is started");
		
		String toMail = "dugun319@naver.com";
		String setFrom = "dugun319@gmail.com";
		String title = "EmpController mailTransport()";
		
		System.out.println("EmpController mailTransport() toMail ->" + toMail);
		
		try {
			// MIME (Multi-purpose Internet Mail Extensions) 전자우편 Internet 표준 Format
			MimeMessage message 			= javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(toMail);
			messageHelper.setSubject(title);
			
			String tempPassword = (int)(Math.random() * 999999) + 1 + "";
			messageHelper.setText("Temporal Password : " + tempPassword);
			System.out.println("EmpController mailTransport() tempPassword ->" + tempPassword);
			
			javaMailSender.send(message);
			model.addAttribute("check", 1);
			
			//DB LOGIC
			
		} catch (Exception e) {
			System.out.println("EmpController mailTransport() e.getMessage() ->" + e.getMessage());
			model.addAttribute("check", 2);
		}	
		
		return "mailResult";
	}
	
	
	@RequestMapping(value = "/writeDeptIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeDeptIn(Model model) {
		log.info("EmpController writeDeptIn() is started");	
		
		return "writeDept";
	}
	

	@RequestMapping(value = "/writeDept", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeDept(DeptVO deptVO, Model model) {
		
		log.info("EmpController writeDept() is started");
		
		empService.insertDept(deptVO);
		
		if(deptVO == null) {
			System.out.println("EmpController writeDept() deptVO is NULL");
		} else {
			System.out.println("EmpController writeDept() deptVO.getOdeptno() -> " + deptVO.getOdeptno());
			System.out.println("EmpController writeDept() deptVO.getOdname() -> " + deptVO.getOdname());
			System.out.println("EmpController writeDept() deptVO.getOloc() -> " + deptVO.getOloc());
			
			model.addAttribute("msg", "Input is Completed");
			model.addAttribute("deptvo", deptVO);
			
			List<Dept> deptList = empService.deptSelect();
			model.addAttribute("deptList", deptList);
		}
		
		return "writeDept";
	}
		
	
	@RequestMapping(value = "/confirmDeptno", method = {RequestMethod.GET, RequestMethod.POST})
	public String confirmDeptno(Dept raw_dept, Model model) {
		log.info("EmpController confirmEmpno() is started");
		Dept dept = empService.detailDept(raw_dept.getDeptno());
		
		if(dept != null) {
			System.out.println("EmpController confirm() 중복된 부서번호입니다");
			model.addAttribute("msg", "중복된 부서번호입니다");
		} else {
			System.out.println("EmpController confirm() 사용가능 부서번호입니다");
			model.addAttribute("msg", "사용가능한 부서번호입니다");
		}
		
		return "forward:writeDeptIn";
	}
	
	
	
	@GetMapping(value = "/writeDeptCursor")
	public String writeDeptCursor(Model model) {
		
		log.info("EmpController writeDeptCursor() is started");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 100);
		
		empService.selectListDept(map);
		
		// resultMap은 DB 컬럼명과 DTO 변수 명이 다를 때 사용한다.
		@SuppressWarnings("unchecked")
		List<Dept> deptList = (List<Dept>) map.get("dept");
		
		for(Dept dept : deptList) {
			System.out.println("EmpController writeDeptCursor() dept-> " + dept);
		}
		
		System.out.println("EmpController writeDeptCursor() deptList.size()" + deptList.size());
		model.addAttribute("deptList", deptList);
		
		return "writeDeptCursor";
	}
	
	// interCeptor 시작 화면 
	@GetMapping(value = "/interceptorForm") 
	public String interceptorForm(Model model) {
		log.info("EmpController interceptorForm() is started");
	    return "interceptorForm";
	}
	
	/*
	// 2.interceptor Number 2
	@RequestMapping(value="/interCeptor")
	public String interCeptor(Member1 member1, Model model) {
		System.out.println("EmpController  interCeptor  Test Start");
		System.out.println("EmpController  interCeptor id->"+member1.getId());
		// 존재 : 1  , 비존재 : 0
		int memCnt = es.memCount(member1.getId()); 
		
		System.out.println("EmpController interCeptor memCnt ->"+ memCnt);

		model.addAttribute("id",member1.getId());
		model.addAttribute("memCnt",memCnt);
		System.out.println("interCeptor  Test End");
	
		return "interCeptor";   // User 존재하면  User 이용 조회 Page
	}
	*/
	
	// interceptor #2
	@GetMapping(value = "/interceptor")
	public String interceptor(MemberTwo memberTwo, Model model) {
		log.info("EmpController interceptor() is started");
		System.out.println("EmpController interceptor() memberTwo.getId() -> " + memberTwo.getId());
		
		int memCnt = empService.memCount(memberTwo.getId());
		
		model.addAttribute("id", memberTwo.getId());
		model.addAttribute("memCnt", memCnt);
		System.out.println("interceptor test is COMPLETED");
		
		return "interceptor";
	}
	
	
	@GetMapping(value = "/doMemberWrite")
	public String doMemberWrite(Model model, HttpServletRequest request) {
		
		log.info("EmpController doMemberWrite() is started");
		
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("EmpController doMemberWrite() DO FIRST");
		model.addAttribute("id", ID);
		model.addAttribute("message", "CREATE MEMBER FIRST!!!");
		
		return "doMemberWrite";
	}
	
	
	@GetMapping(value = "/doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		
		log.info("EmpController doMemberList() is started");
		
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList ID -> " + ID);
		
		MemberTwo memberTwo = null;
		
		List<MemberTwo> listMember = empService.listMember(memberTwo);
		
		System.out.println("doMemberList LINE 466 -> " + listMember);
		model.addAttribute("ID", ID);
		System.out.println("doMemberList LINE 468 ID -> " + ID);
		model.addAttribute("listMember", listMember);
		
		return "doMemberList";
	}
	
	@GetMapping(value = "/ajaxForm")
	public String ajaxForm(Model model) {
		log.info("EmpController ajaxForm() is started");		
		return "ajaxForm";
	}
	
	@ResponseBody
	@GetMapping(value = "/getDeptName")
	public String getDeptName(Dept dept, Model model) {
		log.info("EmpController getDeptName() is started");
		
		System.err.println("EmpController getDeptName() dept.getDeptno() -> " + dept.getDeptno());
		String deptName = empService.deptName(dept.getDeptno());		
		
		return deptName;
	}
	
	
	@GetMapping(value = "/listEmpAjaxForm1")
	public String listEmpAjaxForm1(Model model) {
		log.info("EmpController listEmpAjaxForm1() is started");
		Emp emp = new Emp();
		
		emp.setStart(1);
		emp.setEnd(10);
		
		List<Emp> listEmp = empService.listEmp(emp);
		
		System.out.println("EmpController listEmpAjaxForm1() listEmp.size() -> " + listEmp.size());
		model.addAttribute("result", "result Ajax");
		model.addAttribute("listEmp", listEmp);
		
		return "listEmpAjaxForm";		
	}
	
	@ResponseBody
	@PostMapping(value = "/empSerializeWrite")
	public Map<String, Object> empSerializeWrite(@RequestBody @Valid Emp emp){
		log.info("EmpController empSerializeWrite() is started");
		System.out.println("EmpController empSerializeWrite() emp -> " + emp);
		int writeResult = 1;
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("writeResult", writeResult);
		
		return resultMap;
	}
	
	@GetMapping(value = "/listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		log.info("EmpController listEmpAjaxForm2() is started");
		
		Emp emp = new Emp();
		emp.setStart(1);
		emp.setEnd(10);
		
		List<Emp> listEmp = empService.listEmp(emp);
		model.addAttribute("listEmp", listEmp);
		System.out.println("EmpController listEmpAjaxForm2() listEmp.size() -> " + listEmp.size());
		
		return "listEmpAjaxForm2";
	}
	
	
	@GetMapping(value = "/listEmpAjaxForm3")
	public String listEmpAjaxForm3(Model model) {
		log.info("EmpController listEmpAjaxForm3() is started");
		
		Emp emp = new Emp();
		emp.setStart(1);
		emp.setEnd(10);
		
		List<Emp> listEmp = empService.listEmp(emp);
		model.addAttribute("listEmp", listEmp);
		System.out.println("EmpController listEmpAjaxForm3() listEmp.size() -> " + listEmp.size());
		
		return "listEmpAjaxForm3";
	}

	@ResponseBody
	@PostMapping(value = "/empListUpdate")
	public Map<String, Object> empListUpdate(@RequestBody @Valid List<Emp> listEmp){
		log.info("EmpController empListUpdate() is started");
		Map<String, Object> resultMap = new HashMap<>();
		int updateResult = 1;
		
		for(Emp emp : listEmp) {
			System.out.println("EmpController empListUpdate() emp -> " + emp);
			//int updateResult = empService.updateEmp(emp);
		}
		
		resultMap.put("updateResult", updateResult);
		
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping(value = "/transactionInsertUpdate")
	public String transactionInsertUpdate() {
		log.info("EmpController transactionInsertUpdate() is started");
		
		int rtnMember = empService.transactionInsertUpdate();
		System.out.println("EmpController transactionInsertUpdate() rtnMember -> " + rtnMember);
		String rtnMemberStr = String.valueOf(rtnMember);
		
		return rtnMemberStr;		
	}
	
}
