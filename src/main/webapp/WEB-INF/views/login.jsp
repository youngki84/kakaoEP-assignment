<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="resources/css/main.css" />
	<link rel="stylesheet" href="resources/css/search.css" />
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/> 
    <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
    <script>
	    function fn_click_button_search(event){
			event.preventDefault();
			fn_tbody_clear();
			
			$.ajax({
				url: "/api/users?nickname=" + $("#nickname").val(),
				dataType : 'json',
				success: function(resultData){
					console.log(resultData);
					if(resultData.length > 0){
						$.each(resultData, function(index, item){
							var targetTR = $("<tr></tr>");
							targetTR.append("<td>"+item.app_user_id+"</td>");
							targetTR.append("<td>"+item.nickname+"</td>");
							targetTR.append("<td>"+item.access_token+"</td>");
							targetTR.append("<td>"+item.refresh_token+"</td>");
							targetTR.append("<td>"+item.created_at+"</td>");
							$("#table_body").append(targetTR);
						});

						$("#table").show();
						$("#table_empty").hide();
					}else{
						$("#table_empty").show();
						$("#table").hide();
					}
				}
			});
			
		}
	    function fn_tbody_clear(){
			$("#table_body").empty();
		}
    </script>
   
    <title>Kakao login service</title>
</head>
<body>
        <h1>로그인 성공</h1>
        
        <div class="user info" style="/* align-content: center; */border: ridge;">
        	<br> **사용자 정보**
        	<br> 
        	<br> App User Id : ${appUserId} 
        	<br> 닉네임 : ${nickName} 
        	<br> 이메일 : ${email}
        	<br> Access Token : ${accessToken}  
        	<br> refresh Token : ${refreshToken}
        </div>
        
      
<!--         <div class="s01"> -->
<!--        		<input type="button" value="현재 로그인 사용자 조회" onclick="location.href='/api/user/1324302081'"> -->
<!--         </div> -->
        <div class="s01" >
        	<input type="button" value="로그아웃" onclick="location.href='/logout'">
        </div>
        <div class="s01">
       		<input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
        </div>
        <div class="s01">
	        <form>
				<div class="inner-form">
					<div class="input-field first-wrap">
						<input id="nickname" type="text" placeholder="검색할 닉네임을 입력" />
					</div>
					<div class="input-field third-wrap">
						<button class="btn-search" type="button" onclick="fn_click_button_search(event)">검&nbsp;&nbsp;색</button>
					</div>
				</div>
			</form>
		</div>
		<section id="table_empty" style="display:none; background-image: none !important; backgrond-color:#333; margin-top:30px; font-size:20px;">
			<div class="inner">
				<div style="/* color:white; */text-align:center;">검색 결과가 없습니다.</div>
			</div>
		</section>
		<section id="table" style="display:none; background-image: none !important; backgrond-color:#333">
			<div class="inner">
				<table class="zui-table">
					<thead>
							<tr>
								<th>APP_USER_ID</th>
								<th>NICKNAME</th>
								<th>ACCESS_TOKEN</th>
								<th>REFRESH_TOKEN</th>
								<th>CREATED_AT</th>
							</tr>
						</thead>
					<tbody id="table_body">
					</tbody>
				</table>
			</div>
		</section>
</body>
</html>
