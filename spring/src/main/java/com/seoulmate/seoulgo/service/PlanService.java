package com.seoulmate.seoulgo.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.controller.PlanController;
import com.seoulmate.seoulgo.dao.PlanDAO;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PageUtil;


//이 클래스는 일정짜기의 서비스를 지원하는 서비스 클래스이다.
@Service
public class PlanService {
	  
	@Autowired
	PlanDAO pdao;
	//내용목록형식으로 보기.
	public List<PlanDTO> paging(PlanDTO plan) {
		//페이지 보기 요청이 들어오면 db내의plan 테이블의 정보를 보여주어야 한다.	
		System.out.println("PlanService의 viewplan 진입"+plan);
		//null값을 줘도 전체를 셀렉트해서 보여주어야 한다.
		//dao에서 받은 값을 plandto에 넣어야한다
		
		return pdao.getDown(plan);
	}
	public ArrayList addrservice(int addrNo) {

		return pdao.addrdao(addrNo);
	}
	public List<PlanDTO> planview(PlanDTO plan) {

		return pdao.viewplan(plan);
	}
	public ArrayList<PlanDTO> dtoview(int pno) {
		// TODO Auto-generated method stub
		return pdao.detailView(pno);
	}
	
	public void planwritedservice(PlanDTO plan, HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("planwritedservice도착 PlanDTO의 값"+plan);
		Date planDate = null;
		//임시로 db에 넣은 더미값을 넣었다.
		String memberid = "abroad91";
		String plandate =req.getParameter("plandate");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			planDate = fm.parse(plandate);
			System.out.println(planDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("날짜변환중 오류발생.");
		}
		String planplace = req.getParameter("planplace");
		String planTitle = req.getParameter("planTitle");
		String planCate = req.getParameter("plancate");
		plan.setMemberid(memberid);
		plan.setPlanDate(planDate);
		plan.setPlanplace(planplace);
		plan.setPlanTitle(planTitle);
		plan.setPlanCate(planCate);
		System.out.println("변수를 확인합니다."+plan);
		pdao.planwriteddao(plan);
	}

	public void planmodifinservice(PlanDTO plan,HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("planmodifinservice도착");
		Date planDate = null;
		String plandate =req.getParameter("plandate");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			planDate = fm.parse(plandate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("날짜변환중 오류발생.");
		}
		int planno = Integer.parseInt(req.getParameter("planNo"));
		String memberid = req.getParameter("memberid");
		String planplace = req.getParameter("planplace");
		String planTitle = req.getParameter("planTitle");
		String planCate = req.getParameter("plancate");
		
		plan.setPlanNo(planno);
		plan.setMemberid(memberid);
		plan.setPlanDate(planDate);
		plan.setPlanplace(planplace);
		plan.setPlanTitle(planTitle);
		plan.setPlanCate(planCate);
		
		System.out.println("변수를 확인합니다.쿼리에 넣을 변수"+plan);
		
		pdao.planmodifin(plan);
	}

	public void planSboardservice(PlanDTO plan) 
	{
		pdao.planSboard(plan);
		
	}
	public PageUtil getPageInfo(int nowPage) {
		int totalCount= pdao.getTotalCnt();
		
		//PageUtil(보고싶은페이지,   전체게시물수);
		//PageUtil(int nowPage, int totalCount);
		PageUtil pInfo = new PageUtil(nowPage, totalCount);
		//PageUtil객체생성자에서는 내부적으로
		//	한페이지당 보여주고 싶은 게시물의 개수는 3
		//	페이지 이동 기능은 3개까지 지정
		return pInfo;
	}
	public ArrayList<PlanDTO> placeservice(PlanDTO plan) {
		// TODO Auto-generated method stub
		
		
		
		ArrayList<PlanDTO> result = pdao.placeview(plan);
		return result;
	}
	

}
