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
        <input type="button" value="사용자 조회" onclick="location.href='/api/user/${appUserId}'">
        <input type="button" value="로그아웃" onclick="location.href='/logout'">
        <input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
    </c:if>
    
</body>
</html>
