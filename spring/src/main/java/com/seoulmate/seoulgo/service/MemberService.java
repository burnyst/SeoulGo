package com.seoulmate.seoulgo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dao.MemberDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;

public class MemberService {

	@Autowired
	MemberDAO mDAO;

	// 회원탈퇴
	public MemberDTO deleteSuccess(MemberDTO mdto) {
		return mdto;
	}

	// 비밀번호 변경 처리
	public void pwUpdate(MemberDTO mdto) {
		System.out.println("MemberService.pwUpdate() 진입");
		mDAO.pwUpdate(mdto);
	}
	
	// 회원정보 조회
	public MemberDTO getMemberInfo(MemberDTO mdto) {
		return mDAO.getMemberInfo(mdto);
	}

	// 회원정보 수정
	public void memberInfoUpdate(MemberDTO mdto) {
		mDAO.memberInfoUpdate(mdto);
	}

	// 마이페이지
	public MemberDTO findMember(String memberID) {
		return mDAO.findMember(memberID);
	}
}