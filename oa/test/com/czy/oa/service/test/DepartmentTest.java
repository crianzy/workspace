package com.czy.oa.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.czy.oa.domain.Department;
import com.czy.oa.service.DepartmentService;

public class DepartmentTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private DepartmentService departmentService = (DepartmentService) ac.getBean("departmentServiceImpl"); 

	@Test
	public void findTopListTest() {
		List<Department> departmentList1 = departmentService.findTopList();
		for (Department top : departmentList1) {
			System.out.println( top.getId()+"   "+top.getName());
		}
	}
	
	@Test
	public void findChildrenListTest() {
		List<Department> departmentList1 = departmentService.findChildren((long)1);
		for (Department child : departmentList1) {
			System.out.println(child.getName());
		}
	}
	
	@Test
	public void DepartmentTreeView() {
		
	}

}
