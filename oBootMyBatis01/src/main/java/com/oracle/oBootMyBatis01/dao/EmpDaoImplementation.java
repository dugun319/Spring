package com.oracle.oBootMyBatis01.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImplementation implements EmpDao{
	
	//MyBatis DB connection
	
	private final SqlSession session;	
	
	@Override
	public int totalEmp() {
		
		int totEmpCount = 0;
		System.out.println("EmpDaoImplementation totalEmp() is started");
		
		try {
			totEmpCount = session.selectOne("com.oracle.oBootMyBatis01.EmpMapper.EmpTotal");
			System.out.println("EmpDaoImplementation totalEmp() is started totEmpCount -> " + totEmpCount);
		} catch (SqlSessionException e) {
			System.out.println("EmpDaoImplementation totalEmp() e.getMessage() -> " + e.getMessage());
		}
		
		
		return totEmpCount;
	}

}
