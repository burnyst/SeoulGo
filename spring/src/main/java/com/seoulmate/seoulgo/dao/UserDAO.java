package com.seoulmate.seoulgo.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class UserDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;
	
	// 로그인 결과
	public HashMap loginResult(HashMap hmap) {
		HashMap result = (HashMap)session.selectOne("user.loginResult", hmap);
		return result;
	}

	// 로그인 처리
	public MemberDTO loginProc(MemberDTO mdto) {
		return session.selectOne("user.loginProc", mdto);
	}

	// 회원가입 처리
	public void registerProc(MemberDTO mdto) {
		session.insert("user.registerProc", mdto);
	}

	// 아이디 조회
	public MemberDTO getMemberID(MemberDTO mdto) {
		return session.selectOne("user.getMemberID", mdto);
	}
}