package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.constants.ErrorCode;
import com.joalvarez.challengealkemy.data.dao.MovieDAO;
import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.dto.MovieRequestDTO;
import com.joalvarez.challengealkemy.data.mapper.MovieMapper;
import com.joalvarez.challengealkemy.data.model.Movie;
import com.joalvarez.challengealkemy.exception.generals.GenericException;
import com.joalvarez.challengealkemy.service.generals.GenericService;
import com.joalvarez.challengealkemy.service.interfaces.IMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Transactional
public class MovieService extends GenericService<MovieDAO, MovieMapper> implements IMovieService {

	private final GenreService genreService;
	private final CharacterService characterService;

	public MovieService(MovieDAO movieDAO, MovieMapper mapper, GenreService genreService,
						CharacterService characterService) {
		super(movieDAO, mapper);
		this.genreService = genreService;
		this.characterService = characterService;
	}

	@Override
	public MovieDTO findById(Long id) {
		var movie = this.dao.findById(id);

		if (Objects.isNull(movie)) {
			throw new GenericException(
				HttpStatus.BAD_REQUEST,
				ErrorCode.MOVIE_NOT_FOUND
			);
		}

		return this.mapper.toDTO(movie);
	}

	@Override
	public MovieDTO update(MovieDTO movieDTO, Long id) {
		return null;
	}

	@Override
	public MovieDTO save(MovieRequestDTO dto) {
		if (this.dao.existsByTitle(dto.title())) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.MOVIE_ALREADY_EXISTS
			);
		}

		var genres = new ArrayList<GenreDTO>();

		dto.genres()
			.forEach(genreId -> {

				var genre = this.genreService.findById(genreId);

				if (Objects.isNull(genre)) {
					this.info("Genre not found: {{}}", genreId);
					throw new GenericException(
						HttpStatus.NOT_FOUND,
						ErrorCode.GENRE_NOT_FOUND
					);
				}

				genres.add(genre);

			});

		var characters = new ArrayList<CharacterDTO>();

		dto.characters()
			.forEach(characterId -> {
				var character = this.characterService.findById(characterId);

				if (Objects.isNull(character)) {
					this.info("Character not found: {{}}", characterId);
					throw new GenericException(
						HttpStatus.NOT_FOUND,
						ErrorCode.CHARACTER_NOT_FOUND
					);
				}

				characters.add(character);
			});


		return this.mapper.toDTO(
			this.dao.save(
				this.mapper.fromDTO(
					this.mapper.toRequest(dto, genres, characters)
				)
			)
		);
	}

	@Override
	public Page<MovieDTO> findAll(Pageable pageable) {
		return this.dao.findAll(pageable)
			.map(this.mapper::toDTO);
	}

	@Override
	public MovieDTO addCharacter(Long id, Long idCharacter) {
		var movie = this.obtainMovie(id);

		movie.getCharacters()
			.forEach(character -> {
				if (character.getCharacterId().equals(idCharacter)) {
					throw new GenericException(
						HttpStatus.NOT_FOUND,
						ErrorCode.CHARACTER_ALREADY_EXISTS
					);
				}
			});

		var character = this.characterService.findById(idCharacter);

		if (Objects.isNull(character)) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.CHARACTER_NOT_FOUND
			);
		}

		movie.getCharacters().add(this.mapper.toCharacterEntity(character));

		return this.mapper.toDTO(this.dao.save(movie));
	}

	@Override
	public MovieDTO deleteCharacter(Long id, Long idCharacter) {
		var movie = this.obtainMovie(id);

		movie.getCharacters().removeIf(character -> character.getCharacterId().equals(idCharacter));

		this.dao.save(movie);

		return this.mapper.toDTO(movie);
	}

	/**
	 * Obtains a movie by its id.
	 *
	 * @param id the ID of the movie to obtain
	 * @return the obtained movie
	 */
	private Movie obtainMovie(Long id) {
		var movie = this.dao.findById(id);

		if (Objects.isNull(movie)) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.MOVIE_NOT_FOUND
			);
		}

		return movie;
	}
}
