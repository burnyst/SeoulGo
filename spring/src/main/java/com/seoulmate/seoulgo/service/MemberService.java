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
}
