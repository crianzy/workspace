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

	/** 列表 */
	public String list() throws Exception {
		List<Department> departments = departmentService.findAll();
		ActionContext.getContext().put("departments", departments);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {
		departmentService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Department department = departmentService.getById(model.getId());
		model.setName(department.getName());
		model.setDescription(department.getDescription());
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		departmentService.update(model);
		return "toList";
	}

	public Department getModel() {
		return model;
	}
}
