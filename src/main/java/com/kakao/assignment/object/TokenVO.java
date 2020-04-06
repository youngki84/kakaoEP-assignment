package com.kakao.assignment.object;

public class TokenVO {
	
	private String access_token;
	private String refresh_token;
	
	public String getAccessToken() {
		return access_token;
	}
	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
	public String getRefreshToken() {
		return refresh_token;
	}
	public void setRefreshToken(String refreshToken) {
		this.refresh_token = refreshToken;
	}

}
