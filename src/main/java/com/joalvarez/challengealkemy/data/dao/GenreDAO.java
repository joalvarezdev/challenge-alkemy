package com.joalvarez.challengealkemy.data.dao;

import com.joalvarez.challengealkemy.data.dao.generals.GenericDAO;
import com.joalvarez.challengealkemy.data.model.Genre;
import com.joalvarez.challengealkemy.data.repository.GenreRepository;
import org.springframework.stereotype.Component;

@Component
public class GenreDAO extends GenericDAO<GenreRepository, Genre, Long> {

	public GenreDAO(GenreRepository repository) {
		super(repository);
	}
}
