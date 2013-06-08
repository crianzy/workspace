package com.czy.oa.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.czy.oa.base.BaseAction;
import com.czy.oa.domain.Privilege;
import com.czy.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -36101066303981220L;
	private Long[] privilegeIds;

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {
		Role t_role = new Role();
		t_role.setName(model.getName());
		t_role.setDescription(model.getDescription());
		roleService.save(t_role);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Role t_role = roleService.getById(model.getId());
		model.setName(t_role.getName());
		model.setDescription(t_role.getDescription());
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Role t_role = roleService.getById(model.getId());
		t_role.setName(model.getName());
		t_role.setDescription(model.getDescription());
		roleService.update(t_role);
		return "toList";
	}

	/** 设置权限页面 */
	public String setPrivilegeUI() throws Exception {
		// 准备数据
		// 设置当前角色名称
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		// 准备回显数据
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege privilege : role.getPrivileges()) {
			privilegeIds[index++] = privilege.getId();
		}
		return "setPrivilegeUI";
	}

	/** 设置权限 */
	public String setPrivilege() throws Exception {
		// 从数据库中取出原对象
		Role role = roleService.getById(model.getId());
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 更新到数据库中
		roleService.update(role);
		return "toList";
	}

	// ---------------------------
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
