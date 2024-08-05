package com.oracle.oBootJpa01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.repository.MemberRepository;

@Service
@Transactional
public class MemberSerive {
	
	private MemberRepository memberRepository;
	
	public MemberSerive(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		
	}
	
	public Long memberSave(Member member) {
		
		System.out.println("MemberSerive memberSave() is started");
		System.out.println("MemberSerive memberSave() member -> " + member);
		memberRepository.memberSave(member);
		
		return member.getId();		
	}

	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAllMember();
		System.out.println("MemberSerive getListAllMember() listMember.size() -> " + listMember.size());
		return listMember;
	}

	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberSerive getListSearchMember() is started");
		System.out.println("MemberSerive getListSearchMember() searchName -> " + searchName);
		
		List<Member> listMember = memberRepository.findByName(searchName);
		System.out.println("MemberSerive getListSearchMember() listMember.size() -> " + listMember.size());		
		
		return listMember;
	}
}
