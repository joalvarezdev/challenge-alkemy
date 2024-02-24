package com.joalvarez.challengealkemy.data.dao;

import com.joalvarez.challengealkemy.data.dao.generals.GenericDAO;
import com.joalvarez.challengealkemy.data.model.Character;
import com.joalvarez.challengealkemy.data.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CharacterDAO extends GenericDAO<CharacterRepository, Character, Long> {

	public CharacterDAO(CharacterRepository repository) {
		super(repository);
	}

	public Page<Character> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public boolean existsByName(String name) {
		return this.repository.findByNameIgnoreCase(name).isPresent();
	}

	public boolean existsById(Long id) {
		return this.repository.existsById(id);
	}

	public Optional<Character> findByName(String name) {
		return this.repository.findByNameIgnoreCase(name);
	}
}
