package com.czy.oa.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.czy.oa.base.BaseDaoImpl;
import com.czy.oa.domain.Department;
import com.czy.oa.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findTopList() {
		Session session = getSession();
		String hql = "FROM Department department WHERE department.parent IS NULL";
		List<Department> departmentList = session.createQuery(hql).list();
		return departmentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findChildren(Serializable id) {
		Session session = getSession();
		String hql = "FROM Department department WHERE department.parent.id = ? ";
		List<Department> departmentList = session.createQuery(hql)//
											.setParameter(0, id)//
											.list();
		return departmentList;
	}

}
