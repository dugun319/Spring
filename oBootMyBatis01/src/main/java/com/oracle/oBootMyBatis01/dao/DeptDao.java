package com.oracle.oBootMyBatis01.dao;

import java.util.HashMap;
import java.util.List;
import com.oracle.oBootMyBatis01.model.Dept;
import com.oracle.oBootMyBatis01.model.DeptVO;

public interface DeptDao {
	
	List<Dept>	deptSelect();
	void 		insertDept(DeptVO deptVO);
	void 		selectListDept(HashMap<String, Object> map);
	Dept 		detailDept(int deptno);
	
}
