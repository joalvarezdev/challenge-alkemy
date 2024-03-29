package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;

public class RoleDTO implements BaseDTO {

	private Long roleId;
	private String name;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
