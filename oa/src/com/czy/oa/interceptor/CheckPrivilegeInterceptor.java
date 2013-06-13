package com.czy.oa.interceptor;

import com.czy.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1091319974397411665L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获得当前用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		// 获取当前访问的url 并去掉前缀与后缀
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = null;
		if (namespace.equals("/")) {
			privilegeUrl = namespace + actionName;
		} else {
			privilegeUrl = namespace + "/" + actionName;
		}

		// 要去掉开头的'/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}
		//如果用户未登录
		if(user == null){
			if(privilegeUrl.startsWith("userAction_login")){
				return invocation.invoke();
			}else{
				return "loginUI";
			}
		}else{//如果已登录
			if(user.hasPrivilegeByUrl(privilegeUrl)){
				return invocation.invoke();
			}else{
				return "noPrivilegeError";
			}
		}
	}

}
