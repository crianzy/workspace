package com.czy.oa.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	public void delete(Serializable id);
	
	public List<T> findAll();
	
	public T getById(Serializable id);
	
	public List<T> getByids(Serializable [] ids);
	
	public void save(T t);
	
	public void update(T t);
}
