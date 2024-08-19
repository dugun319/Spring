package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.EmpDept;

public interface EmpDao {

	List<Emp> 		listEmp(Emp emp);
	// 모든 직원의 수	
	int				totalEmp();
	Emp				detailEmp(int empno);
	int  			updateEmp(Emp emp);
	int 			deleteEmp(int empno);
	List<Emp>  		listManager();
	int 			insertEmp(Emp emp);
	int 			condTotalEmp(Emp emp);
	List<Emp> 		searchEmpList(Emp emp);
	List<EmpDept> 	listEmpDept();
	String 			deptName(int deptno);	
}
