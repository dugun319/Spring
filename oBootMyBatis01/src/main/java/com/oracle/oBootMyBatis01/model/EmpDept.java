package com.oracle.oBootMyBatis01.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EmpDept {
	
	// From EMP
	@Id
	private int		empno;
	private String	ename;
	private String	job;
	private int		mgr;
	private String	hiredate;
	private int		sal;
	private int		comm;
	private int		deptno;
		
	// From DEPT
	// Join column 이 많지 않을 경우, 원 DTO에 삽입하는 것도 괜찮음.
	// 2 column 밖에 없지만 많다고 가정하고 새로 EmpDept 생성
	private String 	dname;
	private String 	loc;	

}
