package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.NoticeDTO;
import com.seoulmate.seoulgo.service.NoticeService;

@RequestMapping("/notice")
@Controller
public class NoticeController {

	@Autowired
	NoticeService nService;
	
	// 댓글 작성
	@RequestMapping("/ReplyProc")
	public void ReplyProc() {
		
	}
	
	// 공지사항 수정 처리
	@RequestMapping("/modifyProc")
	public ModelAndView modifyProc(HttpServletRequest request, ModelAndView mv, @RequestParam String nTitle, @RequestParam String nContent) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		System.out.println("NoticeController.delete() 진입: "+nNo);
		NoticeDTO ndto = nService.findNo(nNo);
		
		ndto.setnTitle(nTitle);
		ndto.setnContent(nContent);
		nService.modifyProc(ndto);
		
		RedirectView rv = new RedirectView("/notice/list");
		mv.setView(rv);
		return mv;
	}
	
	// 공지사항 수정 폼 보여주기
	@RequestMapping("/modify")
	public void modify(Model model, HttpServletRequest request) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeDTO ndto = nService.detailView(nNo);
		model.addAttribute("ndto", ndto);
	}
	
	// 공지사항 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(NoticeDTO ndto, HttpServletRequest request, ModelAndView mv) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		System.out.println("NoticeController.delete() 진입: "+nNo);
		ndto = nService.findNo(nNo);
		ndto.setnExp(false);
		
		nService.delete(ndto);
		RedirectView rv = new RedirectView("/notice/list");
		mv.setView(rv);
		return mv;
	}
	
	// 조회수 증가
	@RequestMapping("/cntUpdate")
	public ModelAndView cntUpdate(HttpServletRequest request, ModelAndView mv) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		nService.cntUpdate(nNo);
		
		mv.addObject("nNo", nNo);
		RedirectView rv = new RedirectView("/notice/detailView");
		mv.setView(rv);
		
		return mv;
	}
	
	// 글 상세보기
	@RequestMapping("/detailView")
	public void detailView(Model model, HttpServletRequest request) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		System.out.println("NoticeController.detailView() nNo="+nNo);
		
		NoticeDTO ndto = nService.detailView(nNo);
		model.addAttribute("nNo", nNo);
		model.addAttribute("ndto", ndto);
	}
	
	// 글목록
	@RequestMapping("/list")
	public void list(NoticeDTO ndto, Model model) {
		ArrayList<NoticeDTO> list = nService.list(ndto);
		model.addAttribute("LIST", list);
	}
	
	// 글쓰기 처리
	@RequestMapping("/writeProc")
	public ModelAndView writeProc(NoticeDTO ndto, ModelAndView mv) {
		System.out.println("NoticeController.writeProc() 진입");
		
		String nTitle = ndto.getnTitle();
		String nContent = ndto.getnContent();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String memberID = principal.toString();
		System.out.println("memberID="+memberID);
		
		ndto.setnWriter(memberID);
		ndto.setnExp(true);
		System.out.println("ndto="+ndto);
		
		nService.writeProc(ndto);
		RedirectView rv = new RedirectView("/notice/list");
		mv.setView(rv);
		return mv;
	}
	
	// 글쓰기 폼 보여주기
	@RequestMapping("/write")
	public void write() {
	}
}