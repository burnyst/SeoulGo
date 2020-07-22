package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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