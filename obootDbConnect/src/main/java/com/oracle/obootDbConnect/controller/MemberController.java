package com.oracle.obootDbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.obootDbConnect.domain.Member7;
import com.oracle.obootDbConnect.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/memberForm")
	private String createMemberForm() {
		System.out.println("MemberController createMemberForm() is started");
		
		return "/members/createMemberForm";
	}
	
	@PostMapping(value = "/members/newSave")
	private String memberSave(Member7 member7) {
		System.out.println("MemberController memberSave(Member7 member7) is started");
		memberService.memberSave(member7);
		return "redirect:/";
		
	}
	
	@GetMapping(value = "/members/memberList")
	private String memberLists(Model model) {
		
		List<Member7> memberList = memberService.findMembers();
		model.addAttribute("members", memberList);
		return "/members/memberList";

	}

}
