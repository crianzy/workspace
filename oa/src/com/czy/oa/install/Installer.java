package com.czy.oa.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.czy.oa.domain.Privilege;
import com.czy.oa.domain.User;

@Controller
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		// ====================================================
		// һ����������Ա
		User user = new User();
		user.setName("��������Ա");
		user.setLoginName("admin");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);
		// ===================================================
		// ����Ȩ������
		Privilege menu, menu1, menu2, menu3, menu4, menu5;

		menu = new Privilege("ϵͳ����", null, "FUNC20082.gif", null);
		menu1 = new Privilege("��λ����", "roleAction_list", null, menu);
		menu2 = new Privilege("���Ź���", "departmentAction_list", null, menu);
		menu3 = new Privilege("�û�����", "userAction_list", null, menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("��λ�б�", "roleAction_list", null, menu1));
		session.save(new Privilege("��λɾ��", "roleAction_delete", null, menu1));
		session.save(new Privilege("��λ���", "roleAction_add", null, menu1));
		session.save(new Privilege("��λ�޸�", "roleAction_edit", null, menu1));

		session.save(new Privilege("�����б�", "departmentAction_list", null, menu2));
		session.save(new Privilege("����ɾ��", "departmentAction_delete", null,
				menu2));
		session.save(new Privilege("�������", "departmentAction_add", null, menu2));
		session.save(new Privilege("�����޸�", "departmentAction_edit", null, menu2));

		session.save(new Privilege("�û��б�", "userAction_list", null, menu3));
		session.save(new Privilege("�û�ɾ��", "userAction_delete", null, menu3));
		session.save(new Privilege("�û����", "userAction_add", null, menu3));
		session.save(new Privilege("�û��޸�", "userAction_edit", null, menu3));
		session.save(new Privilege("�û���ʼ������", "userAction_initPassword", null,
				menu3));

		// -------------------------

		menu = new Privilege("���Ͻ���", null, "FUNC20064.gif", null);
		menu1 = new Privilege("��̳����", "forumManageAction_list", null, menu);
		menu2 = new Privilege("��̳", "forumAction_list", null, menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		// -------------------------
		menu = new Privilege("������ת", null, "FUNC20057.gif", null);
		menu1 = new Privilege("�������̹���", "processDefinitionAction_list", null,
				menu);
		menu2 = new Privilege("����ģ�����", "applicationTemplateAction_list", null,
				menu);
		menu3 = new Privilege("�������", "flowAction_applicationTemplateList",
				null, menu);
		menu4 = new Privilege("��������", "flowAction_myTaskList", null, menu);
		menu5 = new Privilege("�ҵ������ѯ", "flowAction_myApplicationList", null,
				menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("����ִ�а�װ...");

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

		System.out.println("== ��װ��� ==");
	}

}
