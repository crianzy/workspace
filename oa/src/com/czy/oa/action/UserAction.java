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

	/** 列表 */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 添加department 树状展现
		List<Department> departmentListTop = departmentService.findTopList();
		ActionContext.getContext().put("departmentList",
				DepartmentUtils.getAllDepartmentsTreeView(departmentListTop));
		// 添加角色的展现
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 添加 */
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

	/** 修改页面 */
	public String editUI() throws Exception {
		// 添加department 树状展现
		List<Department> departmentListTop = departmentService.findTopList();
		ActionContext.getContext().put("departmentList",
				DepartmentUtils.getAllDepartmentsTreeView(departmentListTop));
		// 添加角色的展现
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {// 注意要不为空
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles().size() > 0) {// 注意岗位不能为空
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		// >> 普通属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 所属部门
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		// >> 关联的岗位
		List<Role> roleList = roleService.getByids(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);
		return "toList";
	}

	/** 初始化密码 */
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
