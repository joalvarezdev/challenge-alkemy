package com.joalvarez.challengealkemy.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.dto.MovieRequestDTO;
import com.joalvarez.challengealkemy.data.mapper.generals.BaseMapper;
import com.joalvarez.challengealkemy.data.model.Character;
import com.joalvarez.challengealkemy.data.model.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieMapper extends BaseMapper<MovieDTO, Movie> {

	private final CharacterMapper characterMapper;

	public MovieMapper(ObjectMapper objectMapper, CharacterMapper characterMapper) {
		super(objectMapper);
		this.characterMapper = characterMapper;
	}

	public MovieDTO toRequest(MovieRequestDTO dto, List<GenreDTO> genres, List<CharacterDTO> characters) {
		var entity = new MovieDTO();

		entity.setImage(dto.image());
		entity.setRating(dto.rating());
		entity.setTitle(dto.title());
		entity.setCreationDate(LocalDate.now());
		entity.setGenres(genres);
		entity.setCharacters(characters);

		return entity;
	}

	public Character toCharacterEntity(CharacterDTO dto) {
		return this.characterMapper.fromDTO(dto);
	}
}
