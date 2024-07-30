package com.oracle.oBootHello.service;

import java.util.List;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.repository.MemberRepository;
import com.oracle.oBootHello.repository.MemoryMemberRepository;

public class MemberService {
	
	MemberRepository memberRepository = new MemoryMemberRepository();
	
	public Long memberSave(Member1 member1) {
		System.out.println("MemberService memberSave() is started");
		
		memberRepository.save(member1);
		return member1.getId();
	}

	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers() is started");
		List<Member1> memberList = null;
		
		memberList = memberRepository.findAll();
		System.out.println("MemberService memberList.size() -> " + memberList.size());
				
		return memberList;
		
	}
}
