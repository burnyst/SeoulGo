package com.seoulmate.seoulgo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.page.PlacePage;
import com.seoulmate.seoulgo.page.ReviewPage;
import com.seoulmate.seoulgo.service.MemberService;
import com.seoulmate.seoulgo.service.PlaceService;
import com.seoulmate.seoulgo.service.ReviewService;

@Controller
@RequestMapping("/place")
public class PlaceController {
	private final String VIEW_PATH = "/place";
	@Autowired
	private PlaceService service;
	@Autowired
	private ReviewService rService;
	@Autowired
	private MemberService mService;
	
	@GetMapping("/list")
	public String list(PlacePage page, Model model) {
		model.addAttribute("page", service.list(page));
		return VIEW_PATH+"/list";
	}
	
	@GetMapping("/detail")
	public String detail(int placeNo, Model model, ReviewPage reviewPage) {
		reviewPage.setPlaceNo(placeNo);
		// 로그인 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = principal.toString();
	
		model.addAttribute("item", service.detail(placeNo));
		model.addAttribute("review",rService.getDetailList(reviewPage));
		model.addAttribute("mem", mService.findMember(mem_id));
		model.addAttribute("page", reviewPage);
		return VIEW_PATH+"/detail";
	}
	
	@GetMapping("/insert")
	public String insertForm() {
		return VIEW_PATH+"/insert";
	}
	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<Boolean> insert(PlaceDto dto, MultipartFile[] files, HttpServletRequest req) {
		return new ResponseEntity<>(new Boolean(service.insert(dto, files, req.getSession().getServletContext().getRealPath("/"))), HttpStatus.OK);
	}
	
	@GetMapping("/update")
	public String updateForm(int placeNo, Model model) {
		model.addAttribute("item", service.detail(placeNo));
		return VIEW_PATH+"/update";
	}
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> update(PlaceDto dto, MultipartFile[] files, HttpServletRequest req) {
		return new ResponseEntity<>(new Boolean(service.update(dto, files, req.getSession().getServletContext().getRealPath("/"))), HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<Boolean> delete(int placeNo) {
		return new ResponseEntity<>(new Boolean(service.delete(placeNo)), HttpStatus.OK);
	}
}
