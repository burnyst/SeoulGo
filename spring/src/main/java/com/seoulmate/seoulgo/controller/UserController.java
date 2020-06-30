package com.seoulmate.seoulgo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	UserService uService;
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	//회원가입시 아이디 중복 확인 요청 함수
	@ResponseBody
	@PostMapping("/idCheck")
	public Map<String, Object> idCheck(MemberDTO mdto) {
		System.out.println("idCheck 실행");
		
		Map<String, Object> checkResult = new HashMap<String, Object>();
		
		if(uService.getMemberID(mdto) != null) {
			checkResult.put("idCheck", "fail");
		}else {
			checkResult.put("idCheck", "success");
		}
		
		return checkResult;
	}
	
	//아이디 조회
	public ModelAndView getMemberID(MemberDTO mdto, ModelAndView mv) {
		mv.addObject("member", uService.getMemberID(mdto));
		mv.setViewName("#");
		
		return mv;
	}
	
	//회원가입 처리 요청 함수
	@RequestMapping("/registerProc")
	public ModelAndView registerProc(MemberDTO mdto, ModelAndView mv, @RequestParam String phone, @RequestParam String memberPW) {
		System.out.println("registerProc 함수 진입");
		String phone1 = phone.substring(0, 3);
		String phone2;
		String phone3;
		
		if(phone.length() == 10) {
			phone2 = phone.substring(3, 6);
			phone3 = phone.substring(6);
		}else {
			phone2 = phone.substring(3, 7);
			phone3 = phone.substring(7);
		}
		String encodePw = pwEncoder.encode(memberPW);
		mdto.setMemberPW(encodePw);
		
		mdto.setPhone1(phone1);
		mdto.setPhone2(phone2);
		mdto.setPhone3(phone3);
		mdto.setmLevel("ROLE_MEMBER");
		
		uService.registerProc(mdto);
		
		RedirectView rv = new RedirectView("../user/loginForm");
		mv.setView(rv);
		
		return mv;
	}
	
	//회원가입 폼 보여주기
	@RequestMapping("/registerForm")
	public void registerForm() {
	}
	
	//로그인 폼 보여주기
	@RequestMapping("/loginForm")
	public void loginForm() {
	}
}
