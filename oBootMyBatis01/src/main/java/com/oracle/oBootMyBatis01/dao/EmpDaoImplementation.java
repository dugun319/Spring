package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.EmpDept;

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

	@Override
	public int deleteEmp(int empno) {
		int deleteCnt;
		System.out.println("EmpDaoImplementation deleteEmp() is started");
		
		try {
			deleteCnt = session.delete("com.oracle.oBootMyBatis01.EmpMapper.EmpDelete", empno);
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation deleteEmp() e.getMessage() -> " + e.getMessage());
			deleteCnt = 0;
		}		
		
		return deleteCnt;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empMgrList = null;
		System.out.println("EmpDaoImplementation listManager() is started");
		
		try {
			empMgrList = session.selectList("com.oracle.oBootMyBatis01.EmpMapper.SelectManger");
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation listManager() e.getMessage() -> " + e.getMessage());
			//mgrListCnt = 0;
		}		
		
		return empMgrList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int insertResult;
		
		System.out.println("EmpDaoImplementation insertEmp() is started");
		
		try {
			insertResult = session.insert("com.oracle.oBootMyBatis01.EmpMapper.InsertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation insertEmp(Emp emp) e.getMessage() -> " + e.getMessage());
			insertResult = 0;
		}
		
		
		return insertResult;
	}

	@Override
	public int condTotalEmp(Emp emp) {
		
		System.out.println("EmpDaoImplementation condTotalEmp() is started");
		int totalEmp;
		
		try {
			
			totalEmp = session.selectOne("com.oracle.oBootMyBatis01.EmpMapper.CondEmpTotal", emp);
				
			} catch (Exception e) {
				System.out.println("EmpDaoImplementation condTotalEmp(Emp emp) e.getMessage() -> " + e.getMessage());
				totalEmp = 0;
			}
	
		
		return totalEmp;
	}

	@Override
	public List<Emp> searchEmpList(Emp emp) {
		List<Emp> searchEmpList = null;
		System.out.println("EmpDaoImplementation searchEmpList() is started");
		System.err.println("EmpDaoImplementation searchEmpList() emp.getSearch() ->" + emp.getSearch());
		System.err.println("EmpDaoImplementation searchEmpList() emp.getKeyword() ->" + emp.getKeyword());		
		try {
			//										MAP_ID										PARAMETER
			searchEmpList = session.selectList("com.oracle.oBootMyBatis01.EmpMapper.SearchEmpList", emp);
			System.out.println("EmpDaoImplementation searchEmpList() searchEmpList.size() -> " + searchEmpList.size());
			
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation searchEmpList() e.getMessage() -> " + e.getMessage());
		}
		
		return searchEmpList;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		
		List<EmpDept> listEmpDept = null;
		
		try {
			//										MAP_ID										
			listEmpDept = session.selectList("com.oracle.oBootMyBatis01.EmpDeptMapper.listEmpDept_LOJ");
			System.out.println("EmpDaoImplementation listEmpDept() listEmpDept.size() -> " + listEmpDept.size());
			
		} catch (Exception e) {
			System.out.println("EmpDaoImplementation listEmpDept() e.getMessage() -> " + e.getMessage());
		}
		
		
		return listEmpDept;
	}

}
