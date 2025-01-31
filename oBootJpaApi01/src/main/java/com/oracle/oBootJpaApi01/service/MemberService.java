package com.oracle.oBootJpaApi01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.repository.MemberRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
  // 전체회원 조회 API
	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}

	public Long saveMember(@Valid Member member) {
		System.out.println("MemberService saveMember() member.getName() -> " + member.getName());
		Long id = memberRepository.save(member);
		
		return id;
	}

	public Member findByMember(Long memberId) {
		Member findMember = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember() findMember -> " + findMember);
		return findMember;
	}

	public void updateMember(Long id, String name, Long sal) {
		Member member = new Member();
		
		member.setId(id);
		member.setName(name);
		member.setSal(sal);
		
		System.out.println("MemberService updateMember() member -> " + member);
		
		memberRepository.updateByMember(member);
		
		return;
	}
	

}
