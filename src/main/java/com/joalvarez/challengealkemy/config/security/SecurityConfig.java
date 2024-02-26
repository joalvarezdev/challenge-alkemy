package com.joalvarez.challengealkemy.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@Configuration
public class SecurityConfig {

	private final SecurityProperties properties;
	private final AuthenticationConfiguration authenticationConfiguration;
	private final ObjectMapper mapper;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
						  SecurityProperties properties,
						  ObjectMapper mapper) {
		this.authenticationConfiguration = authenticationConfiguration;
		this.properties = properties;
		this.mapper = mapper;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {

				auth.requestMatchers("/auth/**").permitAll();

				this.properties.getEndpoints().forEach(endpoint -> {
					auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new));
				});

				auth.requestMatchers(this.properties.getExcludedPaths()).permitAll()
					.anyRequest().authenticated();
			})
			.addFilter(this.jwtAuthenticationFilter())
			.addFilter(new JwtValidationFilter(this.authenticationManager()))
			.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.formLogin(formLoginConfigurer -> formLoginConfigurer.loginPage("/auth/login"))
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

	/**
	 * Creates and configure a JwtAuthenticationFilter.
	 * @return
	 * @throws Exception
	 */
	private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		var filter = new JwtAuthenticationFilter(this.authenticationManager(), this.mapper);
		filter.setFilterProcessesUrl("/auth/login");
		return filter;
	}
}
