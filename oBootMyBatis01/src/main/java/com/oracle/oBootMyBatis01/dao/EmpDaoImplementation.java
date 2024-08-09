package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis01.model.Emp;

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

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImplementation listEmp() is started");
		
		try {
			//								MAP_ID		PARAMETER
			empList = session.selectList("com.oracle.oBootMyBatis01.EmpMapper.EmpListAll", emp);
			System.out.println("EmpDaoImplementation listEmp() empList.size() -> " + empList.size());
			
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation listEmp() e.getMessage() -> " + e.getMessage());
		}
		
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		
		Emp detailEmp = null;
		System.out.println("EmpDaoImplementation detailEmp() is started");
		
		try {
			detailEmp = session.selectOne("com.oracle.oBootMyBatis01.EmpMapper.EmpSelOne", empno);
			System.out.println("EmpDaoImplementation detailEmp() detailEmp.getEname() -> " + detailEmp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation detailEmp() e.getMessage() -> " + e.getMessage());
		}		
		
		return detailEmp;
	}

	@Override
	public int updateEmp(Emp emp) {
		int updateCnt;
		System.out.println("EmpDaoImplementation updateEmp() is started");
		
		try {
			updateCnt = session.update("com.oracle.oBootMyBatis01.EmpMapper.EmpUpdate", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation updateEmp() e.getMessage() -> " + e.getMessage());
			updateCnt = 0;
		}		
		
		return updateCnt;
	}

}
