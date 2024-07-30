package com.oracle.oBootHello.controller;

import java.util.List;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	MemberService memberService = new MemberService();
	
	@GetMapping(value = "members/memberForm")
	public String memberForm() {
		
		System.out.println("MemberController members/memberForm is started");
		logger.info("start logger.info");
		
		return "members/memberForm";
	}
	
	@PostMapping(value = "members/save")
	public String save(Member1 member1) {
		
		System.out.println("MemberController members/save is started");
		System.out.println("MemberController member1.getName() -> " + member1.getName());
		
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController members/save id -> " + id);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "members/memberList")
	public String memberList(Model model) {
		logger.info("memberList is started");
		List<Member1> memberList = memberService.allMembers();
		
		model.addAttribute("memberList", memberList);
		logger.info("memberList.size() -> {}", memberList.size());
		
		return "members/memberList";
	}
	
	
	
}
