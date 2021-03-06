package com.kakao.assignment.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kakao.assignment.dao.KakaoDAO;
import com.kakao.assignment.object.LogVO;
import com.kakao.assignment.object.TokenVO;
import com.kakao.assignment.object.UserVO;



@Service
public class KakaoService {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoService.class);
	
	@Autowired
	private KakaoDAO kakaoDAO;
	
	public TokenVO kakaoGetTokens (String authorize_code) {
		
        String reqURL = "https://kauth.kakao.com/oauth/token";
        TokenVO tokens = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=94347b2d9dcab66353ad9932e87cda1e");
            sb.append("&redirect_uri=http://localhost:8000/login");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            System.out.println("reqest data: " + reqURL + sb.toString());
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response data : " + result);
            
            String headerData = "";
	        for(String header : conn.getHeaderFields().keySet()) {
	        	if(header != null) {
	        		for(String value: conn.getHeaderFields().get(header)) {
	        			headerData = headerData + header + ":" + value + " ";
//	        			System.out.println(header + ":" + value);
	        		}
	        	}
	        }
            
            //log insert
            insertLog(conn.getURL().toString(), conn.getRequestMethod(), headerData, sb.toString(), conn.getResponseCode(), "", result);
            
            Gson gson = new Gson();
    		tokens = gson.fromJson(result, TokenVO.class);
            
            System.out.println("access_token (login) : " + tokens.getAccessToken());
            System.out.println("refresh_token (login) : " + tokens.getRefreshToken());
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return tokens;
    }
	
	public TokenVO kakaoGetAccessTokenAfterRefresh(TokenVO tokens) {

        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
        	if(tokens.getRefreshToken().isEmpty())
    			throw new IOException("refresh token is empty");
        	
        	System.out.println("refresh_token (refreshlogin) : " + tokens.getRefreshToken());
        	
        	URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=refresh_token");
            sb.append("&client_id=94347b2d9dcab66353ad9932e87cda1e");
            sb.append("&refresh_token=" + tokens.getRefreshToken());
            bw.write(sb.toString());
            bw.flush();
            
            System.out.println("reqest : " + sb);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            Gson gson = new Gson();
            
    		tokens = gson.fromJson(result, TokenVO.class);
            
            System.out.println("access_token : " + tokens.getAccessToken());
            System.out.println("refresh_token : " + tokens.getRefreshToken());
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return tokens;
	}
	
	public UserVO kakaoGetUserInfo (TokenVO tokens) {
	    
	    UserVO userInfo = new UserVO();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        conn.setRequestProperty("Authorization", "Bearer " + tokens.getAccessToken());
	        int responseCode = conn.getResponseCode();
	        
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        String headerData = "";
	        for(String header : conn.getHeaderFields().keySet()) {
	        	if(header != null) {
	        		for(String value: conn.getHeaderFields().get(header)) {
	        			headerData = headerData + header + ":" + value + " ";
//	        			System.out.println(header + ":" + value);
	        		}
	        	}
	        }
	        
	      //log insert
            insertLog(conn.getURL().toString(), conn.getRequestMethod(), headerData, "", conn.getResponseCode(), "", result);

            String email = "";
            
	        JsonElement element = JsonParser.parseString(result);
	        
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	        
	        long id = element.getAsJsonObject().get("id").getAsLong();
	        String connected_at = element.getAsJsonObject().get("connected_at").getAsString();
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        
	        Set<String> keyset = kakao_account.getAsJsonObject().keySet();
	        boolean email_yes = false;
	        for(String s : keyset) {
	        	if(s.equals("email")) {
	        		email_yes = true;
	        	}
	        }
	        if(email_yes) {
	        	email = kakao_account.getAsJsonObject().get("email").getAsString();
	        } else {
	        	email = "동의하지 않은 항목";
	        }
	        
	        String profile_image_url = properties.getAsJsonObject().get("profile_image").getAsString();
	        String thumbnail_image_url = properties.getAsJsonObject().get("thumbnail_image").getAsString();
	        
	        userInfo.setAppUserId(id);
	        userInfo.setConnected_at(connected_at);
	        userInfo.setNickname(nickname);
	        userInfo.setEmail(email);
	        userInfo.setProfile_image_url(profile_image_url);
	        userInfo.setThumbnail_image_url(thumbnail_image_url);
	        userInfo.setAccess_token(tokens.getAccessToken());
	        userInfo.setRefresh_token(tokens.getRefreshToken());
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	        
	        String headerData = "";
	        for(String header : conn.getHeaderFields().keySet()) {
	        	if(header != null) {
	        		for(String value: conn.getHeaderFields().get(header)) {
	        			headerData = headerData + header + ":" + value + " ";
//	        			System.out.println(header + ":" + value);
	        		}
	        	}
	        }
	        
	      //log insert
            insertLog(conn.getURL().toString(), conn.getRequestMethod(), headerData, "", conn.getResponseCode(), "", result);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public int kakaoUnlink(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/unlink";
	    int responseCode = 0;
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	        
	        String headerData = "";
	        for(String header : conn.getHeaderFields().keySet()) {
	        	if(header != null) {
	        		for(String value: conn.getHeaderFields().get(header)) {
	        			headerData = headerData + header + ":" + value + " ";
//	        			System.out.println(header + ":" + value);
	        		}
	        	}
	        }
	        
	      //log insert
            insertLog(conn.getURL().toString(), conn.getRequestMethod(), headerData, "", conn.getResponseCode(), "", result);
            
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return responseCode;
	}
	
	public void insertLog(String request_url, String request_method, String request_header, String request_body, int response_code, String response_header, String response_body) {
		LogVO log = new LogVO();
		
		log.setRequest_url(request_url);
		log.setRequest_method(request_method);
		log.setRequest_header(request_header);
		log.setRequest_body(request_body);
		log.setResponse_code(response_code);
		log.setResponse_header(response_header);
		log.setResponse_body(response_body);
		
		kakaoDAO.insertKakaoApiLog(log);
		
	}

}
