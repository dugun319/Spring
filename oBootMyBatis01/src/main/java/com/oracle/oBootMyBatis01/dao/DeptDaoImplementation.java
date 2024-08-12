package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.oracle.oBootMyBatis01.model.Dept;

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

}
