package com.joalvarez.challengealkemy.service.interfaces;

import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.dto.MovieRequestDTO;
import com.joalvarez.challengealkemy.exception.generals.NotImplementedException;
import com.joalvarez.challengealkemy.service.generals.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMovieService extends IBaseService<MovieDTO, Long> {

	default List<MovieDTO> findAll() {
		throw new NotImplementedException();
	}

	default MovieDTO create(MovieDTO movieDTO) {
		throw new NotImplementedException();
	}

    MovieDTO save(MovieRequestDTO dto);

	Page<MovieDTO> findAll(Pageable pageable);

	MovieDTO addCharacter(Long id, Long idCharacter);

	MovieDTO deleteCharacter(Long id, Long idCharacter);
}
