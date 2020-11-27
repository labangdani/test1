package com.example.test1.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long idU;
	private String username;
	private String mailU;
	private List<String> roles;

	public JwtResponse(String accessToken, Long idU, String username, String mailU, List<String> roles) {
		this.token = accessToken;
		this.idU = idU;
		this.username = username;
		this.mailU = mailU;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getIdU() {
		return idU;
	}

	public void setIdU(Long idU) {
		this.idU = idU;
	}

	public String getMailU() {
		return mailU;
	}

	public void setMailU(String mailU) {
		this.mailU = mailU;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}