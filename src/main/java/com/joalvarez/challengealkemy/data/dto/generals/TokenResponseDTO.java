package com.joalvarez.challengealkemy.data.dto.generals;

public record TokenResponseDTO(String token, Long expiresIn, String type) {
}
