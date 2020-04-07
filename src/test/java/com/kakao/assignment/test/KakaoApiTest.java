package com.kakao.assignment.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.google.gson.Gson;
import com.kakao.assignment.object.LogVO;
import com.kakao.assignment.object.UserVO;


public class KakaoApiTest {

	@Test
	public void testGetUsersInfo() {
		
		String reqURL = "http://localhost:8000/api/users";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        int responseCode = conn.getResponseCode();
	        assertEquals(responseCode, 200);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("junit test result : " + result);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testGetUserInfo() {
		
		String appUserId = "1324302081";
		String reqURL = "http://localhost:8000/api/user/" + appUserId;
	    
		try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        int responseCode = conn.getResponseCode();
	        assertEquals(responseCode, 200);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("junit test result : " + result);
	        
	        Gson gson = new Gson();
	        
	        UserVO user = gson.fromJson(result, UserVO.class);
	        
	        if(user != null) {
	        	System.out.println("user.getAppUserId() : " + user.getAppUserId());
	        	assertEquals(user.getAppUserId(), Integer.parseInt(appUserId));
	        }
	        
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testUpdateUserInfo() {
		
		String appUserId = "1324302081";
		String update_field = "nickname";
		String update_date = "뱅기"; 
		String reqURL = "http://localhost:8000/api/user/" + appUserId + "?" + update_field + "=" + update_date;
	    System.out.println("reqURL : " + reqURL);
		try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("PUT");
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("junit test result : " + result);
	        
	        Gson gson = new Gson();
	        
	        UserVO user = gson.fromJson(result, UserVO.class);
	        
	        if(user != null) {
	        	System.out.println("user.getNickname() : " + user.getNickname());
	        	assertEquals(user.getNickname(), update_date);
	        }
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testGetApiLog() {
		
		String search_string = "POST";
		String reqURL = "http://localhost:8000/api/log/" + search_string;
	    
		try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("junit test result : " + result);
	        
	        Gson gson = new Gson();
	        
	        LogVO[] logs = gson.fromJson(result, LogVO[].class);
	        
	        if(logs != null) {
	        	System.out.println("log.getRequest_method() : " + logs[0].getRequest_method());
	        	assertEquals(logs[0].getRequest_method(), search_string);
	        }
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
