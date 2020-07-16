package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService mService;
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	// 로그아웃
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	// 회원탈퇴
	@RequestMapping("/deleteSuccess")
	public void deleteSuccess(MemberDTO mdto, HttpSession session, @RequestParam String pw) {
		// mService.deleteSuccess(mdto);
		MemberDTO pwCheck = mService.getMemberInfo(mdto);
		boolean result = pwEncoder.matches(pw, pwCheck.getMemberPW());
		System.out.println("result=" + result);
	}

	// 회원탈퇴 폼 보여주기
	@RequestMapping("/deleteAccount")
	public void deleteAccount() {
	}

	// 비밀번호 변경 폼 보여주기
	@RequestMapping("/changePW")
	public void changePWForm() {
	}

	// 회원정보 수정
	@RequestMapping("/memberInfoUpdate")
	public void memberInfoUpdate(MemberDTO mdto, HttpSession session) {
		mService.memberInfoUpdate(mdto);
	}

	// 회원정보 수정 폼 보여주기
	@RequestMapping("/memberInfo")
	public void memberInfo() {
	}

	// 마이페이지 폼 보여주기
	@RequestMapping("/mypage")
	public void mypageForm(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String memberID = ((UserDetails) principal).getUsername();
			System.out.println("if.username=" + memberID);
			MemberDTO mem = mService.findMember(memberID);
			session.setAttribute("mem", mem);
		} else {
			String memberID = principal.toString();
			System.out.println("else.username=" + memberID);
			MemberDTO mem = mService.findMember(memberID);
			session.setAttribute("mem", mem);
		}
	}
}