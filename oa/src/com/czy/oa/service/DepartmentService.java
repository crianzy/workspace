package com.czy.oa.service;

import java.io.Serializable;
import java.util.List;

import com.czy.oa.base.BaseDao;
import com.czy.oa.domain.Department;

public interface DepartmentService extends BaseDao<Department> {

	public List<Department> findTopList();
	
	public List<Department> findChildren(Serializable id);
}
