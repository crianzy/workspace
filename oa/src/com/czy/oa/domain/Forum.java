package com.czy.oa.domain;

import java.io.Serializable;

/**
 * ���
 * @author chen
 *
 */
public class Forum implements Serializable{

	private static final long serialVersionUID = -1368861124984902738L;
	private Long id;
	private String name;
	private String description;
	private int position;// �����õ�λ�ú�

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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
