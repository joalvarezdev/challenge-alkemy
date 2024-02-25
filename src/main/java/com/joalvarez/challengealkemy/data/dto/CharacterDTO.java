package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CharacterDTO implements BaseDTO {

	private Long characterId;
	@NotBlank(message = "Name is required")
	private String name;
	private String image;
	@Min(value = 0, message = "Age must be greater than 0")
	private Integer age;
	@Min(value = 0, message = "Weight must be greater than 0")
	private Integer weight;
	@NotBlank(message = "History is required")
	private String history;

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
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
