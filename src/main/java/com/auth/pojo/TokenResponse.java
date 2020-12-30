package com.auth.pojo;

public class TokenResponse {

	
	private String token;
	
	public TokenResponse() {
		// TODO Auto-generated constructor stub
	}
	

	public TokenResponse(String token) {
		super();
		this.token = token;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
