package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis01.model.MemberTwo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberTwoDaoImplementation implements MemberTwoDao {

	private final SqlSession session;
	
	@Override
	public int memCount(String id) {
		int memCount;
		
		try {
			memCount = session.selectOne("com.oracle.oBootMyBatis01.MemberTwoMapper.MemberCount", id);
			System.out.println("MemberTwoDaoImplementation memCount() memCount -> " + memCount);
		} catch (Exception e) {
			System.out.println("MemberTwoDaoImplementation memCount() e.getMessage() -> " + e.getMessage());
			memCount = 0;
		}
		
		return memCount;
	}

	@Override
	public List<MemberTwo> listMember(MemberTwo memberTwo) {
		List<MemberTwo> listMember = null;
		
		try {
			listMember = session.selectList("com.oracle.oBootMyBatis01.MemberTwoMapper.ListMember");
			System.out.println("MemberTwoDaoImplementation listMember() listMember.size() -> " + listMember.size());
		} catch (Exception e) {
			System.out.println("MemberTwoDaoImplementation listMember() e.getMessage() -> " + e.getMessage());
		}
		
		return listMember;
	}

}
