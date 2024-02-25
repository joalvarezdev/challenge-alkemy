package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class MovieDTO implements BaseDTO {

	private Long id;
	@NotBlank
	private String title;
	private String image;
	private LocalDate creationDate;
	@Min(value = 0)
	@Max(value = 5)
	private Integer rating;
	private List<GenreDTO> genres;
	private List<CharacterDTO> characters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

	public List<CharacterDTO> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterDTO> characters) {
		this.characters = characters;
	}
}
