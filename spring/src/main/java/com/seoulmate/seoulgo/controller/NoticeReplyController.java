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
	
	// 댓글 답글 작성
	@RequestMapping("/reReply/{nNo}/{nrNo}/{nrParent}")
	//@RequestMapping("/reReply")
	public void reReply(NoticeReplyDTO nrdto, HttpServletRequest request, @RequestParam String nrContent2, @PathVariable("nNo") int nNo, @PathVariable("nrNo") int nrNo, @PathVariable("nrParent") int nrParent) {
		System.out.println("request: "+request.getParameter("nrContent2")+"/nNo:"+nNo
		+"/nrNo:"+nrNo+"/nrParent:"+nrParent+
				"/nrdto:"+nrdto);
		
		
		//System.out.println("댓글의 답글 작성 함수 진입: nNo?"+nNo+", nrNo?"+nrNo+", nrParent"+nrParent);

				
		//nrdto.setNrWriter(nrWriter);
		nrdto.setnNo(nNo);
		//nrdto.setNrContent(nrContent2);
		nService.replyProc(nrdto);
				
		RedirectView rv = new RedirectView("/notice/detailView?nNo="+nNo);
		//mv.setView(rv);
		//return mv;
		
		System.out.println("nrdto?"+nrdto);
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