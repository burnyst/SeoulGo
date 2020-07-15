package com.seoulmate.seoulgo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class UserDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 이메일 인증
	public MemberDTO emailAuth(MemberDTO mdto) {
		System.out.println("UserDAO.emailAuth() 진입");
		MemberDTO result = (MemberDTO)session.selectOne("user.emailAuth",mdto);
		System.out.println("UserDAO.emailAuth() result="+result);
		return result;
	}
	
	// 비밀번호 찾기 처리
	public void searchPWProc(MemberDTO mdto) {
		System.out.println("UserDAO.searchPWProc() 진입");
		session.update("user.searchPWProc", mdto);
	}
	
	// 회원가입 처리
	public void registerProc(MemberDTO mdto) {
		session.insert("user.registerProc", mdto);
	}
	
	// 이메일 조회
	public MemberDTO getEmail(MemberDTO mdto) {
		return session.selectOne("user.getEmail", mdto);
	}

	// 닉네임 조회
	public MemberDTO getNickname(MemberDTO mdto) {
		return session.selectOne("user.getNickname", mdto);
	}

	// 아이디 조회
	public MemberDTO getMemberID(MemberDTO mdto) {
		return session.selectOne("user.getMemberID", mdto);
	}
}