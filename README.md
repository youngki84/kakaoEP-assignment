#Kakao Enterprise Assignment

## 구축 환경 
* Server Framework : Spring boot - 2.2.6.RELEASE
* Database Framework : MyBatis - 2.1.2
* Database : MySQL - 8.0.19
* UnitTest : JUnit4 - 4.13

## 구축 전략 
* 일반적인 MVC 모델 채택
* Web : JSP로 구성  
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

## 테스트 전략 
* junit 으로 DB에 관련된 기능 수행 점검 
* POSTMAM 프로그램을 이용하여 서버로 Rest call 실행

## 실행 방법 (로컬실행 기준) 
1. Project Download : https://github.com/youngki84/kakaoEP-assignment
2. 이클립스(또는 다른 IDE)에서 Project import 후 spring boot 내장 톰캣 서버 start
3. 카카오 로그인 관련 서비스는 http://localhost:8000 으로 접속  
4. 로그 검색 관련 서비스는 http://localhost:8000/log 으로 접속 
	