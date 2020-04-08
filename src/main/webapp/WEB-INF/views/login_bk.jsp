<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="resources/css/main.css" />
	<link rel="stylesheet" href="resources/css/search.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kakao login service</title>
</head>
<body>
    <c:if test="${appUserId ne null}">
        <h1>로그인 성공</h1>
        <div class="user info">
        	<br> **사용자 정보**
        	<br> 
        	<br> App User Id : ${appUserId} 
        	<br> 닉네임 : ${nickName} 
        	<br> 이메일 : ${email} 
        	<br> Access Token : ${accessToken} 
        	<br> refresh Token : ${refreshToken}
        </div>
        <div class="user search">
       		<input type="button" value="현재 로그인 사용자 조회" onclick="location.href='/api/user/${appUserId}'">
        </div>
        <div class="user" >
        	<input type="button" value="로그아웃" onclick="location.href='/logout'">
       		<input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
        </div>
        <form action="/api/users" method="get">
			<div class="user search">
			     <input type="text" id="nickname" name="nickname" placeholder="검색어를 입력해주세요.">
			     <button type="submit">닉네임으로 사용자 조회</button> 
			</div>
		</form>
		<div class="result" >
			${result}
		</div>
    </c:if>
    
</body>
</html>
