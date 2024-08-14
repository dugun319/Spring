package com.oracle.oBootMyBatis01.repository;

import java.util.List;
import java.util.Optional;

import com.oracle.oBootMyBatis01.domain.Member;

public interface MemberJpaRepository {
	Member				save(Member member);
	List<Member> 		findAll();
	Optional<Member>	findById(Long memberId);
	void				updateByMember(Member member);
	
}
