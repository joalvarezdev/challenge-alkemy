package com.joalvarez.challengealkemy.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "characters")
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String image;
	private Integer age;
	private Integer weight;
	private String history;


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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
}
