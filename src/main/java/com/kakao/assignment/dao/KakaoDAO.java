package com.kakao.assignment.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kakao.assignment.objects.UserVO;


@Component
public class KakaoDAO {

	private static final String Namespace="com.kakao.assignment";
	private static final Logger logger = LoggerFactory.getLogger(KakaoDAO.class);
	
	@Autowired
	SqlSession sqlSession;

	public List<UserVO> selectKakaoUsers(UserVO userInfo) {
		logger.info("selectKakaoUsers" );
		System.out.println("selectKakaoUsers");
		return sqlSession.selectList(Namespace + ".selectKakaoUsers", userInfo);
	}
	
	public UserVO selectKakaoUser(UserVO userInfo) {
		logger.info("selectKakaoUser" );
		System.out.println("selectKakaoUser");
		return sqlSession.selectOne(Namespace + ".selectKakaoUser", userInfo);
	}

	public int insertKakaoUser(UserVO userInfo) {
		logger.info("insertKakaoUser" );
		System.out.println("insertKakaoUser");
		return sqlSession.insert(Namespace + ".insertKakaoUser", userInfo);
	}

	public int updateKakaoUser(UserVO userInfo) {
		logger.info("updateKakaoUser" );
		System.out.println("updateKakaoUser");
		return sqlSession.update(Namespace + ".updateKakaoUser", userInfo);
	}
	
	public int deleteKakaoUser(UserVO userInfo) {
		logger.info("deleteKakaoUser" );
		System.out.println("deleteKakaoUser");
		return sqlSession.delete(Namespace + ".deleteKakaoUser", userInfo);
	}
	
//	public List<NoticeResultVO> selectNoticeAll(NoticeVO noticevo) {
//		logger.info("selectNoticeAll" );
//
//		return sqlSession.selectList(Namespace + ".selectNoticeAll",noticevo);
//	}

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
