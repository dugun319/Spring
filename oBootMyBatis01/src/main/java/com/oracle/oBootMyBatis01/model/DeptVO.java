package com.oracle.oBootMyBatis01.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeptVO {
	
	// Input
	private int		deptno;
	private String	dname;
	private String	loc;
	
	// Output
	private int		odeptno;
	private String	odname;
	private String	oloc;
	

}
