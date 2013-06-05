package com.czy.oa.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.Department;
import com.czy.oa.domain.Role;
import com.czy.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -2057587610472404698L;

	private Long departmentId;
	private Long[] roleIds;

	/** �б� */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().put("roleList", roleList);
		return "addUI";
	}

	/** ��� */
	public String add() throws Exception {
		Department department = departmentService.getById(departmentId);

		List<Role> roleList = null;
		try {
			roleList = roleService.getByids(roleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.setDepartment(department);
		model.setRoles(new HashSet<Role>(roleList));
		model.setPassword("1234");

		userService.save(model);
		return "toList";
	}

	/** �޸�ҳ�� */
	@SuppressWarnings("unchecked")
	public String editUI() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().put("roleList", roleList);
		User user = userService.getById(model.getId());
		departmentId = user.getDepartment().getId();
		List <Role> roles =  new ArrayList<Role>(user.getRoles());
		//TODO ����ת����
		Long [] roleidss = new Long[roles.size()];
		for(int i = 0;i<roles.size();i++){
			roleidss[i] = roles.get(i).getId();
		}
		setRoleIds(roleidss);
		model.setName(user.getName());
		model.setDescription(user.getDescription());
		model.setEmail(user.getEmail());
		model.setGender(user.getGender());
		model.setLoginName(user.getLoginName());
		model.setPhoneNumber(user.getPhoneNumber());
		return "editUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = userService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		// >> ��ͨ����
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> ��������
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		// >> �����ĸ�λ
		List<Role> roleList = roleService.getByids(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3�����µ����ݿ�
		userService.update(user);
		return "toList";
	}

	// -------------------------------------------------
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}


}
