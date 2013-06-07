package com.czy.oa.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.Department;
import com.czy.oa.domain.Role;
import com.czy.oa.domain.User;
import com.czy.oa.util.DepartmentUtils;
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
		// ���department ��״չ��
		List<Department> departmentListTop = departmentService.findTopList();
		ActionContext.getContext().put("departmentList",
				DepartmentUtils.getAllDepartmentsTreeView(departmentListTop));
		// ��ӽ�ɫ��չ��
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
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
		String passwdMD5 = DigestUtils.md5Hex("1234");
		model.setPassword(passwdMD5);
		userService.save(model);
		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ���department ��״չ��
		List<Department> departmentListTop = departmentService.findTopList();
		ActionContext.getContext().put("departmentList",
				DepartmentUtils.getAllDepartmentsTreeView(departmentListTop));
		// ��ӽ�ɫ��չ��
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// ׼�����Ե�����
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {// ע��Ҫ��Ϊ��
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles().size() > 0) {// ע���λ����Ϊ��
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
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

	/** ��ʼ������ */
	public String initPassword() throws Exception {
		User user = userService.getById(model.getId());
		String passwdMD5 = DigestUtils.md5Hex("1234");
		user.setPassword(passwdMD5);
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
