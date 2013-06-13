package com.czy.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.czy.oa.domain.Privilege;
import com.czy.oa.service.PrivilegeService;

public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		// �õ�serviceʵ������
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(application);
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");

		// ׼�����ж���Ȩ�޵ļ��ϣ������˵���
		List<Privilege> topPrivilegeList = privilegeService.findTop();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("-- ��׼���ö���Ȩ�޵����� --");
		
		// ׼������Ȩ�޵�URL����
		List<String> allPrivilegeUrlList = privilegeService.getAllprivilegeUrl();
		application.setAttribute("allPrivilegeUrlList", allPrivilegeUrlList);
		System.out.println("-- ��׼���ö�urlȨ������ --");

	}

}
