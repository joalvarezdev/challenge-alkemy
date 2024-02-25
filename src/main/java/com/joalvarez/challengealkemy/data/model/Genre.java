package com.joalvarez.challengealkemy.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;
	private String name;
	private String image;

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long id) {
		this.genreId = id;
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
