package com.seoulmate.seoulgo.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PageObject;
import com.seoulmate.seoulgo.service.PlanService;


@Controller
public class PlanController {
@Autowired
public PlanService planservice;
 
	//http://127.0.0.1:9000/plan/plan	?nowPage=1
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(
			ModelAndView mv, PlanDTO plan , 
			@RequestParam(value="nowPage",
	 					required=false,
		               defaultValue="1")int nowPage,HttpServletRequest req)throws Exception{
		System.out.println("planpage진입요청함수");
		
		//페이징관련 준비(페이지 숫자)
		PageObject pInfo = planservice.getPageInfo(nowPage);
//		2-2) 해당 페이지에 출력할 목록조회
		ArrayList<PlanDTO> list = (ArrayList) planservice.paging(plan);
		//페이지에 들어갈 정보 입력
		ArrayList<PlanDTO> view = (ArrayList)planservice.planview(plan);
		for (int i=0;i<view.size(); i++) {
			int j = view.get(i).getPlanNo();
			System.out.println(j+"j의값");
			ArrayList<PlanDTO> view2 = planservice.addrservice(j);
			System.out.println(view2);
			//view2.get(0).getAdd1();
			view.get(i).setAddr1(view2.get(0).getAddr1());
			//view2.get(0).getAdd2();
			view.get(i).setAddr2(view2.get(0).getAddr2());
		}
		
		System.out.println("view 병합의 값:"+view);
		mv.addObject("PINFO",pInfo);
		mv.addObject("DTOlist",list);
		mv.addObject("Viewlist",view);
		mv.setViewName("plan/plan");
		return mv;
	}
	//http://127.0.0.1:9000/plan/planmodi
	@RequestMapping("/plan/planmodi")
	public ModelAndView planredirect(ModelAndView mv,HttpServletRequest req) {
		int pno  =  Integer.parseInt(req.getParameter("pno"));//글번호
		System.out.println("planmodi도착, pno의 숫자: "+pno);
		ArrayList<PlanDTO> pdto = (ArrayList)planservice.dtoview(pno);//DTO에 내용 넣기.
		
		
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
		//1.작성된 정보를 db에 업데이트 작업을 한다.
		
		planservice.planmodifinservice(plan, req);
		
		//db에서 정보를 가져와 출력
		
		mv.setViewName("plan/plan");
		return mv;
	}
	
	//1.작성된 정보를  2.db로 날리는 작업 
	@PostMapping("/plan/planwrited")
	public ModelAndView planwriteed(ModelAndView mv,HttpServletRequest req,PlanDTO plan) {
		planservice.planwritedservice(plan,req);
		//3.그 정보를 갱신해서 db에서 가져와 출력
		ArrayList<PlanDTO> list = (ArrayList) planservice.planview(plan);
		mv.addObject("DTOlist",list);
		System.out.println(list);
		mv.setViewName("plan/plan");
		return mv;
	}
	@RequestMapping("/plan/planSboard")
	public String planShare(PlanDTO plan) {
		System.out.println("planShare 페이지 호출함수");
		planservice.planSboardservice(plan);
		
		return "plan/planSboard";
	}
	@RequestMapping("/plan/planwrite")
	public ModelAndView planwrite(PlanDTO plan,ModelAndView mv) {
		System.out.println("planwrite 호출완료");
		
		ArrayList<PlanDTO> placeview =planservice.placeservice(plan);
		
		//이 메소드는 planwrite.jsp파일을 호출할때 사용하는 메소드이다.
		mv.addObject("place",placeview);
		mv.setViewName("plan/planwrite");
		return mv;
	}
	@RequestMapping("/plan/planview")
		public ModelAndView planviews(ModelAndView mv,HttpServletRequest req,PlanDTO plan ,
				@RequestParam(value="pno",
					required=false,
		               defaultValue="1")int pno) {
		System.out.println("planview 호출완료");
		
		
		
		System.out.println("planview에서의 pno의 값"+pno);
		ArrayList<PlanDTO> planview = (ArrayList<PlanDTO>)planservice.planview(plan);
		
		mv.addObject("Pdto",planview);
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