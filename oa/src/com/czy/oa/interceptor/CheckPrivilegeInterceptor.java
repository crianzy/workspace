package com.czy.oa.interceptor;

import com.czy.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1091319974397411665L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// ��õ�ǰ�û�
		User user = (User) ActionContext.getContext().getSession().get("user");
		// ��ȡ��ǰ���ʵ�url ��ȥ��ǰ׺���׺
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = null;
		if (namespace.equals("/")) {
			privilegeUrl = namespace + actionName;
		} else {
			privilegeUrl = namespace + "/" + actionName;
		}

		// Ҫȥ����ͷ��'/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}
		//����û�δ��¼
		if(user == null){
			if(privilegeUrl.startsWith("userAction_login")){
				return invocation.invoke();
			}else{
				return "loginUI";
			}
		}else{//����ѵ�¼
			if(user.hasPrivilegeByUrl(privilegeUrl)){
				return invocation.invoke();
			}else{
				return "noPrivilegeError";
			}
		}
	}

}
