package com.joalvarez.challengealkemy.data.dto.generals;

import com.joalvarez.challengealkemy.constants.IResponse;

import java.util.List;

public record ResponseDTO (int code, String message, List<String> details) implements BaseDTO {}