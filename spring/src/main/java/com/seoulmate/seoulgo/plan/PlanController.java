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
		System.out.println("planpage �Լ� ����.");
		//�� �Լ��� plan.jsp������ �����ٶ� ����ϴ� �Լ��̴�~!
		
		return "plan/plan";
	}
	@RequestMapping("/plan/planwrite")
	public String planwrite() {
		
		System.out.println("palnwrite�Լ� ����");
		//�� �Լ��� planwrite jsp������ �����ٶ� ����ϴ� �Լ��̴�.
		
		return "plan/planwrite";
	}
	

}