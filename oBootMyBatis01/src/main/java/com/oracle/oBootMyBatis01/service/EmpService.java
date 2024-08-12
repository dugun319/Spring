package com.oracle.oBootMyBatis01.service;

import java.util.List;

import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.Emp;

public interface EmpService {
	int			totalEmp();
	List<Emp> 	listEmp(Emp emp);
	Emp 		detailEmp(int empno);
	int 		updateEmp(Emp emp);
	int 		deleteEmp(int empno);
	List<Emp> 	listManager();
	List<Dept> 	deptSelect();
	int 		insertEmp(Emp emp);
	int 		condTotalEmp(Emp emp);
	List<Emp> 	searchEmpList(Emp emp);			
}
