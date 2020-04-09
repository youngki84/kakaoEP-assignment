# Kakao Enterprise Assignment

작성자 : 배영기 

## 구축 환경 
* Language : JAVA 1.8
* Web Library : JQuery - 3.4.1
* Application Framework : Spring boot - 2.2.6.RELEASE
* Server : Apache Tomcat - 9.0.33 (Spring boot 내장) 
* Database Framework : MyBatis - 2.1.2
* Database : MySQL - 8.0.19
* UnitTest : JUnit4 - 4.13
* Json Libray : Gson - 2.8.6


## 구축 전략 
* 디자인 패턴 : 
	- Model-View-Controller 모델 채택
* 패키지 구조 :
	(Java)
	- com.kakao.assignment
	- com.kakao.assignment.controller
	- com.kakao.assignment.service
	- com.kakao.assignment.dao
	- com.kakao.assignment.object
	- com.kakao.assignment.util
	(SQL)
	- resources/mapper
	(Web)
	- WEB-INF/views
* Sequence Flow :
	- Web browser > KakaoController > KakaoService > KakaoDAO > kakao_SQL
* Web : 
	- JQeury를 이용한 JSP 파일 
* KakaoController : 
	- RestAPI call을 받아 KakaoService를 호출.
	- RestAPI Request/Response에 대한 log 값 기록 요청 (KakaoDAO 호출)
	- DB에서 데이터 조회 후 리턴 (KaKaoDAO 호출)
* KakaoService :
	- Kakao oauth API 호출 담당 
	- Kakao API Request/Response에 대한 log 값 기록 요청 (KakaoDAO 호출)
* KakaoDAO :
	- Database에 접근하여 CRUD를 실행 
* VO 객체 :
	- token,user,log에 대한 객체정보
	- Database에 전달되는 파라미티 및 결과를 리턴받는 객체
* Util 객체 :
	- 모든 객체에서 사용하는 util 관련 static 함수 모음
* kakao_SQL.xml :
	- DAO에 의해서 실행되는 Database SQL문  
* 로그 기록 :
	- Rest API 호출이 일어날 때마다 RDB에 Request/Response 데이터 Insert 
	
## 작동 방식 
* 로그인 : 
	- 첫 화면에서 로그인 버튼 클릭하여 카카오 로그인 진행 
	- 카카오 oauth 서버에서 받은 code로 사용자 토큰을 받아옴 
	- 로그인 성공 시 DB에 사용자 정보 Insert 및 로그인 화면 진입 
* 로그아웃 : 
	- 로그인 화면에서 로그아웃 시 카카오 logout api 호출 후 브라우져 창 blank (브라우저 강제 종료는 현 크롬에서 명령어가 안먹힘) 
* 탈퇴하기 :
	- 로그인 화면에서 탈퇴하기 시 카카오 unlink api 호출 후 DB에서 User Delete 하고 첫 화면으로 진입
* 유저 검색 : 
	- 로그인 화면에서 검색할 닉네임 입력하고 검색 버튼 클릭
	- DB에서 해당 유저 정보 가져옴 
* 로그 검색 : 
	- 로그 검색 화면으로 진입 
	- 검색할 문구 입력하면 해당 문구를 가진 모든 컬럼을 가져옴   

## 테스트 전략 
* junit으로 Rest Call에 대한 결과값 확인  
* POSTMAM 프로그램을 이용하여 서버로 Rest call 실행 후 결과값 확인 

## 실행 방법 (로컬실행 기준) 
1. Project Download : https://github.com/youngki84/kakaoEP-assignment
2. 서버구동 : Eclipse에서 Project import 후 'project 우클릭 > Run As > Spring Boot App'
3. 카카오 로그인 관련 : http://localhost:8000 으로 접속  
4. 로그 검색 : http://localhost:8000/log 으로 접속 또는 http://localhost:8000 접속/로그인 후 '로그검색' 클릭 
	