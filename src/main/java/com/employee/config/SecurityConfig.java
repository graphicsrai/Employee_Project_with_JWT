package com.employee.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.security.JwtAuthEntryPoint;
import com.employee.security.JwtAuthFilter;

import jakarta.servlet.Filter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 @Autowired
	    private JwtAuthEntryPoint jwtAuthEntryPoint;

	    // Create JwtAuthFilter bean
	    @Bean
	    public JwtAuthFilter jwtAuthFilter(){
	        return new JwtAuthFilter();
	    }

	    // Create PasswordEncoder bean
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return NoOpPasswordEncoder.getInstance();
	    }

	    // Create SecurityFilterChain bean
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        // Disable CORS
	        http.cors().disable();

	        // Disable CSRF
//	        http.csrf().disable();

	        // Change session management to STATELESS
	        http.sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        // Add exception handler
	        http.exceptionHandling()
	                .authenticationEntryPoint(jwtAuthEntryPoint);

	        // Authorize http requests
	        http.authorizeHttpRequests()
	                .requestMatchers("/").permitAll()
	                .requestMatchers("/app-auth/token/**").permitAll()
	                .anyRequest().authenticated();

	        // Add JWT authentication filter

	        http.addFilterBefore((Filter) jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
