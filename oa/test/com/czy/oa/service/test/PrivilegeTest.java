package com.czy.oa.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.czy.oa.domain.Department;
import com.czy.oa.domain.Privilege;
import com.czy.oa.service.PrivilegeService;

public class PrivilegeTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl"); 

	@Test
	public void findTopListTest() {
		List<Privilege> PrivilegeList = privilegeService.findTop();
		for (Privilege privilege : PrivilegeList) {
			System.out.println(" "+privilege.getName());
			List<Privilege> children = new ArrayList<Privilege>(privilege.getChildren());
			for (Privilege child : children) {
				System.out.println("        "+child.getName());
			}
		}
	}
	
	@Test
	public void findChildrenListTest() {
		List<Privilege> PrivilegeList = privilegeService.findChildren((long)1);
		for (Privilege privilege : PrivilegeList) {
			System.out.println(" "+privilege.getName());
		}
	}
	
	@Test
	public void DepartmentTreeView() {
		
	}

}
