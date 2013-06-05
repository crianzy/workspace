package com.czy.oa.enviroment.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.czy.oa.domain.Department;
import com.czy.oa.domain.Role;
import com.czy.oa.domain.User;
import com.czy.oa.util.HibernateUtil;

public class TestHibernate {

	@Test
	public void testDomain() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ------------------------------
		// ------------------------------
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testDepartRoleUser() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ------------------------------
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		user1.setName("user1");
		user1.setLoginName("loginName1");
		
		user2.setName("user2");
		user2.setLoginName("loginName2");
		
		user3.setName("user3");
		user3.setLoginName("loginName3");

		Department department1 = new Department();
		Department department2 = new Department();
		Department department3 = new Department();

		department1.setName("department 1");
		department1.setDescription("department 1");
		department1.getChildren().add(department2);
		department1.getChildren().add(department3);
		department1.getUsers().add(user1);

		department2.setName("department 2");
		department2.setDescription("department 2");
		department2.setParent(department1);
		department2.getUsers().add(user2);
		
		department3.setName("department 3");
		department3.setDescription("department 3");
		department3.setParent(department1);
		department3.getUsers().add(user3);

		Role role1 = new Role();
		Role role2 = new Role();
		role1.setName("role1");
		role2.setName("role2");
		
		user3.getRoles().add(role1);
		user3.getRoles().add(role2);
		
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.save(department1);
		session.save(department2);
		session.save(department3);
		session.save(role1);
		session.save(role2);

		// ------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
