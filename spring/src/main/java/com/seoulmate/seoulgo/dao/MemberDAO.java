package com.seoulmate.seoulgo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class MemberDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;
	
	// 회원탈퇴
	public void deleteSuccess(MemberDTO mdto) {
		session.update("member.deleteSuccess", mdto);
	}

	// 비밀번호 변경 처리
	public void pwUpdate(MemberDTO mdto) {
		System.out.println("MemberDAO.pwUpdate() 진입");
		session.update("member.pwUpdate", mdto);
	}
	
	// 회원정보 조회
	public MemberDTO getMemberInfo(MemberDTO mdto) {
		return session.selectOne("member.getMemberInfo", mdto);
	}

	// 회원정보 수정
	public void memberInfoUpdate(MemberDTO mdto) {
		System.out.println("MemberDAO.memberInfoUpdate() 진입: "+mdto);
		session.update("member.memberInfoUpdate", mdto);
	}

	// 마이페이지
	public MemberDTO findMember(String memberID) {
		MemberDTO mdto = session.selectOne("member.findMember", memberID);
		return mdto;
	}

}