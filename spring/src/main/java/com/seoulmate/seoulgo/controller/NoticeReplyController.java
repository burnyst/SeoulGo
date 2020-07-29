package com.seoulmate.seoulgo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.dto.NoticeReplyDTO;
import com.seoulmate.seoulgo.service.MemberService;
import com.seoulmate.seoulgo.service.NoticeService;

@RestController
@RequestMapping("/comment")
public class NoticeReplyController {

	@Autowired
	NoticeService nService;
	
	@Autowired
	MemberService mService;
	
	// 댓글 정보 가져오기
	@RequestMapping("/getInfo")
	public Map<String, Object> getInfo(NoticeReplyDTO nrdto, @RequestParam int nrNo, @RequestParam String nrWriter) {
		nrdto.setNrNo(nrNo);
		nrdto.setNrWriter(nrWriter);
		
		System.out.println("NoticeReplyContrllor.getInfo() nrdto="+nrdto);
		List<MemberDTO> list = nService.getMemberInfo(nrdto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("msg", "success");
		
		return map;
	}
	
	// 댓글, 댓글의 답글 작성 가능 여부 확인
	@RequestMapping("/infoCheck")
	public Map<String, String> infoCheck(NoticeReplyDTO nrdto, @RequestParam int nrNo) {
		System.out.println("NoticeReply.infoCheck() 들어오니?");
		System.out.println("nrdto="+nrdto);
		
		// 댓글 정보 가져오기
		NoticeReplyDTO replyInfo = nService.getReplyInfo(nrNo);
		
		// 현재 로그인한 회원
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String memberID = principal.toString();
		
		// 현재 로그인한 회원 정보 가져오기
		MemberDTO mdto = mService.findMember(memberID);
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 댓글 작성자와 로그인한 회원의 정보가 같거나 현재 로그인한 회원의 권한이 ROLE_ADMIN인 경우
		if(replyInfo.getNrWriter().equals(memberID) || mdto.getmLevel().equals("ROLE_ADMIN")) {
			map.put("msg", "success");
		}else {
			map.put("msg", "fail");
		}
		return map;
	}
	
	// 대댓글(댓글의 답글) 작성
	@RequestMapping("/reply/{nNo}/{nrNo}/{nrParent}")
	public ModelAndView reply(NoticeReplyDTO nrdto, ModelAndView mv, HttpServletRequest request, @RequestParam String nrContent2, @PathVariable("nNo") int nNo, @PathVariable("nrNo") int nrNo, @PathVariable("nrParent") int nrParent) {
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
		nService.reply(nrdto);
		
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/detailView?nNo="+nNo);
		mv.setView(rv);
		return mv;
	}
	
	// 댓글 삭제
	@RequestMapping("/delete/{nNo}/{nrNo}")
	public ModelAndView rplDelete(ModelAndView mv, HttpServletRequest request, @PathVariable("nNo") int nNo, @PathVariable("nrNo") int nrNo) {
		System.out.println("NoticeReplyController.delete() 진입"+nNo);
		nService.delete(nrNo);
		
		RedirectView rv = new RedirectView(request.getContextPath()+"/notice/detailView?nNo="+nNo);
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