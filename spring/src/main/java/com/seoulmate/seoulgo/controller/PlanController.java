package com.seoulmate.seoulgo.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.service.PlanService;

@Controller
public class PlanController {
@Autowired
public PlanService planservice;
 
	//http://127.0.0.1:9000/plan/plan	
	@RequestMapping("/plan/plan")
	public ModelAndView planpage(
			ModelAndView mv, PlanDTO plan){
		//페이지 보기 요청이 들어오면 db에 저장된  내용이 출력되어야 한다.
		//해당 파일의 정보를 DB에서 가져와야 한다
		System.out.println("planpage진입요청함수");
		//1.파라미터받기 -필요없음.
		//2.비즈니스로직 실행
		ArrayList<PlanDTO> list = (ArrayList) planservice.viewplan(plan);
		//viewplan안에 저장되어있는 내용을 변수로 선언해야 한다.
		//해당 정보를 DB에서 가져와야 한다
		//3.model
		mv.addObject("DTOlist",list);
		System.out.println("list변수안의 내용"+list);
		//4.view
		mv.setViewName("plan/plan");
		return mv;
	}
	//이 메소드는 plan.jsp에서 수정하기 버튼을 눌렀을때 적용되는 리다이렉트 작동이다.
	@PostMapping("/planmodi_redirect")
	   public String exRedirect(HttpServletRequest request,  RedirectAttributes redirectAttributes) {
		
		String result2 = request.getParameter("Result2");
		if(result2==null) {
			System.out.println("if문 안에 들어옴"+result2);
			 String referer = request.getHeader("Referer");
			 return "redirect:"+ referer;
		}else {
			System.out.println("else문 안에 들어옴"+result2);
		   String projectUrl = "http://www.naver.com";
	       return "redirect:" + projectUrl;}  
	}
	
	//http://127.0.0.1:9000/plan/planmodi
	@RequestMapping("/plan/planmodi")
	public String planmodi(HttpServletRequest request) {
		System.out.println("planmodi 페이지 호출함수");
		//1.파라미터받기
		//2.비즈니스로직실행
		//3.모델, 뷰 제작
		
		return "plan/planmodi";
	}
	
	@RequestMapping("/plan/planwrite")
	public String planwrite() {
		System.out.println("planwrite 호출완료");
		//이 메소드는 planwrite.jsp파일을 호출할때 사용하는 메소드이다.
		return "plan/planwrite";
	}
	

	@RequestMapping("/plan/plandelete")
	public String plandelete() {
		System.out.println("plandelete 페이지 호출함수");
		return "plan/plandelete";
	}
	@RequestMapping("/plan/planSboard")
	public String planShare() {
		System.out.println("planShare 페이지 호출함수");
		return "plan/planSboard";
	}
	@RequestMapping("plan/viewplan")
	public String planview() {
		System.out.println("viewplan페이지 호출함수.,");
		return "plan/viewplan";
	}
}