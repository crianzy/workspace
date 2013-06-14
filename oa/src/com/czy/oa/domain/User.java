package com.czy.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable{

	private static final long serialVersionUID = -5698624995617865854L;
	private Long id;
	private String name;
	private String loginName;
	private String password;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;

	private Department department;

	private Set<Role> roles = new HashSet<Role>();
	
	/**
	 * 根据 权限名称判断是有该权限
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		if(isAdmin()){
			return true;
		}
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if(privilege.getName().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean hasPrivilegeByUrl(String url){
		if(isAdmin()){
			return true;
		}
		
		//如果已UI 结尾则去掉UI （addUI  add  是同一个权限）
		if(url.endsWith("UI")){
			url = url.substring(0, url.length()-2);
		}
		List<String> allPrivilegeUrlList = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrlList");
		//如果不包含 当前url 及  不需要控制权限的 url  例 注销 主页等。
		if(!allPrivilegeUrlList.contains(url)){
			return true;
		}
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if(url.equals(privilege.getUrl())){//小心空指针异常 权限表中有些url 是空的 不能写错privilege.getUrl().equals(url)
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是 超级管理员
	 * @return
	 */
	public boolean isAdmin(){
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
