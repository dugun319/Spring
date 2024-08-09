package com.oracle.oBootMyBatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.oracle.oBootMyBatis01.dao.EmpDao;
import com.oracle.oBootMyBatis01.model.Emp;

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

	@Override
	public List<Emp> listEmp(Emp emp) {
		
		System.out.println("EmpServiceImplementation listEmp is started");
		List<Emp> empList = empDao.listEmp(emp);
		System.out.println("EmpServiceImplementation listEmp is started empList.size() -> " + empList.size());
		
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		
		System.out.println("EmpServiceImplementation detailEmp() is started");		
		Emp detailEmp = empDao.detailEmp(empno); 
		System.out.println("EmpServiceImplementation detailEmp() detailEmp -> " + detailEmp);
		
		return detailEmp;
	}

	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpServiceImplementation updateEmp() is started");
		int updateCnt = empDao.updateEmp(emp);		
		System.out.println("EmpServiceImplementation updateEmp() updateCnt -> " + updateCnt);
		
		return updateCnt;
	}

}
