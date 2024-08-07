package com.oracle.oBootJpa02.repository;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	// @Autowired	
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
	public int updateByMember(Member member) {
		int result = 0;
		
		System.out.println("JpaMemberRepository updateByMember member -> " + member);
		Member member3 = em.find(Member.class, member.getId());
		
		if(member3 != null) {
			System.out.println("JpaMemberRepository updateByMember member -> " + member.getTeamid());
			Team team = em.find(Team.class, member.getTeamid());
			if(team != null) {
				team.setName(member.getTeamname());
				em.persist(team);
			}
			
			
			member3.setTeam(team);
			member3.setName(member.getName());
			member3.setSal(member.getSal());
			
			em.persist(member3);
			
			result = 1;
					
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember member is failed");
		}
		
		return result; 
		
	}

	@Override
	public List<Member> findByName(String searchName) {
		
		String pname = searchName + "%" ;
		System.out.println("JpaMemberRepository findByName() pname -> " + pname);
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE : pname", Member.class)
									.setParameter("pname", pname)
									.getResultList();		
		System.out.println("JpaMemberRepository findByName() memberList.size() -> " + memberList.size());
		
		return memberList;
	}

	@Override
	public List<Member> findByList(Long id, Long sal) {
		List<Member> memberList = em.createQuery("SELECT m FROM Member m "
												+ "WHERE id > :id "
										   		+ "	AND sal > :sal", Member.class)
									.setParameter("id", id)
									.setParameter("sal", sal)
									.getResultList();	
		
		System.out.println("JpaMemberRepository findByList() memberList.size() -> " + memberList.size());
		
		return memberList;
	}

}
