<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<link rel="stylesheet" href="resources/css/main.css" />
	<link rel="stylesheet" href="resources/css/search.css" />
 	 <script
		  src="https://code.jquery.com/jquery-3.4.1.js"
		  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		  crossorigin="anonymous">
 	 </script>
  	<script>
		function fn_click_button_search(event){
			event.preventDefault();
			fn_tbody_clear();
			$("#table").hide();
			$("#table_empty").hide();
			
			$.ajax({
				url: "/api/log?searchString=" + $("#search").val(),
				dataType : 'json',
				success: function(resultData){
					if(resultData.length > 0){
						$.each(resultData, function(index, item){
							var targetTR = $("<tr></tr>");
							targetTR.append("<td width='100px'>"+item.request_url+"</td>");
							targetTR.append("<td width='100px'>"+item.request_method+"</td>");
							targetTR.append("<td width='100px'>"+item.request_header+"</td>");
							targetTR.append("<td width='100px'>"+item.request_body+"</td>");
							targetTR.append("<td width='100px'>"+item.response_code+"</td>");
							targetTR.append("<td width='100px'>"+item.response_header+"</td>");
							targetTR.append("<td width='100px'>"+item.response_body+"</td>");
							targetTR.append("<td width='100px's>"+item.created_at+"</td>");	
							$("#table_body").append(targetTR);
						});
						console.log("데이타 있음");
						$("#table").show();
						$("#table_empty").hide();
					}else{
						console.log("데이타 없음");
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
	<title>Kakao log searching</title>
  </head>
  <body>
  		<h1>로그 검색</h1>
  		<div class="s01">
			<form>
				<div class="inner-form">
				<div class="input-field first-wrap">
					<input id="search" type="text" placeholder="검색 로그 문구 입력" />
				</div>
				<div class="input-field third-wrap">
					<button class="btn-search" type="button" onclick="fn_click_button_search(event)">검&nbsp;&nbsp;색</button>
				</div>
				</div>
			</form>
		</div>	

		<!-- Informaion -->
			<section id="table_empty" style="display:none; background-image: none !important; backgrond-color:#333; margin-top:30px; font-size:20px;">
				<div class="inner">
					<div style="color:white; text-align:center;">검색 결과가 없습니다.</div> 
				</div>
			</section>

			<section id="table" style="display:none; background-image: none !important; backgrond-color:#333">
				<div>
					<table class="zui-table">
						<thead>
								<tr>
									<th width="100px">Request_url</th>
									<th width="100px">Request_method</th>
									<th width="100px">Request_header</th>
									<th width="100px">Request_body</th>
									<th width="100px">Response_code</th>
									<th width="100px">Response_header</th>
									<th width="100px">Response_body</th>
									<th width="100px">Created_at</th>
								</tr>
							</thead>
						<tbody id="table_body">
						</tbody>
					</table>
				</div>
			</section>
  </body>
</html>