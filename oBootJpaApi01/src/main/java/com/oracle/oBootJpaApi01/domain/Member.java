package com.oracle.oBootJpaApi01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name =  "member3")
// 1. Sequence 
//   1) 객체 nm : member_seq_gen5
//   2) DB  nm : member_seq_generator5
//   3) 초기 -> 1 , 할당 ->1
@SequenceGenerator( 
		name = "member_seq_gen3",
		sequenceName = "member_seq_generator3", //매핑할 DB 시퀀스 이름
		initialValue = 1, 
		allocationSize = 1
		)

public class Member {
	// PK --> member_seq_gen5 , nm -> member_id
	@Id
	@GeneratedValue(
	         strategy = GenerationType.SEQUENCE,
	         generator = "member_seq_gen3"
	        )
	@Column(name = "member_id")
	private Long   id;
	@NotEmpty
	//  nm -> userName
	@Column(name = "username")
	private String name;
	private Long   sal;

	@ManyToOne
	@JoinColumn(name =  "team_id")
	private Team team;

}
