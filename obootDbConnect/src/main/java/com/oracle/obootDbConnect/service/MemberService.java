package com.oracle.obootDbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.obootDbConnect.domain.Member7;
import com.oracle.obootDbConnect.repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	
	public Long memberSave(Member7 member7) {
		
		memberRepository.save(member7);		
		return member7.getId();
	}
	
	public List<Member7> findMembers(){
		
		List<Member7> memberList = null; 
		memberList = memberRepository.findAll();
		
		return memberList;
	}

}
