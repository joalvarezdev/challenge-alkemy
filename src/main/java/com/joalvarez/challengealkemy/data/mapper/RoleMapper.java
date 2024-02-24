package com.joalvarez.challengealkemy.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.dto.RoleDTO;
import com.joalvarez.challengealkemy.data.mapper.generals.BaseMapper;
import com.joalvarez.challengealkemy.data.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends BaseMapper<RoleDTO, Role> {

	public RoleMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
