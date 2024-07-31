package com.oracle.obootDbConnect.repository;
import java.util.List;
import com.oracle.obootDbConnect.domain.Member7;

public interface 	MemberRepository {
	
	Member7			save(Member7 member7);
	List<Member7>	findAll();

}
