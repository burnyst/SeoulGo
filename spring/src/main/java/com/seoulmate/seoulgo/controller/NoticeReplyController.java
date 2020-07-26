package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.NoticeReplyDTO;
import com.seoulmate.seoulgo.service.NoticeService;

@RestController
@RequestMapping("/reply")
public class NoticeReplyController {

	@Autowired
	NoticeService nService;
	
	// 대댓글(댓글의 답글) 작성
	@RequestMapping("/reReply/{nNo}/{nrNo}/{nrParent}")
	public ModelAndView reReply(NoticeReplyDTO nrdto, ModelAndView mv, @RequestParam String nrContent2, @PathVariable("nNo") int nNo, @PathVariable("nrNo") int nrNo, @PathVariable("nrParent") int nrParent) {
		System.out.println("RequestParam으로 넘어온 nrContent2: "+nrContent2+"/nNo: "+nNo+"/nrNo: "+nrNo+"/nrParent: "+nrParent+"/nrdto: "+nrdto);
		
		// 댓글 정보 가져오기
		NoticeReplyDTO replyInfo = nService.getReplyInfo(nrNo);
		System.out.println("replyInfo: "+replyInfo);
		
		// 작성자(= 현재 로그인한 회원) 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nrWriter = principal.toString();
		nrdto.setNrWriter(nrWriter);
		nrdto.setnNo(nNo);
		nrdto.setNrContent(nrContent2);
		nrdto.setNrParent(nrNo);
		nrdto.setNrOrder(replyInfo.getNrOrder()+1);
				
		System.out.println("nService로 넘어갈 nrdto: "+nrdto);
		nService.reReply(nrdto);
		
		RedirectView rv = new RedirectView("/notice/detailView?nNo="+nNo);
		mv.setView(rv);
		return mv;
	}
	
	// 댓글 삭제
	@RequestMapping("/rplDelete/{nNo}/{nrNo}")
	public ModelAndView rplDelete(ModelAndView mv, @PathVariable("nNo") int nNo, @PathVariable("nrNo") int nrNo, HttpServletRequest request) {
		System.out.println("NoticeReplyController.delete() 진입"+nNo);
		nService.rplDelete(nrNo);
		
		RedirectView rv = new RedirectView("/notice/detailView?nNo="+nNo);
		mv.setView(rv);
		return mv;
	}
	
	// 댓글 리스트
	@RequestMapping("/{nNo}")
	public ArrayList<NoticeReplyDTO> replyList(NoticeReplyDTO nrdto, @PathVariable("nNo") int nNo) {
		ArrayList<NoticeReplyDTO> list = nService.replyList(nNo);
		
		System.out.println("list="+list);
		return list;
	}
}