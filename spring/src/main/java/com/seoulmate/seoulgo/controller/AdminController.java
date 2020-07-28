package com.seoulmate.seoulgo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.page.MemberSearchPage;
import com.seoulmate.seoulgo.service.AdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	AdminService aService;

	// 회원 리스트 검색
	@RequestMapping("/search")
	public String search(Model model, MemberSearchPage search, @RequestParam String searchType, @RequestParam String keyword) {
		System.out.println("searchType: "+searchType+"/keyword: "+keyword);
		
		// 해당 검색 결과수 
		int listCnt = aService.searchList(search);
		System.out.println("검색 결과수는? "+listCnt);
		search.setTotalRow(listCnt);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		System.out.println("aService.getMemberList(search):"+aService.getMemberList(search));
		
		model.addAttribute("memberList", aService.getMemberList(search));
		model.addAttribute("search", search);
		
		return "/admin/memberManagement";
	}
	
	// 회원 리스트에서 회원 탈퇴 시키기
	@ResponseBody
	@RequestMapping("/deleteAccount")
	public Map<String, Object> deleteAccount(MemberDTO mdto) {
		System.out.println("탈퇴 진행할 회원 아이디는? "+mdto.getMemberID());
		mdto.setEnabled(false);
		aService.deleteAccount(mdto);
		
		MemberDTO result = aService.findMember(mdto);
		Map<String, Object> msg = new HashMap<String, Object>();
		System.out.println("회원 탈퇴 결과 "+result);
		
		if(result.isEnabled() == false) {
			msg.put("msg", "success");
		}else {
			msg.put("msg", "fail");
		}
		return msg;
	}
	
	// 회원 리스트 폼 보여주기
	@RequestMapping("/memberManagement")
	public void memberManagement(Model model, MemberSearchPage search) {
		// 해당 검색 결과수 
		int listCnt = aService.searchList(search);
		System.out.println("검색 총 결과수는? "+listCnt);
		search.setTotalRow(listCnt);
		System.out.println("aService.getMemberList(search):"+aService.getMemberList(search));
		model.addAttribute("memberList", aService.getMemberList(search));
		model.addAttribute("search", search);
	}
}