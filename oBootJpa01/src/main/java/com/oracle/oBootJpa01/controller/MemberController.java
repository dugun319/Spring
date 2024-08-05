package com.oracle.oBootJpa01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.service.MemberSerive;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberSerive memberSerive;
	
	@Autowired
	public MemberController(MemberSerive memberSerive) {
		this.memberSerive = memberSerive;
	}
	
	@GetMapping(value = "/members/new")
	public String creatForm() {
		System.out.println("MemberController creatForm() is started");
		return "members/creatMemberForm";
	}
	
	@PostMapping(value = "/members/save")
	public String memberSave(Member member) {
		System.out.println("MemberController memberSave() is started");
		System.out.println("MemberController -> member " + member);
		System.out.println("MemberController -> member.getId() " + member.getId());
		System.out.println("MemberController -> member.getName() " + member.getName());
		
		memberSerive.memberSave(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		
		List<Member> memberList = memberSerive.getListAllMember();
		logger.info("memberList.size() -> " + memberList.size());
		model.addAttribute("members", memberList);
		
		return "members/memberList";
	}
	
	@PostMapping(value = "/members/search")
	public String memberSearch(Member member, Model model) {
		
		System.out.println("MemberController memberSearch member.getName() -> " + member.getName());		
		List<Member> memberList = memberSerive.getListSearchMember(member.getName());
		
		System.out.println("MemberController memberSearch memberList.size() -> " + memberList.size());
		model.addAttribute("members", memberList);
		
		return "members/memberList";
	}
	
}
