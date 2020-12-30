package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.helper.JwtUtil;
import com.auth.pojo.LoginRequest;
import com.auth.pojo.TokenResponse;
import com.auth.service.CustomUserDetailsService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateLoginToken(@RequestBody LoginRequest loginreq) {
		 System.out.println(loginreq);
		
		 try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(loginreq.getUsername(), loginreq.getPassword()));
		} catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Bad Credentials", HttpStatus.UNAUTHORIZED);
		}
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginreq.getUsername());
		
		String token  = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT: " + token);
		
		
		
		return ResponseEntity.ok(new TokenResponse(token));
	}

}
