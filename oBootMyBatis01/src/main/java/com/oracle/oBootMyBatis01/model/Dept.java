package com.oracle.oBootMyBatis01.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Dept {
	@Id
	private int 	deptno;
	private String 	dname;
	private String 	loc;
}
