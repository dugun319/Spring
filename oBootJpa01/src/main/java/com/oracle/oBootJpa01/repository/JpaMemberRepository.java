package com.oracle.oBootJpa01.repository;
import com.oracle.oBootJpa01.domain.Member;

import jakarta.persistence.EntityManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
	// JPA DML --> EntityManager 필수!!!!
	
	private final EntityManager em;
	
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member memberSave(Member member) {
		
		System.out.println("JpaMemberRepository memberSave() is started");
		em.persist(member);
		
		return member;
	}
	
	// JPQL 그냥 문법임.
	// SELECT a FROM Member a == SELECT m FROM Member m
	// m 과 a 는 alias 일뿐
	@Override
	public List<Member> findAllMember() {
		List<Member> memberList = em.createQuery("SELECT a FROM Member a", Member.class)
									.getResultList();
		
		System.out.println("JpaMemberRepository findAllMember() memberList.size() -> " + memberList.size());
		
		return memberList;
	}

	@Override
	public List<Member> findByName(String searchName) {
		
		String pname = searchName + "%";
		System.out.println("JpaMemberRepository findByName pname -> " + pname);
		
		List<Member> memberList = em.createQuery("SELECT a FROM Member a WHERE name LIKE : name", Member.class)
									.setParameter("name", pname)
									.getResultList();

		System.out.println("JpaMemberRepository findAllMember() memberList.size() -> " + memberList.size());
				
		return memberList;
	}

}
