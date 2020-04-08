<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>SNS 테스트</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		<link rel="stylesheet" href="css/search.css" />
		<style>
			h1 {margin-bottom:80px !important}
			.info_wrapper {display:list-item; width:900px; margin-bottom:30px; font-size:20px}
			.info_title { width:160px; float:left; text-align:left;} 
			.info_contents { width:670px; float:right; text-align:left;}
			.actions {margin-top:60px;}
		</style>
		<script>
			function fn_click_button_search(event){
				event.preventDefault();
				fn_tbody_clear();
				if($("#search").val()==""){
					fn_get_all_users();	
				}else{
					$.ajax({
						url: "user/info/user?nickname=" + $("#search").val(),
						dataType : 'json',
						success: function(data){
							var resultData = JSON.parse(data);
								if(resultData.length > 0){
									$.each(resultData, function(index, item){
										var targetTR = $("<tr></tr>");
										targetTR.append("<td>"+item.id+"</td>");
										targetTR.append("<td>"+item.app_user_id+"</td>");
										targetTR.append("<td>"+item.nickname+"</td>");
										targetTR.append("<td>"+item.access_token+"</td>");
										targetTR.append("<td>"+item.refresh_token+"</td>");
										targetTR.append("<td>"+item.created_at+"</td>");
										$("#table_body").append(targetTR);
									});

									$("#personal").show();
									$("#personal_empty").hide();
								}else{
									$("#personal_empty").show();
									$("#personal").hide();
								}
						}
					});
				}
			}

			function fn_tbody_clear(){
				$("#table_body").empty();
			}

			function fn_get_all_users(){
				fn_tbody_clear();

				$.ajax({
					url: "user/info/users",
					dataType : 'json',
					success: function(data){
						var resultData = JSON.parse(data);
						if(resultData.length > 0){
							$.each(resultData, function(index, item){
								var targetTR = $("<tr></tr>");
								targetTR.append("<td>"+item.id+"</td>");
								targetTR.append("<td>"+item.app_user_id+"</td>");
								targetTR.append("<td>"+item.nickname+"</td>");
								targetTR.append("<td>"+item.access_token+"</td>");
								targetTR.append("<td>"+item.refresh_token+"</td>");
								targetTR.append("<td>"+item.created_at+"</td>");
								$("#table_body").append(targetTR);
							});

							$("#personal").show();
							$("#personal_empty").hide();
						}else{
							$("#personal_empty").show();
							$("#personal").hide();
						}
					}
				});
			}
		</script>
	</head>
	<body>
		<div class="page-wrap">

			<!-- Nav -->
				<nav id="nav">
					<ul>
						<li><a data-tippy-content="메인화면" href="main"><span class="icon fa-home"></span></a></li>
						<li><a data-tippy-content="사람검색" href="search_user" class="active"><span class="icon fa-user"></span></a></li>
						<li><a data-tippy-content="로그검색" href="search_log"><span class="icon fa-file-text-o"></span></a></li>
					</ul>
				</nav>

			<!-- Main -->
				<section id="main">
				<div id="header_bar">
					<ul>
						<li><a href="#" data-tippy-content="로그아웃" onClick="fn_click_button_logout()"><span class="icon fa-sign-out"></span></a></li>
						<li><a href="#" data-tippy-content="탈퇴" onClick="fn_click_button_secession()"><span class="icon far fa-trash"></span></a></li>
					</ul>	
				</div>

					<!-- Search Bar -->
					<div class="s01">
						<form>
							<div class="inner-form">
							<div class="input-field first-wrap">
								<input id="search" type="text" placeholder="검색 하고 싶은 닉네임을 적어주세요" />
							</div>
							<div class="input-field third-wrap">
								<button class="btn-search" type="button" onclick="fn_click_button_search(event)">검&nbsp;&nbsp;색</button>
							</div>
							</div>
						</form>
					</div>	

					<!-- Informaion -->
						<section id="personal_empty" style="display:none; background-image: none !important; backgrond-color:#333; margin-top:30px; font-size:20px;">
							<div class="inner">
								<div style="color:white; text-align:center;">현재 정보가 존재하지 않습니다</div> 
							</div>
						</section>

						<section id="personal" style="display:none; background-image: none !important; backgrond-color:#333">
							<div class="inner">
								<table>
									<thead>
											<tr>
												<th>ID</th>
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
				</section>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.poptrox.min.js"></script>
		<script src="js/jquery.scrolly.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/util.js"></script>
		<script src="js/main.js"></script>
		<script src="js/common.js"></script>
		<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.min.js"></script>
		<script src="https://unpkg.com/tippy.js@6/dist/tippy-bundle.umd.js"></script>
		<script>
			$( document ).ready(function() {
				tippy('[data-tippy-content]',{
					theme: 'tomato',
				});
				fn_get_all_users();
			});
		</script>
	</body>
</html>