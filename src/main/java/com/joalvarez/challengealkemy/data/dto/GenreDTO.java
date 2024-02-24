package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;

public class GenreDTO implements BaseDTO {

	private Long id;
	private String name;
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
