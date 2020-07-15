package com.seoulmate.seoulgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoulmate.seoulgo.service.AdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	AdminService aService;

	// 회원 리스트에서 회원 강퇴 처리하기
	
	
	// 회원 리스트 폼 보여주기
	@RequestMapping("/memberManagement")
	public void memberManagement(Model model) {
		model.addAttribute("memberList", aService.getMemberList());
	}
}
