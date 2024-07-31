package com.oracle.obootDbConnect.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.oracle.obootDbConnect.domain.Member7;


//@Repository
public class MemoryMemberRepository implements MemberRepository {
	
	private static Map<Long, Member7> store = new HashMap<Long, Member7>();
	private static Long squence = 0L;
		
	
	@Override
	public Member7 save(Member7 member7) {
		
		System.out.println("MemoryMemberRepository save(Member7 member7) is started");
		
		member7.setId(++squence);
		store.put(member7.getId(), member7);			
		return null;
	}

	@Override
	public List<Member7> findAll() {		
		
		System.out.println("MemoryMemberRepository findAll() is started");
		
		List<Member7> memberList = new ArrayList<Member7>(store.values());		
		return memberList;
	}

}
