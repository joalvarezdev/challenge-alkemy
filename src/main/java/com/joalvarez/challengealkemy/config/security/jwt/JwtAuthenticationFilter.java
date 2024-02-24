package com.joalvarez.challengealkemy.config.security.jwt;

import static com.joalvarez.challengealkemy.config.security.jwt.JwtConstants.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.challengealkemy.data.model.User;
import com.joalvarez.challengealkemy.shared.HasLogger;
import com.joalvarez.challengealkemy.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements HasLogger {

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		User user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

		return this.authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
		throws IOException, ServletException {

		User user = (User) authResult.getPrincipal();
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

		Claims claims = Jwts.claims()
			.add("id_user", user.getId())
			.add("username", user.getUsername())
			.add("authorities", roles)
			.add("tenant", user.getUserId())
			.build();

		long currentMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentMillis);

		String token = Jwts.builder()
			.subject(user.getUsername())
			.claims(claims)
			.issuedAt(currentDate)
			.expiration(new Date(currentDate.getTime() + EXPIRATION_TIME))
			.signWith(SECRET_KEY)
			.compact();

		response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN.concat(token));

		Map<String, Object> body = new HashMap<>();
		body.put("token", token);
		body.put("expires_in", EXPIRATION_TIME);
		body.put("token_type", PREFIX_TOKEN);

		this.setReponse(response, body, HttpStatus.OK, MediaType.APPLICATION_JSON);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
		throws IOException, ServletException {

		Map<String, String> body = new HashMap<>();
		body.put("message", "Error en la autenticacion username o password incorrectos!");
		body.put("error", failed.getMessage());

		this.setReponse(response, body, HttpStatus.UNAUTHORIZED, MediaType.APPLICATION_JSON);
	}

	private void setReponse(HttpServletResponse response, Object body, HttpStatus status, MediaType contentType) throws IOException {
		response.getWriter().write(Utils.objectToJson(body));
		response.setStatus(status.value());
		response.setContentType(contentType.toString());
	}
}