package com.oracle.oBootJpa02.service;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// @Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member memberSave(Member member) {
		System.out.println("MemberService memberSave() is started");
		System.out.println("MemberService memberSave() member -> " + member);
		
		memberRepository.memberSave(member);		
		
		return member;
	}

	public List<Member> getListAllMember() {		
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember() listMember.size() -> " + listMember.size());
		
		return listMember;
	}

	public Member findByMember(Long memberId) {
		Member member1 = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember() member1 -> " + member1);
		return member1;
	}

	public void memberUpdate(Member member) {
		System.out.println("MemberService memberUpdate() member -> " + member);
		memberRepository.updateByMember(member);
		System.out.println("MemberService memberRepository.updateMember(member) is called");
		
		return;
		
	}

	public List<Member> getListMember(String name) {
		System.out.println("MemberService name -> " + name);
		List<Member> memberList = memberRepository.findByName(name);
		System.out.println("MemberService memberRepository.findByName(name) is called");
		return memberList;
	}

	public List<Member> getListfindByMembers(Member member) {
		
		Long id = member.getId();
		Long sal = member.getSal();
		
		System.out.println("MemberService member.getId() -> " + id);		
		System.out.println("MemberService member.getSal() -> " + sal);
		
		
		List<Member> memberList = memberRepository.findByList(id, sal);
		System.out.println("MemberService memberRepository.findByList(id, sal) is called");
		
		return memberList;
	}

}
