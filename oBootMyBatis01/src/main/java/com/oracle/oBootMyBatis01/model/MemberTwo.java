package com.oracle.oBootMyBatis01.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberTwo {
	private	String 	id;
	private String 	name;
	private String 	password;
	private Date	reg_date;
}
