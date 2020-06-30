package com.seoulmate.seoulgo.plan;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlanController {
	@RequestMapping("/plan")
	//http://127.0.0.1:9000/myapp/t/rm/board.co
	//http://127.0.0.1:9000/myapp/plan/plan - 안됨
	//http://127.0.0.1:9000/plan
	public String planpage() {
		System.out.println("planpage 함수 진입.");
		//이 함수는 plan.jsp파일을 보여줄때 사용하는 함수이다~!
		
		return "plan/plan";
	}
	@RequestMapping("/plan/planwrite")
	public String planwrite() {
		
		System.out.println("palnwrite함수 진입");
		//이 함수는 planwrite jsp파일을 보여줄때 사용하는 함수이다.
		
		return "plan/planwrite";
	}
	

}