package com.kakao.assignment.object;

public class LogVO {
	
	private String request_url;
	private String request_method;
	private String request_header;
	private String request_body;
	private int response_code;
	private String response_header;
	private String response_body;
	private String created_at;
	
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public String getRequest_method() {
		return request_method;
	}
	public void setRequest_method(String request_method) {
		this.request_method = request_method;
	}
	public String getRequest_header() {
		return request_header;
	}
	public void setRequest_header(String request_header) {
		this.request_header = request_header;
	}
	public String getRequest_body() {
		return request_body;
	}
	public void setRequest_body(String request_body) {
		this.request_body = request_body;
	}
	public String getResponse_header() {
		return response_header;
	}
	public void setResponse_header(String response_header) {
		this.response_header = response_header;
	}
	public String getResponse_body() {
		return response_body;
	}
	public void setResponse_body(String response_body) {
		this.response_body = response_body;
	}
	public int getResponse_code() {
		return response_code;
	}
	public void setResponse_code(int response_code) {
		this.response_code = response_code;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
