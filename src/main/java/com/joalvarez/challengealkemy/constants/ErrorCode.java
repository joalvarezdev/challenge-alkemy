package com.joalvarez.challengealkemy.constants;

public enum ErrorCode implements IResponse {

	USER_UNIDENTIFIED(7000, "The access_token's userId isn't present or is unidentifiable"),
	USER_NOT_AUTHENTICATED(7001, "The user isn't authenticated"),
	USER_BAD_CREDENTIALS(7002, "The user's credentials are invalid"),
	ATTRIBUTE_VALIDATION(8000, "Some provided attribute has a problem"),
	CLIENT_CONNECTION_ERROR(8001, "The integration's client is unreachable or unavailable for the operation"),
	CLIENT_BAD_REQUEST_ERROR(8002, "The integration's client indicated an error while performing this operation"),
	NOT_IMPLEMENTED_ERROR(8003, "The resource you're trying to access or execute isn't implemented yet"),
	USER_OR_EMAIL_ALREADY_EXISTS(8004, "User or email already exists."),

	// * CHARACTERS
	CHARACTER_ALREADY_EXISTS(8005, "The character already exists."),
	CHARACTER_NOT_FOUND(8006, "The character doesn't exist."),
	CHARACTER_NAME_ALREADY_EXISTS(8007, "The character name already exists."),

	// * GENRES
	GENRE_ALREADY_EXISTS(8008, "The genre already exists."),
	GENRE_NOT_FOUND(8009, "The genre doesn't exist."),

	// * MOVIES
	MOVIE_ALREADY_EXISTS(8010, "The movie already exists."),
	MOVIE_NOT_FOUND(8011, "The movie doesn't exist."),
	MOVIE_NOT_FOUND_BY_TITLE(8012, "The movie doesn't exist by title.")
	;

	private final int code;
	private final String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String message() {
		return this.message;
	}
}