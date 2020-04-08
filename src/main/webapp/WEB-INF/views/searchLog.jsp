<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<title>Kakao log searching</title>
  </head>
  <body >
  		<form action="/api/log" method="get">
			<div class="search">
			     <input type="text" id="searchString" name="searchString" placeholder="검색어를 입력해주세요.">
			     <button type="submit">로그 검색</button> 
			</div>
		</form>
  </body>
</html>