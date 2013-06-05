package com.czy.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 实体岗位
 * @author chen9_000
 *
 */
public class Role {

	private Long id;
	private String name;
	private String description;

	/** 该岗位下的所有用户*/
	private Set<User> users = new HashSet<User>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
