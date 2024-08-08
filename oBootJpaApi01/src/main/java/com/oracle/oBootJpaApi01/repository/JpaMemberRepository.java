package com.oracle.oBootJpaApi01.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi01.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
//	@Autowired
//	public JpaMemberRepository(EntityManager em) {
//		this.em = em;
//	}
	
	@Override
	public Long save(Member member) {
		System.out.println("JpaMemberRepository save before...");
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
					                .getResultList();
		System.out.println("JpaMemberRepository  findAll memberList.size()->"+memberList.size());
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member findMember = em.find(Member.class, memberId);
		return findMember;
	}

	@Override
	public int updateByMember(Member member) {
		
		int result;
		
		Member member3 = em.find(Member.class, member.getId());
		
		if(member3 != null) {
			member3.setName(member.getName());
			member3.setSal(member.getSal());
			result = 1;
			
			System.out.println("JpaMemberRepository updateByMember() is completed");
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember() is failed");
		}		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
