package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.constants.ErrorCode;
import com.joalvarez.challengealkemy.data.dao.GenreDAO;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.mapper.GenreMapper;
import com.joalvarez.challengealkemy.exception.generals.GenericException;
import com.joalvarez.challengealkemy.exception.generals.NotImplementedException;
import com.joalvarez.challengealkemy.service.generals.GenericService;
import com.joalvarez.challengealkemy.service.interfaces.IGenreService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreService extends GenericService<GenreDAO, GenreMapper> implements IGenreService {

	public GenreService(GenreDAO genreDAO, GenreMapper mapper) {
		super(genreDAO, mapper);
	}

	@Override
	public List<GenreDTO> findAll() {
		return this.dao.findAll().stream().map(this.mapper::toDTO).toList();
	}

	@Override
	public GenreDTO findById(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public GenreDTO update(GenreDTO genreDTO, Long id) {
		return null;
	}

	@Override
	public GenreDTO create(GenreDTO dto) {
		if (this.dao.existsByName(dto.getName())) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.GENRE_ALREADY_EXISTS
			);
		}

		return this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(dto)));
	}
}
