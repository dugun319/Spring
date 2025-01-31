package com.oracle.oBootMyBatis01.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Member2")
public class Member {	
	
	@Id
	private Long	id;
	private String	name;
	private String	password;
	@Column(nullable = false, columnDefinition = "date default sysdate")
	private Date	reg_date = new Date();

}
