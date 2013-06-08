package com.czy.oa.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.czy.oa.base.BaseDaoImpl;
import com.czy.oa.domain.Privilege;
import com.czy.oa.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findTop() {
		Session session = getSession();
		List<Privilege> top = null;
		String hql = "FROM Privilege privilege WHERE privilege.parent IS NULL";
		top = session.createQuery(hql).list();
		return top;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findChildren(Serializable id) {
		Session session = getSession();
		List<Privilege> top = null;
		top = session.createQuery("FROM Privilege privilege WHERE privilege.parent.id =?")//
					.setParameter(0, id)//
					.list();
		return top;
	}

}
