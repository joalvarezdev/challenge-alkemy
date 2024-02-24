package com.joalvarez.challengealkemy.config.security;

import com.joalvarez.challengealkemy.config.security.constants.SecurityProperties;
import com.joalvarez.challengealkemy.config.security.jwt.JwtAuthenticationFilter;
import com.joalvarez.challengealkemy.config.security.jwt.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig {

	private final SecurityProperties properties;
	private final AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
						  SecurityProperties properties) {
		this.authenticationConfiguration = authenticationConfiguration;
		this.properties = properties;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {

//				auth.requestMatchers(HttpMethod.GET, "/characters").hasRole("ADMIN");
				auth.requestMatchers("/**").permitAll();
/*
				auth.requestMatchers("/users/register").hasRole("ADMIN");

				this.properties.getEndpoints().forEach(endpoint -> {
					auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new));
				});

				auth.requestMatchers(this.properties.getExcludedPaths()).permitAll()
					.anyRequest().authenticated();
*/
			})
			.addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
			.addFilter(new JwtValidationFilter(this.authenticationManager()))
			.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}

	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.HEAD.name()
					)
					.allowedHeaders("*");
			}
		};
	}
}
