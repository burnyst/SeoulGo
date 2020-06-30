package com.seoulmate.seoulgo.plan;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlanController {
	@RequestMapping("/plan")
	//http://127.0.0.1:9000/myapp/t/rm/board.co
	//http://127.0.0.1:9000/myapp/plan/plan - �ȵ�
	//http://127.0.0.1:9000/plan
	public String planpage() {
		System.out.println("planpage 호출완료");
		//이 메솓는 planpage를 호출하는 메소드이다.
		
		return "plan/plan";
	}
	@RequestMapping("/plan/planwrite")
	public String planwrite() {
		
		System.out.println("planwrite 호출완료");
		//이 메소드는 planwrite.jsp파일을 호출할때 사용하는 메소드이다.
		
		return "plan/planwrite";
	}
	

}