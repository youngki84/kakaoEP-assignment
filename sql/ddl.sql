-- 유저 테이블 생성 
CREATE TABLE `kakao_users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `app_user_id` bigint NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `access_token` varchar(100) DEFAULT NULL,
  `refresh_token` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- 로그 테이블 생성 
CREATE TABLE `kakao_api_logs` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `request_url` varchar(100) DEFAULT NULL,
  `request_method` varchar(100) DEFAULT NULL,
  `request_header` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `request_body` varchar(1000) DEFAULT NULL,
  `response_code` int DEFAULT NULL,
  `response_header` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `response_body` text,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- DDL 이외의 SQL문은 src/main/resources/mapper/kakao_SQL.xml 참조 