package com.seoulmate.seoulgo.service;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.seoulmate.seoulgo.dao.UserDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;

@Service
public class UserService {

	@Autowired
	UserDAO uDAO;

	@Inject
	BCryptPasswordEncoder pwEncoder;

	// 이메일 인증
	public MemberDTO emailAuth(MemberDTO mdto) {
		System.out.println("UserService.emailAuth() 진입");
		MemberDTO result = uDAO.emailAuth(mdto);
		return result;
	}

	// 비밀번호 찾기 처리
	public void searchPWProc(MemberDTO mdto) {
		System.out.println("UserService.searchPWProc() 진입");
		uDAO.searchPWProc(mdto);
	}

	// 회원가입 처리
	public void registerProc(MemberDTO mdto) {
		uDAO.registerProc(mdto);
	}

	// 이메일 조회
	public MemberDTO getEmail(MemberDTO mdto) {
		return uDAO.getEmail(mdto);
	}

	// 닉네임 조회
	public MemberDTO getNickname(MemberDTO mdto) {
		return uDAO.getNickname(mdto);
	}

	// 아이디 조회
	public MemberDTO getMemberID(MemberDTO mdto) {
		return uDAO.getMemberID(mdto);
	}
}