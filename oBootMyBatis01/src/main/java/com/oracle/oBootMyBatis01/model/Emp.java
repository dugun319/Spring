package com.oracle.oBootMyBatis01.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Emp {
	@Id
	private int		empno;
	
	@NotEmpty(message = "Namespace is required")
	private String	ename;
	private String	job;
	private int		mgr;
	private String	hiredate;
	private int		sal;
	private int		comm;
	private int		deptno;
	
	// For search
	private String	search;
	private String	keyword;
	private String	pageNum;
	private int		start;
	private int		end;
	
	// Information of PAGE
	private String	currentPage;
	
}
