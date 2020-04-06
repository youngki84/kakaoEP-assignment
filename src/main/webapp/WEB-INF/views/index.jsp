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
    <c:if test="${appUserId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=94347b2d9dcab66353ad9932e87cda1e&redirect_uri=http://localhost:8000/login&response_type=code">
            <img src="/img/kakao_login_btn_large_wide.png">
        </a>
    </c:if>
    <c:if test="${appUserId ne null}">
        <h1>로그인 성공입니다</h1>
        <input type="button" value="사용자 조회" onclick="location.href='/getUserInfo'">
        <input type="button" value="로그아웃" onclick="location.href='/logout'; window.close();">
        <input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
    </c:if>
</body>
</html>
