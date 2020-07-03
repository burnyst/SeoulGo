package com.seoulmate.seoulgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.UserDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;

@Service
public class UserService {

	@Autowired
	UserDAO uDAO;
	
	//회원가입 처리
	public void registerProc(MemberDTO mdto) {
		uDAO.registerProc(mdto);
	}

	//아이디 조회
	public MemberDTO getMemberID(MemberDTO mdto) {
		return uDAO.getMemberID(mdto);
	}
}