package com.seoulmate.seoulgo.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.PlanDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.MemberSearchPage;
import com.seoulmate.seoulgo.page.PageObject;
import com.seoulmate.seoulgo.page.PlanPage;


//이 클래스는 일정짜기의 서비스를 지원하는 서비스 클래스이다.
@Service
public class PlanService {
	  
	@Autowired
	PlanDAO pdao;
	
	
	public List<PlanDTO> getplanboard(PlanPage page2) {
		
		return (List<PlanDTO>) pdao.getplanboard(page2);
	}
	public List<PlanDTO> planview(PlanPage plan) {

		return (List<PlanDTO>)pdao.planview(plan);
	}
	public List<PlanDTO> paging(PageObject pInfo) {
		
		int start =(pInfo.getPage()-1)*pInfo.getPerPageNum()+1;
		int end = start+pInfo.getPerPageNum()-1;
		int perpagenum = pInfo.getPerPageNum();
		PlanDTO PDTO = new PlanDTO();
		PDTO.setStart(start);
		PDTO.setEnd(end);
		PDTO.setPerpagenum(perpagenum);
		System.out.println("paging메소드의 start와 end"+start+","+end+perpagenum);
		ArrayList<PlanDTO> list = (ArrayList<PlanDTO>)pdao.planSboard(PDTO);
		System.out.println("paging메소드의 list 변수의 값"+list);
		return list;
	}

	public ArrayList addrservice(int addrNo) {

		return pdao.addrdao(addrNo);
	}

	public ArrayList<PlanDTO> detailView(int pno) {
		return pdao.detailView(pno);
	}
	
	public void planwritedservice(PlanDTO plan, HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("planwritedservice도착 PlanDTO의 값"+plan);
		Date planDate = null;
		//현재 로그인한 유저의 아이디
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = principal.toString();
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
		int placeNo = Integer.parseInt(req.getParameter("placeNo"));
		plan.setMemberid(mem_id);
		plan.setPlanDate(planDate);
		plan.setPlanplace(planplace);
		plan.setPlanTitle(planTitle);
		plan.setPlanCate(planCate);
		plan.setPlanNo(placeNo);
		System.out.println("변수를 확인합니다."+plan);
		pdao.planwriteddao(plan);
	}

	
	public void planmodifinservice(PlanDTO plan,HttpServletRequest req) {
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


	public ArrayList<PlanDTO> placeservice(PlanDTO plan) {
		ArrayList<PlanDTO> result = pdao.placeview(plan);
		return result;
	}
	
	
	//페이지 오브젝트를 활용한 페이징

	public int getTotalRow(PlanPage page2) {
		return pdao.totalRow(page2);
	}
	public int personrow(PlanPage page2) {
		
		return pdao.personrow(page2);
	}
	public int sarchplaceint(PlanPage page) {

		return pdao.sarchplaceint(page);
	}
	// 리스트.
	public List<PlanDTO> getMemberList(PlanPage page) {
		System.out.println("PlanService.getMemberList()"+page);
		return pdao.getplanlist(page);
	}
	public ArrayList<PlanDTO> placeSarch(PlanPage page) {
		// TODO Auto-generated method stub
		return pdao.placeSarch(page);
	}
	
	//일정공유 게시판에 나올 데이터
	public ArrayList getSBoard(PlanPage page2) {
		System.out.println("PlanService.getSBoard()"+page2);
		ArrayList<PlanDTO> result = pdao.getSBoard(page2);
		return result;
	}

	public ArrayList<PlanDTO> plist(PlanDTO plan) {
		
		ArrayList<PlanDTO> result = pdao.plist(plan);
		return result;
	}
	//placeno를 이용해 place의 모든 정보를 가져오는 메소드
	public ArrayList choiceplace(int placeNo) {
		
		ArrayList<PlanDTO> result = pdao.choiceplace(placeNo);
		return result;
	}
	//memberid를 가져온다
	public String getmemberid(String memberid) {
		
		String result = pdao.getmemberid(memberid);
		return result;
	}
	public void plandelete(int planno) {
		pdao.deleteplan(planno);
	}
	
	

	


	
	

}
