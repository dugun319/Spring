package com.oracle.oBootMyBatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis01.dao.DeptDao;
import com.oracle.oBootMyBatis01.dao.EmpDao;
import com.oracle.oBootMyBatis01.dao.MemberTwoDao;
import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.DeptVO;
import com.oracle.oBootMyBatis01.model.Emp;
import com.oracle.oBootMyBatis01.model.EmpDept;
import com.oracle.oBootMyBatis01.model.MemberTwo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImplementation implements EmpService {
	
	// 동일한 Interface를 상속받은 중복되는 DAO 선언은 안 되지만, 
	// 각각의 Interface를 상속받는 다수의 DAO 선언은 가능함(갯수무관) 
	private final EmpDao empDao;
	private final DeptDao deptDao;
	private final MemberTwoDao memberTwoDao;
	
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

	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpServiceImplementation deleteEmp() is started");
		int deleteCnt = empDao.deleteEmp(empno);		
		System.out.println("EmpServiceImplementation deleteCnt() deleteCnt -> " + deleteCnt);
		
		return deleteCnt;
	}

	@Override
	public List<Emp> listManager() {
		
		List<Emp> empMgrList = empDao.listManager();
		System.out.println("EmpServiceImplementation listManager() empMgrList.size() -> " + empMgrList.size());
		
		return empMgrList;
	}

	@Override
	public List<Dept> deptSelect() {
		
		List<Dept> deptList = deptDao.deptSelect();
		System.out.println("EmpServiceImplementation deptSelect() deptList.size() -> " + deptList.size());
		
		return deptList;
	}

	@Override
	public int insertEmp(Emp emp) {
		
		int insertResult = empDao.insertEmp(emp);
		System.out.println("EmpServiceImplementation insertEmp(Emp emp) insertResult -> " + insertResult);
		
		return insertResult;
	}

	@Override
	public int condTotalEmp(Emp emp) {
		System.out.println("EmpServiceImplementation condTotalEmp(Emp emp) is started");
		
		int totalEmp = empDao.condTotalEmp(emp);
		System.out.println("EmpServiceImplementation condTotalEmp(Emp emp) totalEmp -> " + totalEmp);
		
		return totalEmp;
	}

	@Override
	public List<Emp> searchEmpList(Emp emp) {
		
		List<Emp> searchEmpList = empDao.searchEmpList(emp);		
		System.out.println("EmpServiceImplementation searchEmpList.size() -> " + searchEmpList.size());

		return searchEmpList;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		
		List<EmpDept> listEmpDept = empDao.listEmpDept();
		System.out.println("EmpServiceImplementation listEmpDept() listEmpDept.size() -> " + listEmpDept.size());

		
		return listEmpDept;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImplementation insertDept() is started");
		deptDao.insertDept(deptVO);
		
	}

	@Override
	public void selectListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImplementation selectListDept() is started");
		deptDao.selectListDept(map);		
	}

	@Override
	public Dept detailDept(int deptno) {
		System.out.println("EmpServiceImplementation detailDept() is started");		
		Dept detailDept = deptDao.detailDept(deptno);
		
		return detailDept;
	}

	@Override
	public int memCount(String id) {
		int memCount = memberTwoDao.memCount(id);
		System.out.println("EmpServiceImplementation memCount() memCount -> " + memCount);
		return memCount;
	}

	@Override
	public List<MemberTwo> listMember(MemberTwo memberTwo) {
		
		List<MemberTwo> listMember = memberTwoDao.listMember(memberTwo);
		System.out.println("EmpServiceImplementation listMember() listMember.size() -> " + listMember.size());
		return listMember;
	}

	@Override
	public String deptName(int deptno) {
		String deptName = empDao.deptName(deptno);		
		System.out.println("EmpServiceImplementation deptName() deptName -> " + deptName);
		
		return deptName;
	}

	@Override
	public int transactionInsertUpdate() {
		System.out.println("EmpServiceImplementation transactionInsertUpdate() is started ");
		
		//return memberTwoDao.transactionInsertUpdate1(); transaction X
		return memberTwoDao.transactionInsertUpdate2();
	}
	

}
