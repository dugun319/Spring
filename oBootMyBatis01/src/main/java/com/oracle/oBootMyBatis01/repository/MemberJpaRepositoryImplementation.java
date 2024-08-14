package com.oracle.oBootMyBatis01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis01.domain.Member;

import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberJpaRepositoryImplementation implements MemberJpaRepository {
	
	private final EntityManager entityManager;
	
	@Override
	public Member save(Member member) {
		entityManager.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		
		List<Member> memberList = null;
		
		try {
			memberList = entityManager.createQuery("SELECT M FROM Member M", Member.class)
									  .getResultList();
			
		} catch (Exception e) {
			System.out.println("MemberJpaRepositoryImplementation List<Member> findAll() e.getMessage() -> " + e.getMessage());
		}

		return memberList;
	}

	@Override
	public Optional<Member> findById(Long memberId) {
		Member member = entityManager.find(Member.class, memberId);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		// entityManager.merge(member);
		// merge는 현제 입력된 value만을 수정하고 나머지는 NULL로 입력
		
		Member upadate_member = entityManager.find(Member.class, member.getId());
		
		if(upadate_member != null) {
			System.out.println("MemberJpaRepositoryImplementation updateByMember member.getName() -> " + member.getName());
			upadate_member.setName(member.getName());
			//entityManager.persist(upadate_member);
		} else {
			System.out.println("MemberJpaRepositoryImplementation updateByMember needs to check");
		}		

	}

}
