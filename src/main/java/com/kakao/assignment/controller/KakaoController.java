package com.kakao.assignment.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kakao.assignment.dao.KakaoDAO;
import com.kakao.assignment.objects.TokenVO;
import com.kakao.assignment.objects.UserVO;
import com.kakao.assignment.service.KakaoService;

@Controller
public class KakaoController {

	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	
	@Autowired
    private KakaoService kakao;
    
	@Autowired
	private KakaoDAO kakaoDAO;
	
	@Autowired
	SqlSession sqlSession;
	
    @RequestMapping(value="/")
    public String index() {
        
        return "index";
    }
    
    public static TokenVO tokens;
    
    /**
	 * 카카오 유저 로그인 (카카오API 사용) 
	 *
	 * @param code       redirect로 얻어온 code
	 * @param session    세션 정보 
	 */
    @RequestMapping(value="/login")
    public String login(@RequestParam("code") String code, HttpSession session) {
        tokens = kakao.kakaoGetTokens(code);
        System.out.println("controller access_token : " + tokens.getAccessToken());
        
        UserVO userInfo = kakao.kakaoGetUserInfo(tokens);
        
        if(!userInfo.getNickname().isEmpty()) {
        	UserVO user = kakaoDAO.selectKakaoUser(userInfo);
        	if(user == null) {
        		kakaoDAO.insertKakaoUser(userInfo);
        	} else {
        		kakaoDAO.updateKakaoUser(userInfo);
        	}
        	
        }
        
        if (userInfo.getEmail() != null) {
            session.setAttribute("appUserId", userInfo.getAppUserId());
            session.setAttribute("access_Token", tokens.getAccessToken());
        }
        return "index";
    }
    
    /**
	 * 카카오 유저 토큰 refresh (카카오API 사용) 
	 *
	 * @param session    세션 정보 
	 */
    @RequestMapping(value="/refreshlogin", method=RequestMethod.POST)
    public String loginAfterRefresh(HttpSession session) {
    	tokens = kakao.kakaoGetAccessTokenAfterRefresh(tokens);
        System.out.println("controller access_token : " + tokens.getAccessToken());
        
        UserVO userInfo = kakao.kakaoGetUserInfo(tokens);
        System.out.println("login Controller : " + userInfo);
        
//            클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.getEmail() != null) {
            session.setAttribute("appUserId", userInfo.getAppUserId());
            session.setAttribute("access_Token", tokens.getAccessToken());
        }
        return "index";
    }
    
    /**
	 * 카카오 유저 정보 조회 (카카오API 사용) 
	 *
	 * @param session    세션 정보 
	 */
    @RequestMapping(value="/getUserInfoFrKaKao")
    public String getUserInfo(HttpSession session) throws IOException {
        
//        UserVO userInfo = kakao.kakaoGetUserInfo(session.getAttribute("access_Token").toString());
    	if(tokens.getAccessToken().isEmpty()) {
    		System.out.println("Access token is empty");
    		throw new IOException("Access token is empty");
    	}
    	else {
    		UserVO userInfo = kakao.kakaoGetUserInfo(tokens);
    	}
        
        return "index";
    }
    
    /**
	 * 카카오 유저 로그아웃 (카카오API 사용) 
	 *
	 * @param session    세션 정보 
	 */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        kakao.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("appUserId");
        return "index";
    }

    /**
	 * 카카오 유저 회원 탈퇴 (카카오API 사용) 
	 *
	 * @param session    세션 정보 
	 */
    @RequestMapping(value="/unlink")
    public String unlink(HttpSession session) {
        kakao.kakaoUnlink((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("appUserId");
        return "index";
    }
    
    /**
	 * DB에 저장된 전체 회원 정보를 검색하는 API 
	 * ex) http://localhost:8000/api/users?nickname=뱅기 
	 * method=RequestMethod.GET
	 * 
	 * @param AppUserId    사용자ID 
	 * @throws Throwable throws
	 * 
	 */
    @RequestMapping(value="/api/users", method=RequestMethod.GET)
    public @ResponseBody String getUsersInfo(@RequestParam(value="nickname", defaultValue="") String nickname) throws IOException {
        
    	UserVO userInfo = new UserVO();
    	userInfo.setNickname(nickname);
        
    	List<UserVO> users = kakaoDAO.selectKakaoUsers(userInfo);
    	
    	for(UserVO user : users) {
    		System.out.println(user.getNickname());
    	}
    	
    	
    	Gson gson = new Gson();
    	
    	String result = gson.toJson(users);
    	
        return result;
    }
    
    /**
	 * DB에 저장된 개인 회원 정보를 검색하는 API 
	 * ex) http://localhost:8000/api/user/1324302081
	 * method=RequestMethod.GET
	 * 
	 * @param AppUserId    사용자ID 
	 * @throws Throwable throws
	 * 
	 */
    @RequestMapping(value="/api/user/{appUserId}", method=RequestMethod.GET)
    public @ResponseBody String getUserInfo(@PathVariable("appUserId") long appUserId) throws IOException {
        
    	UserVO userInfo = new UserVO();
    	userInfo.setAppUserId(appUserId);
        
    	UserVO user = kakaoDAO.selectKakaoUser(userInfo);
    	
    	Gson gson = new Gson();
    	
    	String result = gson.toJson(user);
    	
        return result;
    }
    
    /**
	 * DB에 저장된 개인 회원의 닉네임 정보를 수정하는 API 
	 * ex) http://localhost:8000/api/user/1324302081?nickname=뱅기
	 * method=RequestMethod.PUT
	 * 
	 * @param AppUserId    사용자ID 
	 * @param nickname     수정할 닉네임 정보 
	 * @throws Throwable throws
	 * 
	 */
    @RequestMapping(value="/api/user/{appUserId}", method=RequestMethod.PUT)
    public @ResponseBody String updateUserInfo(@PathVariable("appUserId") long appUserId, @RequestParam(value="nickname") String nickname) throws IOException {
        
    	UserVO userInfo = new UserVO();
    	userInfo.setAppUserId(appUserId);
    	userInfo.setNickname(nickname);
        
    	UserVO user = kakaoDAO.selectKakaoUser(userInfo);
    	if(user == null) {
    		throw new IOException("User is empty.");
    	} else {
    		kakaoDAO.updateKakaoUser(userInfo);
    	}
    	
        return "Update Success.";
    }
    
    /**
	 * DB에 저장된 개인 회원의 정보를 삭제하는 API 
	 * ex) http://localhost:8000/api/user/1324302081
	 * RequestMethod.DELETE
	 * 
	 * @param AppUserId    사용자ID 
	 * @throws Throwable throws
	 * 
	 */
    @RequestMapping(value="/api/user/{appUserId}", method=RequestMethod.DELETE)
    public @ResponseBody String deleteUserInfo(@PathVariable("appUserId") long appUserId) throws IOException {
        
    	UserVO userInfo = new UserVO();
    	userInfo.setAppUserId(appUserId);
        
    	UserVO user = kakaoDAO.selectKakaoUser(userInfo);
    	if(user == null) {
    		throw new IOException("User is empty.");
    	} else {
    		kakaoDAO.deleteKakaoUser(userInfo);
    	}
    	
        return "Delete Success.";
    }
	
}
