<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kakao login service</title>
</head>
<body>
    <c:if test="${appUserId ne null}">
        <h1>로그인 성공</h1>
        <div class="user info">
        	<br> !사용자 정보!
        	<br> 
        	<br> App User Id : ${appUserId} 
        	<br> 닉네임 : ${nickName} 
        	<br> 이메일 : ${email} 
        	<br> Access Token : ${accessToken} 
        	<br> refresh Token : ${refreshToken}
        	<br> 
        </div>
        <div class="user search">
       		<input type="button" value="사용자 조회" onclick="location.href='/api/user/${appUserId}'">
       		<br> 
        </div>
        <div class="user" >
        	<input type="button" value="로그아웃" onclick="location.href='/logout'">
       		<input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
       		<br> 
        </div>

        <div class="search">
		     <input type="text" value="${searchString}" placeholder="검색어를 입력해주세요.">
		     <input type="button" value="로그 검색" onclick="location.href='/api/log/${searchString}'">
		</div>
    </c:if>
    
</body>
</html>
