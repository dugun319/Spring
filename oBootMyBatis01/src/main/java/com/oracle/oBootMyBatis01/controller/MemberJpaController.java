package com.oracle.oBootMyBatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMyBatis01.domain.Member;
import com.oracle.oBootMyBatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberJpaController {
	
	private final MemberJpaService memberJpaService;
	
	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		
		log.info("MemberJpaController createForm() is started");
		
		return "memberJpa/createMemberForm";
	}
	
	
	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		
		log.info("MemberJpaController create(Member member) is started");
		System.out.println("MemberJpaController create(Member member) member ->" + member);
		
		memberJpaService.save(member);
		
		return "memberJpa/createMemberForm";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		log.info("MemberJpaController createlistMember(Model model) is started");
		
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("memberList", memberList);
		
		return "memberJpa/memberList";
	}
	
	@GetMapping(value = "/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Member raw_member, Model model) {
		
		log.info("MemberJpaController memberUpdateForm() is started");
		
		Member member = null;
		String returnJsp = "";
		
		System.out.println("MemberJpaController memberUpdateForm() raw_member.getId() -> " + raw_member.getId());
		Optional<Member> maybeMember = memberJpaService.findById(raw_member.getId());
		
		if(maybeMember.isPresent()) {
			
			System.out.println("MemberJpaController memberUpdateForm() maybeMember is not NULL");
			member = maybeMember.get();
			model.addAttribute("member", member);
			model.addAttribute("message", "찾는 Member가 존재하니 수정해 주세요.");
			returnJsp = "memberJpa/memberMofify";
		} else {
			System.out.println("MemberJpaController memberUpdateForm() maybeMember is NULL");
			model.addAttribute("message", "찾는 Member가 존재하지 않습니다. 입력을 먼저 해 주세요.");
			returnJsp = "forward:/members";
		}
		
		return returnJsp;
	}
	
	@GetMapping(value = "/memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		
		log.info("MemberJpaController memberUpdate() is started");
		memberJpaService.memberUpdate(member);
		
		return "redirect:/members";
	}
	

}
