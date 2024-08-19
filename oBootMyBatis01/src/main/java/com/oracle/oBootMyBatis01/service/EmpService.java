package com.oracle.oBootMyBatis01.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.DeptVO;
import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.EmpDept;
import com.oracle.oBootMyBatis01.model.MemberTwo;

public interface EmpService {
	int				totalEmp();
	List<Emp> 		listEmp(Emp emp);
	Emp 			detailEmp(int empno);
	int 			updateEmp(Emp emp);
	int 			deleteEmp(int empno);
	List<Emp> 		listManager();
	List<Dept> 		deptSelect();
	int 			insertEmp(Emp emp);
	int 			condTotalEmp(Emp emp);
	List<Emp> 		searchEmpList(Emp emp);
	List<EmpDept> 	listEmpDept();
	void 			insertDept(DeptVO deptVO);
	void 			selectListDept(HashMap<String, Object> map);
	Dept 			detailDept(int deptno);
	int 			memCount(String id);
	List<MemberTwo> listMember(MemberTwo memberTwo);
	String 			deptName(int deptno);		
	
}
