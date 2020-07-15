package com.seoulmate.seoulgo.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.service.PlanService;


@Controller
public class PlanController {
@Autowired
public PlanService planservice;
 
	//http://127.0.0.1:9000/plan/plan	?nowPage=1
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(
			ModelAndView mv, PlanDTO plan ){
		System.out.println("planpage진입요청함수");
		

		ArrayList<PlanDTO> list = (ArrayList) planservice.viewplan(plan);
		mv.addObject("DTOlist",list);
		mv.setViewName("plan/plan");
		return mv;
	}
	//http://127.0.0.1:9000/plan/planmodi
	@RequestMapping("/plan/planmodi")
	public ModelAndView planredirect(ModelAndView mv,HttpServletRequest req) {
		int pno  =  Integer.parseInt(req.getParameter("pno"));//글번호
		System.out.println("planmodi도착, pno의 숫자: "+pno);
		ArrayList<PlanDTO> pdto = (ArrayList)planservice.dtoview(pno);//DTO에 내용 넣기.
		mv.setViewName("/plan/planmodi");
		mv.addObject("Pdto",pdto);
		return mv;
	}
	
	@PostMapping("/plan/planmodifin")
	public ModelAndView planmodifin(ModelAndView mv,HttpServletRequest req, HttpServletResponse res, PlanDTO plan) {
		//1.작성된 정보를 db에 업데이트 작업을 한다.
		
		planservice.planmodifinservice(plan, req);
		
		//db에서 정보를 가져와 출력
		ArrayList<PlanDTO> list = (ArrayList) planservice.viewplan(plan);
		mv.addObject("DTOlist",list);
		mv.setViewName("plan/plan");
		return mv;
	}
	
	//1.작성된 정보를  2.db로 날리는 작업 
	@PostMapping("/plan/planwrited")
	public ModelAndView planwriteed(ModelAndView mv,HttpServletRequest req,PlanDTO plan) {
		planservice.planwritedservice(plan,req);
		//3.그 정보를 갱신해서 db에서 가져와 출력
		ArrayList<PlanDTO> list = (ArrayList) planservice.viewplan(plan);
		mv.addObject("DTOlist",list);
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
	public String planwrite() {
		System.out.println("planwrite 호출완료");
		//이 메소드는 planwrite.jsp파일을 호출할때 사용하는 메소드이다.
		return "plan/planwrite";
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