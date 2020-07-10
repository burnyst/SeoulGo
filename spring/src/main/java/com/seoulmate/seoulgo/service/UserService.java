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

	// 로그인 처리
	public void loginProc(MemberDTO mdto, HttpSession session, @RequestParam String memberPW) {
		MemberDTO login = uDAO.getPW(mdto);
		
		String memPW = memberPW;
		String encodedPW = pwEncoder.encode(memPW);
		System.out.println("login.getMemberPW(): "+login.getMemberPW());
		System.out.println("로그인창에서 입력한 PW:"+encodedPW);
		
		boolean pwMatch = pwEncoder.matches(memPW, login.getMemberPW());
		System.out.println("uService.loginProc() 비밀번호 일치여부="+pwMatch);
		
		HashMap map = new HashMap();
		map.put("memberID", mdto.getMemberID());
		map.put("memberPW", mdto.getMemberPW());
		
		HashMap result = uDAO.loginProc(map);
		
		if(result==null || result.size()==0) {
			//로그인 실패
			System.out.println("가입된 회원이 아닙니다.");
		}else {
			if(pwMatch==true) {
				//로그인 성공~!~!
				System.out.println("로그인 성공 소리질러~~~~~");
				session.setAttribute("MNICK", result.get("NICKNAME"));
				System.out.println("회원 MNICK="+session.getAttribute("MNICK"));
			}else {
				System.out.println("입력하신 비밀번호는 틀렸습니다.");
			}
		}
	}

	// 회원가입 처리
	public void registerProc(MemberDTO mdto) {
		uDAO.registerProc(mdto);
	}

	// 아이디 조회
	public MemberDTO getMemberID(MemberDTO mdto) {
		return uDAO.getMemberID(mdto);
	}
}