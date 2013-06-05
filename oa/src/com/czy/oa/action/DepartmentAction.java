package com.czy.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.Department;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private static final long serialVersionUID = 2660162162464983140L;

	/** �б� */
	public String list() throws Exception {
		List<Department> departments = departmentService.findAll();
		ActionContext.getContext().put("departments", departments);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** ��� */
	public String add() throws Exception {
		departmentService.save(model);
		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		Department department = departmentService.getById(model.getId());
		model.setName(department.getName());
		model.setDescription(department.getDescription());
		return "editUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		departmentService.update(model);
		return "toList";
	}

	public Department getModel() {
		return model;
	}
}
