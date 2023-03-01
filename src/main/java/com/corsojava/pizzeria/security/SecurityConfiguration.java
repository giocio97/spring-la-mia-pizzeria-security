package com.corsojava.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()

				// AUTH PER CREARE E MODIFICARE PIZZE: ADMIN
				.requestMatchers("/pizze/create", "/pizze/edit/**").hasAuthority("ADMIN")

				// POST SU PIZZE PER ELIMINARE UNA PIZZA: ADMIN
				.requestMatchers(HttpMethod.POST, "/pizze/**").hasAuthority("ADMIN")

				// CONTROLLO SULLE OFFERTE: ADMIN

				.requestMatchers("/offerte", "/offerte/**").hasAuthority("ADMIN")

				// CONTROLLO SUGLI INGREDIENTI: ADMIN
				.requestMatchers("/ingredienti", "/ingredienti/**").hasAuthority("ADMIN")

				// ELENCO E DETTAGLIO PIZZE: ADMIN E USER
				.requestMatchers("/pizze", "/pizze/**").hasAnyAuthority("USER", "ADMIN")

				// ACCESSO ALLA HOME: USER ED ADMIN
				.requestMatchers("/**").permitAll().and().formLogin().and().logout().and().exceptionHandling();

		return http.build();

	}

	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		System.out.println(passwordEncoder().encode("user"));

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

}
