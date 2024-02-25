package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.constants.ErrorCode;
import com.joalvarez.challengealkemy.data.dao.MovieDAO;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.dto.MovieRequestDTO;
import com.joalvarez.challengealkemy.data.mapper.MovieMapper;
import com.joalvarez.challengealkemy.exception.generals.GenericException;
import com.joalvarez.challengealkemy.exception.generals.NotImplementedException;
import com.joalvarez.challengealkemy.service.generals.GenericService;
import com.joalvarez.challengealkemy.service.interfaces.IMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Service
@Transactional
public class MovieService extends GenericService<MovieDAO, MovieMapper> implements IMovieService {

	private final GenreService genreService;

	public MovieService(MovieDAO movieDAO, MovieMapper mapper, GenreService genreService) {
		super(movieDAO, mapper);
		this.genreService = genreService;
	}

	@Override
	public MovieDTO findById(Long id) {
		return this.mapper.toDTO(this.dao.findById(id));
	}

	@Override
	public MovieDTO update(MovieDTO movieDTO, Long id) {
		return null;
	}

	@Override
	public MovieDTO save(MovieRequestDTO dto) {
		if (this.dao.findByTitle(dto.title())) {
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
					this.info("Genre not found: {{}}",genreId);
					throw new GenericException(
						HttpStatus.NOT_FOUND,
						ErrorCode.GENRE_NOT_FOUND
					);
				}

				genres.add(genre);

			});

		var movieDTO = new MovieDTO();

		movieDTO.setImage(dto.image());
		movieDTO.setRating(dto.rating());
		movieDTO.setTitle(dto.title());
		movieDTO.setCreationDate(LocalDate.now());
		movieDTO.setGenres(genres);

		return this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(movieDTO)));
	}

	@Override
	public Page<MovieDTO> findAll(Pageable pageable) {
		return this.dao.findAll(pageable)
			.map(this.mapper::toDTO);
	}
}
