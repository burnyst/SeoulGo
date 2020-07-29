package com.seoulmate.seoulgo.controller;

import java.util.List;

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
import com.seoulmate.seoulgo.dto.NoticeReplyDTO;
import com.seoulmate.seoulgo.page.NoticePage;
import com.seoulmate.seoulgo.service.NoticeService;

@RequestMapping("/notice")
@Controller
public class NoticeController {

	@Autowired
	NoticeService nService;
	
	// 댓글 작성
	@RequestMapping("/commentProc")
	public ModelAndView commentProc(NoticeReplyDTO nrdto, HttpServletRequest request, ModelAndView mv, @RequestParam String nrContent) {
		// 작성 댓글의 해당 공지사항 정보 가져오기
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		
		// 댓글 작성자(= 현재 로그인한 회원) 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nrWriter = principal.toString();
		
		nrdto.setNrWriter(nrWriter);
		nrdto.setnNo(nNo);
		nrdto.setNrContent(nrContent);
		nService.commentProc(nrdto);
		
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/detailView?nNo="+nNo);
		mv.setView(rv);
		return mv;
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
		
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/list");
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
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/list");
		mv.setView(rv);
		return mv;
	}
	
	// 조회수 증가
	@RequestMapping("/cntUpdate")
	public ModelAndView cntUpdate(HttpServletRequest request, ModelAndView mv) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		nService.cntUpdate(nNo);
		
		mv.addObject("nNo", nNo);
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/detailView");
		mv.setView(rv);
		
		return mv;
	}
	
	// 공지사항 상세보기
	@RequestMapping("/detailView")
	public void detailView(Model model, HttpServletRequest request) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		System.out.println("NoticeController.detailView() nNo="+nNo);
		
		NoticeDTO ndto = nService.detailView(nNo);
		
		// 관리자 권한 가져오기
		String mLevel = nService.findMlevel(ndto);
		
		model.addAttribute("mLevel", mLevel);
		model.addAttribute("nNo", nNo);
		model.addAttribute("ndto", ndto);
	}
	
	// 공지사항 목록 페이징
	@RequestMapping("/list")
	public void list(NoticeDTO ndto, Model model, NoticePage notice) {
		// 공지사항 결과수
		int cnt = nService.getCnt(notice);
		notice.setTotalRow(cnt);
		System.out.println("NoticeController.list().cnt: "+cnt);
		
		List<NoticeDTO> list = nService.list(notice);
		System.out.println("NoticeController.list(): "+list);
		model.addAttribute("LIST", list);
		model.addAttribute("notice", notice);
	}
	
	// 공지사항 작성 처리
	@RequestMapping("/writeProc")
	public ModelAndView writeProc(NoticeDTO ndto, ModelAndView mv, HttpServletRequest request) {
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
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/list");
		mv.setView(rv);
		return mv;
	}
	
	// 공지사항 작성 폼 보여주기
	@RequestMapping("/write")
	public void write() {
	}
}