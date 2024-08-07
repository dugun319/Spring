package com.oracle.oBootJpa02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
*/


@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new createForm() is started");
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/memberSave")
	public String memberSave(Member member) {
		
		System.out.println("MemberController /memberSave memberSave() is started");
		logger.info("member -> " + member);
		System.out.println("member -> " + member);
		System.out.println("member.getName() -> " + member.getName());
		
		memberService.memberSave(member); 
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("MemberController /membes listMember() is started");
		System.out.println("/members listMember() memberList.get(0).getName() -> " + memberList.get(0).getName());
		
		model.addAttribute("memberList", memberList);
		
		return "members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm")
	public String memberModify(Member member, Model model) {
		
		System.out.println("MemberController /memberModifyForm memberModify() member.getId() -> " + member.getId());
		Member member3 = memberService.findByMember(member.getId());
		
		System.out.println("member -> " + member3);
		model.addAttribute("member", member3);
		
		return "members/memberModify";
	}
	
	@PostMapping(value = "/members/memberUpdate")
	private String memberUpdate(Member member, Model model) {
		
		System.out.println("MemberController /members/memberUpdate() member -> " + member);
		memberService.memberUpdate(member);
		logger.info("MemberController memberService.memberUpdate(member) is called");
		return "redirect:/members";
				
	}
	
	@GetMapping(value = "/members/search")
	private String serach(Member member, Model model) {
		
		List<Member> memberList = memberService.getListMember(member.getName());		
		logger.info("MemberController memberService.getListMember(member.getName()) is called");
		
		model.addAttribute("memberList", memberList);		
		
		return "members/memberList";
	}
	
	@GetMapping(value = "/findByListMembers")
	private String findByListMembers(Member member, Model model) {
		
		List<Member> memberList = memberService.getListfindByMembers(member);		
		logger.info("MemberController memberService.getListfindByMembers(member) is called");
		
		model.addAttribute("memberList", memberList);	
		return "members/memberList";
	}
	
	
	
}
