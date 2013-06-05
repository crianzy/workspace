package com.czy.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.czy.oa.service.DepartmentService;
import com.czy.oa.service.RoleService;
import com.czy.oa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1895824111764976679L;

	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	
	protected T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction(){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// 获取model 的类型信息
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) pt.getActualTypeArguments()[0];
		// 反射 new 一个实例
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public T getModel() {
		return model;
	}

}
