package com.joalvarez.challengealkemy.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.data.mapper.generals.BaseMapper;
import com.joalvarez.challengealkemy.data.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper extends BaseMapper<CharacterDTO, Character> {

	public CharacterMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}

	public Character update(CharacterDTO dto) {
		var entity = new Character();

		return entity;
	}
}
