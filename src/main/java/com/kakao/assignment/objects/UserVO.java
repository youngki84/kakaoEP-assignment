package com.kakao.assignment.objects;

public class UserVO {
	
	private long app_user_id;
	private String nickname;
	private String profile_image_url;
	private String thumbnail_image_url;
	private String email;
	private String connected_at;
	private String access_token;
	private String refresh_token;
	private String created_at;
	
	public long getAppUserId() {
		return app_user_id;
	}
	public void setAppUserId(long id) {
		this.app_user_id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile_image_url() {
		return profile_image_url;
	}
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	public String getThumbnail_image_url() {
		return thumbnail_image_url;
	}
	public void setThumbnail_image_url(String thumbnail_image_url) {
		this.thumbnail_image_url = thumbnail_image_url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConnected_at() {
		return connected_at;
	}
	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	
}
