package com.seoulmate.seoulgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {

	//마이페이지 폼 보여주기
	@RequestMapping("/mypageForm")
	public void mypageForm() {
	}
}
