package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;

public class GenreDTO implements BaseDTO {

	private Long genreId;
	private String name;
	private String image;

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
