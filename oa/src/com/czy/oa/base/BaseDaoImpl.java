package com.czy.oa.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;

	public BaseDaoImpl() {
		//通过反射获取泛型的具体类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void delete(Serializable id) {
		Object o = getSession().get(clazz, id);
		getSession().delete(o);
	}

	public List<T> findAll() {
		List<T> list = null;
		String hql = " FROM " + clazz.getSimpleName();
		list = getSession().createQuery(hql).list();
		return list;
	}

	public T getById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByids(Serializable[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		List<T> list = null;
		String hql = " FROM " + clazz.getSimpleName() + " WHERE  id IN(:ids)";
		list = getSession().createQuery(hql)//
				.setParameter("ids", ids)//
				.list();
		return list;
	}

	public void save(T t) {
		getSession().save(t);
	}

	public void update(T t) {
		getSession().update(t);
	}

	/**
	 * 获取当前session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
