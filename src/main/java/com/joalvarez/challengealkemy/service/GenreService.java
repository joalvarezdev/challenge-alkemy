package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.data.dao.GenreDAO;
import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.data.mapper.GenreMapper;
import com.joalvarez.challengealkemy.service.generals.GenericService;
import com.joalvarez.challengealkemy.service.interfaces.IGenreService;
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
		return null;
	}

	@Override
	public GenreDTO findById(Long id) {
		return null;
	}

	@Override
	public GenreDTO update(GenreDTO genreDTO, Long id) {
		return null;
	}

	@Override
	public GenreDTO create(GenreDTO genreDTO) {
		return null;
	}
}
