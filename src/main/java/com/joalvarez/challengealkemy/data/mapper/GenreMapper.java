package com.joalvarez.challengealkemy.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.mapper.generals.BaseMapper;
import com.joalvarez.challengealkemy.data.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper extends BaseMapper<GenreDTO, Genre> {

	public GenreMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
