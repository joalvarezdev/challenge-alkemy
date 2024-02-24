package com.joalvarez.challengealkemy.data.repository;

import com.joalvarez.challengealkemy.data.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
	Optional<Character> findByNameIgnoreCase(String name);
}
