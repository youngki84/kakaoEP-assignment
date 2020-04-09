<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />
<!--         <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css" /> -->
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
        </script>
        <script>
            function fn_click_button_search(event) {
                event.preventDefault();
                fn_tbody_clear();

                $.ajax({
                    url: "/api/users?nickname=" + $("#nickname").val(),
                    dataType: 'json',
                    success: function(resultData) {
                        console.log(resultData);
                        if (resultData.length > 0) {
                            $.each(resultData, function(index, item) {
                                var targetTR = $("<tr></tr>");
                                targetTR.append("<td>" + item.app_user_id + "</td>");
                                targetTR.append("<td>" + item.nickname + "</td>");
                                targetTR.append("<td>" + item.access_token + "</td>");
                                targetTR.append("<td>" + item.refresh_token + "</td>");
                                targetTR.append("<td>" + item.created_at + "</td>");
                                $("#table_body").append(targetTR);
                            });

                            $("#table").show();
                            $("#table_empty").hide();
                        } else {
                            $("#table_empty").show();
                            $("#table").hide();
                        }
                    }
                });

            }

            function fn_tbody_clear() {
                $("#table_body").empty();
            }
            
            
        </script>

        <title>Kakao login service</title>
    </head>

    <body>
        <h1 style="margin-left: 100px;">로그인 되었습니다.</h1>
        <div class="user info" style="border: ridge;text-align: center;-webkit-text-stroke: medium;">
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
        <div class="s01">
			<div>
				<input type="button" value="로그아웃" onclick="location.href='/logout'"> 
			</div>
			<div style="margin: 10px;"> </div>
			<div>
            	<input type="button" value="탈퇴하기" onclick="location.href='/unlink'">
            </div>
        </div>
        <div class="s01">
			<div>
				<input type="button" value="로그 검색" onclick="location.href='/log'"> 
			</div>
        </div>
        <h2 style="margin-left: 100px;"> 유저 검색 </h2>
        <div class="s01">
            <form name="search_form">
                <div class="inner-form">
                    <div class="input-field first-wrap">
                        <input id="nickname" type="text" onkeypress="return event.keyCode != 13;" placeholder="검색할 닉네임을 입력" />
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