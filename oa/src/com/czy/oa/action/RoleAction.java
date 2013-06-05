package com.czy.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -36101066303981220L;

	/** �б� */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		System.out.println("list = " + roleList);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** ��� */
	public String add() throws Exception {
		Role t_role = new Role();
		t_role.setName(model.getName());
		t_role.setDescription(model.getDescription());
		roleService.save(t_role);
		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		Role t_role = roleService.getById(model.getId());
		model.setName(t_role.getName());
		model.setDescription(t_role.getDescription());
		return "editUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		Role t_role = roleService.getById(model.getId());
		t_role.setName(model.getName());
		t_role.setDescription(model.getDescription());
		roleService.update(t_role);
		return "toList";
	}

	// ---------------------------

}
