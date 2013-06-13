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
		// 得到service实例对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(application);
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");

		// 准备所有顶级权限的集合（顶级菜单）
		List<Privilege> topPrivilegeList = privilegeService.findTop();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("-- 已准备好顶级权限的数据 --");
		
		// 准备所有权限的URL集合
		List<String> allPrivilegeUrlList = privilegeService.getAllprivilegeUrl();
		application.setAttribute("allPrivilegeUrlList", allPrivilegeUrlList);
		System.out.println("-- 已准备好顶url权限数据 --");

	}

}
