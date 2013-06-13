package com.czy.oa.service;

import java.io.Serializable;
import java.util.List;

import com.czy.oa.base.BaseDao;
import com.czy.oa.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege> {
	
	public List<Privilege> findTop();
	
	public List<Privilege> findChildren(Serializable id);

	public List<String> getAllprivilegeUrl();

}
