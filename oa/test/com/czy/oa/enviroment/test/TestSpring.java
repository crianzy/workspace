package com.czy.oa.enviroment.test;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.czy.oa.domain.Role;
import com.czy.oa.service.RoleService;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestSpring {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	public void testSessinoFactory() {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		ComboPooledDataSource datasource = (ComboPooledDataSource) ac
				.getBean("dataSource");

		System.out.println(sf);
		System.out.println(datasource);
	}

	@Test
	public void testBaseDao() {
		/*UserDao userDao = (UserDao) ac.getBean("userDaoImpl");
		System.out.println(userDao);*/
	}
	
	@Test
	public void testGetByIds(){
		
	}
}
