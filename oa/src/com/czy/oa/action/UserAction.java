package com.czy.oa.action;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	private static final long serialVersionUID = -2057587610472404698L;
	
	/** �б� */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		for (User user : userList) {
			System.out.println(user.getName()+"    "+user.getDepartment().getName());
		}
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** ��� */
	public String add() throws Exception {
		userService.save(model);
		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		return "editUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		userService.update(model);
		return "toList";
	}

}
