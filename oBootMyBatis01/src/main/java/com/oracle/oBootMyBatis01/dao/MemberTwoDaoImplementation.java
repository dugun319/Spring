package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.oracle.oBootMyBatis01.model.MemberTwo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberTwoDaoImplementation implements MemberTwoDao {

	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;
	
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

	@Override
	public int transactionInsertUpdate1() {
		int result = 0;
		MemberTwo member1 = new MemberTwo();
		MemberTwo member2 = new MemberTwo();
		
		try {
			member1.setId("1005");
			member1.setName("을지문덕");
			member1.setPassword("1");
			
			result = session.insert("com.oracle.oBootMyBatis01.MemberTwoMapper.insertMemberTwo", member1); 
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate1() member1 result -> " + result);
			
			// Same primaryKey, error will be occurred.
			member2.setId("1005");
			member2.setName("계백");
			member2.setPassword("2");
			
			result = session.insert("com.oracle.oBootMyBatis01.MemberTwoMapper.insertMemberTwo", member2); 
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate1() member2 result -> " + result);
			
		} catch (Exception e) {
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate1() e.getMessage() -> " + e.getMessage());
			result = -1;
		}
		return result;
	}

	
	@Override
	public int transactionInsertUpdate2() {
		int result = 0;
		MemberTwo member1 = new MemberTwo();
		MemberTwo member2 = new MemberTwo();
		
		//#시작
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			//기본적으로 SqlSession이 하나 실행되면 자동으로 COMMIT
			// Transaction관리는 transactionManager의 getTransaction을가지고 상태따라 설정
			member1.setId("1006");
			member1.setName("관창");
			member1.setPassword("1");
			
			result = session.insert("com.oracle.oBootMyBatis01.MemberTwoMapper.insertMemberTwo", member1); 
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate2() member1 result -> " + result);
			
			// Same primaryKey, error will be occurred.
			member2.setId("1007");
			member2.setName("계백");
			member2.setPassword("2");
			
			result = session.insert("com.oracle.oBootMyBatis01.MemberTwoMapper.insertMemberTwo", member2); 
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate2() member2 result -> " + result);
			
			//#끝
			transactionManager.commit(transactionStatus);
			// COMMIT 범위 #시작 ~ #끝
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			System.out.println("MemberTwoDaoImplementation transactionInsertUpdate2() e.getMessage() -> " + e.getMessage());
			result = -1;
		}
		return result;
	}

}
