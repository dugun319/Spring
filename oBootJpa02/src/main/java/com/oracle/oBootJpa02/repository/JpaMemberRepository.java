package com.oracle.oBootJpa02.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	@Autowired	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member memberSave(Member member) {	
		//1. Team Save
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		
		//2. Member Save
		member.setTeam(team);
		em.persist(member);

		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class)
									.getResultList();
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return member;
	}

	@Override
	public void updateMember(Member member) {
		/*
		 * List<Member> memberList = em.createQuery("SELECT m FROM Member m",
		 * Member.class)
		 * 
		 * .getResultList();
		 */
		
	}

}
