package com.seoulmate.seoulgo.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	MemberService mService;

	// 회원탈퇴
	@RequestMapping("/deleteSuccess")
	public void deleteSuccess(MemberDTO mdto, @RequestParam String pw) {
	}

	// 회원탈퇴 폼 보여주기
	@RequestMapping("/deleteAccount")
	public void deleteAccount() {
	}

	// 비밀번호 변경 폼 보여주기
	@RequestMapping("/changePWForm")
	public void changePWForm() {
	}

	// 회원정보 수정 폼 보여주기
	@RequestMapping("/memberInfo")
	public void memberInfo() {
	}

	// 마이페이지 폼 보여주기
	@RequestMapping("/mypageForm")
	public void mypageForm() {
	}
}
