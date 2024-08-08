package com.oracle.oBootMyBatis01.service;

import org.springframework.stereotype.Service;
import com.oracle.oBootMyBatis01.dao.EmpDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImplementation implements EmpService {
	
	
	private final EmpDao empDao;
	
	@Override
	public int totalEmp() {
		
		System.out.println("EmpServiceImplementation totalEmp() is started");
		
		int totEmpCnt = empDao.totalEmp();
		System.out.println("EmpServiceImplementation totalEmp() totEmpCnt -> " + totEmpCnt);
		
		return totEmpCnt;
	}

}
