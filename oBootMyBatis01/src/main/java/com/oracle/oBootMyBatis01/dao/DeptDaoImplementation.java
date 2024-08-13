package com.oracle.oBootMyBatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.DeptVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImplementation implements DeptDao {
	//MyBatis DB Connection
	private final SqlSession session;
	
	@Override
	public List<Dept> deptSelect() {
				
		List<Dept> deptList = null;
		System.out.println("DeptDaoImplementation deptSelect() is started");
		
		try {
			deptList = session.selectList("com.oracle.oBootMyBatis01.DeptMapper.DeptSelect");
		} catch (Exception e) {
			System.out.println("DeptDaoImplementation deptSelect() e.getMessage() -> " + e.getMessage());
			//mgrListCnt = 0;
		}		
		
		return deptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImplementation insertDept() is started");
		try {
			session.selectOne("com.oracle.oBootMyBatis01.DeptMapper.ProcDeptInsert", deptVO);
		} catch (Exception e) {
			System.out.println("DeptDaoImplementation insertDept() e.getMessage() -> " + e.getMessage());
		}
		
		
	}

	@Override
	public void selectListDept(HashMap<String, Object> map) {
		System.out.println("DeptDaoImplementation selectListDept() is started");
		try {
			session.selectOne("com.oracle.oBootMyBatis01.DeptMapper.ProcDeptList", map);
		} catch (Exception e) {
			System.out.println("DeptDaoImplementation insertDept() e.getMessage() -> " + e.getMessage());
		}
		
	}

	@Override
	public Dept detailDept(int deptno) {
		System.out.println("DeptDaoImplementation detailDept() is started");		
		Dept detailDept = null;
		
		try {
			detailDept = session.selectOne("com.oracle.oBootMyBatis01.DeptMapper.DetailDept", deptno);
		} catch (Exception e) {
			System.out.println("DeptDaoImplementation detailDept() e.getMessage() -> " + e.getMessage());
		}
	
		return detailDept;
	}

}
