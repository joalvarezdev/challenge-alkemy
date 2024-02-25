package com.joalvarez.challengealkemy.exception.generals;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {

	public AuthException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AuthException(String message) {
		super(message);
	}

}
