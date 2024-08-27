package com.pa.dto;

public class JwtAuthRespone {
	private String accessToken;
	private String tokenType = "Bearer";
	private String role;
	
	public JwtAuthRespone(String accessToken, String tokenType, String role) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.role = role;
	}
	public JwtAuthRespone() {
		super();
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Jwt [accessToken=" + accessToken + ", tokenType=" + tokenType + ", role=" + role + "]";
	}
	
}
