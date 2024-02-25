package com.joalvarez.challengealkemy.data.dao;

import com.joalvarez.challengealkemy.data.dao.generals.GenericDAO;
import com.joalvarez.challengealkemy.data.model.Movie;
import com.joalvarez.challengealkemy.data.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class MovieDAO extends GenericDAO<MovieRepository, Movie, Long> {

	public MovieDAO(MovieRepository repository) {
		super(repository);
	}

	public boolean existsByTitle(String title) {
		return this.repository.findByTitle(title).isPresent();
	}

	public Page<Movie> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}
}
