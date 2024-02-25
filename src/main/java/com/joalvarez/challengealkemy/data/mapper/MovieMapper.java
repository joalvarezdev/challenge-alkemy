package com.joalvarez.challengealkemy.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.mapper.generals.BaseMapper;
import com.joalvarez.challengealkemy.data.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper extends BaseMapper<MovieDTO, Movie> {

	public MovieMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
