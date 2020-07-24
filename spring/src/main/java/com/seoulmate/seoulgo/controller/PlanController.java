package com.seoulmate.seoulgo.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PlanPage;
import com.seoulmate.seoulgo.service.PlanService;


@Controller
public class PlanController {
@Autowired
public PlanService planservice;
 
	//http://127.0.0.1:9000/plan/plan	?nowPage=1
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(
			ModelAndView mv, PlanDTO plan  ,Model model,PlanPage page2,HttpSession session)throws Exception{
		System.out.println("planpage진입요청함수");
		//페이징관련 준비(페이지 숫자)
//		2-2) 해당 페이지에 출력할 목록조회
//		String memberid=null;
//		memberid=session.getId();
//		System.out.println(memberid);
//		memberid=planservice.getmemberid(memberid);
//		if(memberid==null) {
//			mv.setViewName("place/list");
//			return mv;
//		}
		
		int TotalRow = planservice.getTotalRow(page2);
		page2.setTotalRow(TotalRow);
		
		ArrayList<PlanDTO> pdto = (ArrayList) planservice.getplanboard(page2);
		System.out.println(pdto);
		System.out.println("page2의 초기값 조회합니다."+page2);
		System.out.println("pdto의 최종값을 조회합니다."+pdto);
		
		mv.addObject("plist",pdto);
		mv.addObject("page",page2);
		mv.setViewName("plan/plan");
		return mv;
	}
	//http://127.0.0.1:9000/plan/planmodi
	@RequestMapping("/plan/planmodi")
	public ModelAndView planredirect(ModelAndView mv,HttpServletRequest req) {
		int pno  =  Integer.parseInt(req.getParameter("pno"));//글번호
		System.out.println("planmodi도착, pno의 숫자: "+pno);
		
		ArrayList<PlanDTO> pdto = (ArrayList)planservice.detailView(pno);//DTO에 내용 넣기.
		for (int i=0;i<pdto.size(); i++) {
			int j = pdto.get(i).getPlanNo();
			System.out.println(j+"j의값");
			ArrayList<PlanDTO> view2 = planservice.addrservice(j);
			System.out.println(view2);
			//view2.get(0).getAdd1();
			pdto.get(i).setAddr1(view2.get(0).getAddr1());
			//view2.get(0).getAdd2();
			pdto.get(i).setAddr2(view2.get(0).getAddr2());
		}
		mv.setViewName("plan/planmodi");
		mv.addObject("Pdto",pdto);
		return mv;
	}
	
	@PostMapping("/plan/planmodifin")
	public ModelAndView planmodifin(ModelAndView mv,HttpServletRequest req, PlanDTO plan) {
		planservice.planmodifinservice(plan, req);
		
		RedirectView rv = new RedirectView("/plan/plan");
		mv.setView(rv);
		return mv;
	}

	@RequestMapping("/plan/planSboard")
	public ModelAndView planShare( PlanDTO plan,ModelAndView mv,PlanPage page) {
		//페이징 시작
		//startRow 계산
		//int listCnt = aService.searchList(search);
		System.out.println("planSboard진입");
		int TotalRow = planservice.getTotalRow(page);
		page.setTotalRow(TotalRow);
		System.out.println("planobject planpage()"+page);
		
		ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.planview(page);
		System.out.println("plandto를 받은 변수 view의 값: "+view);
		mv.addObject("pageview", view);
		mv.addObject("page",page);
		mv.setViewName("plan/planSboard");
		return mv;
	}
//일정을 작성할때 사용할 페이지 폼
	@RequestMapping("/plan/planwrite")
	public ModelAndView planwrite(ModelAndView mv) {
		System.out.println("planwrite 호출완료");
		
		
		mv.setViewName("plan/planwrite");
		return mv;
	}
	@RequestMapping("/plan/planwritenter")
	public ModelAndView planwritenter(ModelAndView mv,HttpServletRequest req) {
		System.out.println("planwritenter 호출완료");
		
		int placeNo = Integer.parseInt(req.getParameter("placeNo"));
		
		ArrayList choiceplace = (ArrayList)planservice.choiceplace(placeNo);
		System.out.println("choiceplace 변수의 내용 : 컨트롤러 "+choiceplace);
		mv.addObject("choice", choiceplace);
		mv.setViewName("plan/planwritenter");
		return mv;
	}
	//1.작성된 정보를  2.db로 날리는 작업 
	@PostMapping("/plan/planwrited")
	public ModelAndView planwriteed(ModelAndView mv,HttpServletRequest req,PlanDTO plan,PlanPage page) {
		
		planservice.planwritedservice(plan,req);
		
		
		int TotalRow = planservice.getTotalRow(page);
		page.setTotalRow(TotalRow);
		
		ArrayList<PlanDTO> pdto = (ArrayList) planservice.getplanboard(page);
		System.out.println(pdto);
		System.out.println("page2의 초기값 조회합니다."+page);
		System.out.println("pdto의 최종값을 조회합니다."+pdto);
		
		RedirectView rv = new RedirectView("/plan/plan");
		mv.addObject("plist",pdto);
		mv.addObject("page",page);
		mv.setView(rv);
		return mv;
	}
	
	
	//일정 상세보기 페이지<비회원, 회원 공용 (?)
	@RequestMapping("/plan/planview")
		public ModelAndView planviews(ModelAndView mv,HttpServletRequest req,PlanDTO plan,int planNo) {
		System.out.println("planview 호출완료");
		System.out.println("지금 받은 planno의 값 : "+planNo);
		//detailview에서 어레이 리스트로  해당 planno에 들어있는 게시글을 받았다.
		ArrayList<PlanDTO> detailview = planservice.detailView(planNo);
		for (int i=0;i<detailview.size(); i++) {
			int j = detailview.get(i).getPlanNo();
			System.out.println(j+"j의값");
			ArrayList<PlanDTO> view2 = planservice.addrservice(j);
			System.out.println(view2);
			//view2.get(0).getAdd1();
			detailview.get(i).setAddr1(view2.get(0).getAddr1());
			//view2.get(0).getAdd2();
			detailview.get(i).setAddr2(view2.get(0).getAddr2());
		}
		System.out.println("dtd를 어레이 리스트로-planview : "+detailview);
		
		mv.addObject("Pdto", detailview);
		return mv;
	}
	
	//장소검색 폼
	@RequestMapping("plan/placesarch")
	public ModelAndView placeSarch(ModelAndView mv,PlanPage page){
		//place정보를 다 긁어와야 한다.
		
		//페이징 시작
		int TotalRow = planservice.sarchplaceint(page);
		page.setTotalRow(TotalRow);
		ArrayList<PlanDTO> view = (ArrayList<PlanDTO>) planservice.placeSarch(page);
		
		System.out.println("totalrow의 값: "+TotalRow);
		System.out.println("view 변수의 값 : "+view);
		mv.addObject("placeview", view);
		mv.addObject("page",page);
		mv.setViewName("plan/placesarch");
		return mv;
	}
	@RequestMapping("/plan/plandelete")
	public String plandelete() {
		System.out.println("plandelete 페이지 호출함수★★★");
		return "plan/plandelete";
	}
	
	@RequestMapping("plan/viewplan")
	public String planview() {
		System.out.println("viewplan페이지 호출함수.,");
		return "plan/viewplan";
	}

	
}