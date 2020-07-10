package com.seoulmate.seoulgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	//회원 관리 폼 보여주기
	@RequestMapping("/memberManagement")
	public void memberManagement() {
	}
}
