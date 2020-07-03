package com.seoulmate.seoulgo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class UserDAO extends SqlSessionDaoSupport {
	
	@Autowired
	SqlSessionTemplate session;
	
	//회원가입 처리
	public void registerProc(MemberDTO mdto) {
		session.insert("user.registerProc", mdto);
	}

	public MemberDTO getMemberID(MemberDTO mdto) {
		return session.selectOne("user.getMemberID", mdto);
	}
}