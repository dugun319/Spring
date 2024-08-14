package com.oracle.oBootMyBatis01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis01.domain.Member;
import com.oracle.oBootMyBatis01.repository.MemberJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberJpaService {
	
	private final MemberJpaRepository memberJpaRepository;

	public void save(Member member) {
		
		System.out.println("MemberJpaService save(Member member) is started");
		System.out.println("MemberJpaService save(Member member) member -> " + member);
		
		memberJpaRepository.save(member);
		
	}

	public List<Member> getListAllMember() {
		
		System.out.println("MemberJpaService getListAllMember() is started");
		List<Member> memberList = memberJpaRepository.findAll();
		System.out.println("MemberJpaService getListAllMember() is memberList.size() -> " + memberList.size());
		
		return memberList;
	}

	public Optional<Member> findById(Long memberId) {
		System.out.println("MemberJpaService findById() is started");
		Optional<Member> member = memberJpaRepository.findById(memberId);
		return member;
	}

	public void memberUpdate(Member member) {		
		System.out.println("MemberJpaService memberUpdate() is started");
		memberJpaRepository.updateByMember(member);
		System.err.println("MemberJpaService memberUpdate() is finished");
		
	}

}
