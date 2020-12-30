package com.auth.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable()
		.authorizeRequests()
		.antMatchers("/token").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
	@Bean
	public AuthenticationManager authManagerBean()throws Exception {
		return super.authenticationManagerBean();
	}
	
}
