package com.oracle.oBootMyBatis01.dao;

import java.util.List;

import com.oracle.oBootMyBatis01.model.MemberTwo;

public interface MemberTwoDao {
	int				memCount(String id);
	List<MemberTwo> listMember(MemberTwo memberTwo);
	int 			transactionInsertUpdate1(); 	//transaction X
	int 			transactionInsertUpdate2(); 	//transaction O
	
}
