package com.joalvarez.challengealkemy.data.dto;

import com.joalvarez.challengealkemy.data.dto.generals.BaseDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequestDTO(
	Long id,
	@NotBlank
	String title,
	String image,
	LocalDate creationDate,
	@Min(value = 0)
	@Max(value = 5)
	Integer rating,
	@NotEmpty
	List<Long> genres
) implements BaseDTO {
}
