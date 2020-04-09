package com.kakao.assignment.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kakao.assignment.object.LogVO;
import com.kakao.assignment.object.UserVO;


@Component
public class KakaoDAO {

	private static final String Namespace="com.kakao.assignment";
	private static final Logger logger = LoggerFactory.getLogger(KakaoDAO.class);
	
	@Autowired
	SqlSession sqlSession;

	public List<UserVO> selectKakaoUsers(UserVO userInfo) {
		logger.info("selectKakaoUsers" );
		return sqlSession.selectList(Namespace + ".selectKakaoUsers", userInfo);
	}
	
	public UserVO selectKakaoUser(UserVO userInfo) {
		logger.info("selectKakaoUser" );
		return sqlSession.selectOne(Namespace + ".selectKakaoUser", userInfo);
	}

	public int insertKakaoUser(UserVO userInfo) {
		logger.info("insertKakaoUser" );
		return sqlSession.insert(Namespace + ".insertKakaoUser", userInfo);
	}

	public int updateKakaoUser(UserVO userInfo) {
		logger.info("updateKakaoUser" );
		return sqlSession.update(Namespace + ".updateKakaoUser", userInfo);
	}
	
	public int deleteKakaoUser(long appUserId) {
		logger.info("deleteKakaoUser" );
		return sqlSession.delete(Namespace + ".deleteKakaoUser", appUserId);
	}
	
	public List<LogVO> seleteKakaoApiLogs(String search_string) {
		logger.info("seleteKakaoApiLogs" );
		if(search_string.length() > 0) {
			search_string = "%" + search_string + "%";
		}
		return sqlSession.selectList(Namespace + ".selectKakaoApiLogs", search_string);
	}
	
	public int insertKakaoApiLog(LogVO log) {
		logger.info("insertKakaoApiLog" );
		return sqlSession.insert(Namespace + ".insertKakaoApiLog", log);
	}
	
	/**
	 * @return the sqlSession
	 */
	public SqlSession getSqlSession() {
		return sqlSession;
	}


	/**
	 * @param sqlSession the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
