package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import com.oracle.oBootMyBatis01.model.Emp;

public interface EmpDao {

	List<Emp> 	listEmp(Emp emp);
	// 모든 직원의 수	
	int			totalEmp();
	Emp			detailEmp(int empno);
	int  		updateEmp(Emp emp);
}
