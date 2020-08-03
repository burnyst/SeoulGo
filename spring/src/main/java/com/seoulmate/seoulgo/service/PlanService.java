package com.seoulmate.seoulgo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.PlanDAO;
import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PlanPage;

//이 클래스는 일정짜기의 서비스를 지원하는 서비스 클래스이다.
@Service
public class PlanService {

	@Autowired
	PlanDAO pdao;

	public List<PlanDTO> getplanboard(PlanPage page2) {
		List<PlanDTO>result = (List<PlanDTO>) pdao.getplanboard(page2);
		//System.out.println("result의 사이즈 - 서비스단 : "+result.size());
		return result;
	}

	public List<PlanDTO> planview(PlanPage plan) {
		return (List<PlanDTO>) pdao.planview(plan);
	}



	public ArrayList<PlanDTO> detailView(int pno) {
		return pdao.detailView(pno);
	}

	public void planWrited(PlanDTO plan, PlaceDto place, ArrayList planlist) {
		System.out.println("PlanService.planWrited() 진입="+plan);
		pdao.planWrited(plan, place, "plan");
		for (int i =0; i<planlist.size(); i++) {
			HashMap map = (HashMap)planlist.get(i);
			place.setPlaceNo((int)map.get("placeNo"));
			pdao.planWrited(plan, place, "planplace");
		}
	}
	
	public void planmodifinservice(PlanDTO plan,PlaceDto place, HttpServletRequest req) {
		System.out.println("planmodifinservice도착");
		Date planDate = null;
		String plandate = req.getParameter("plandate");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			planDate = fm.parse(plandate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("날짜변환중 오류발생.");
		}
		int planno = Integer.parseInt(req.getParameter("planNo"));
		String memberid = req.getParameter("memberid");
		String planplace = req.getParameter("planplace"); //planplace = plan에서 사용하는 placename
		String planTitle = req.getParameter("planTitle");
		String planCate = req.getParameter("plancate");

		plan.setPlanNo(planno);
		plan.setMemberid(memberid);
		plan.setPlanDate(planDate);
		place.setPlaceName(planplace);
		plan.setPlanTitle(planTitle);
		plan.setPlanCate(planCate);

		System.out.println("변수를 확인합니다.쿼리에 넣을 변수" + plan);

		pdao.planmodifin(plan);
	}

	public ArrayList<PlanDTO> placeservice(PlanDTO plan) {
		ArrayList<PlanDTO> result = pdao.placeview(plan);
		return result;
	}

	// 페이지 오브젝트를 활용한 페이징
	public int getTotalRow(PlanPage page2) {
		return pdao.totalRow(page2);
	}

	public int personrow(PlanPage page2) {
		return pdao.personrow(page2);
	}

	// 리스트
	public List<PlanDTO> getMemberList(PlanPage page) {
		System.out.println("PlanService.getMemberList()" + page);
		return pdao.getplanlist(page);
	}

	// 일정공유 게시판에 나올 데이터
	public ArrayList getSBoard(PlanPage page2) {
		System.out.println("PlanService.getSBoard()" + page2);
		ArrayList<PlanDTO> result = pdao.getSBoard(page2);
		return result;
	}

	public ArrayList<PlanDTO> plist(PlanDTO plan) {
		ArrayList<PlanDTO> result = pdao.plist(plan);
		return result;
	}

	// placeno를 이용해 place의 모든 정보를 가져오는 메소드
	public ArrayList<PlanDTO> choiceplace(int placeNo) {
		ArrayList<PlanDTO> result = pdao.choiceplace(placeNo);
		return result;
	}

//	public ArrayList<PlanDTO> choiceplace2(int placeNo2) {
//		ArrayList<PlanDTO> result = pdao.choiceplace2(placeNo2);
//		result.get(0).setPlaceNo2(placeNo2);
//		return result;
//	}
//
//	public ArrayList<PlanDTO> choiceplace3(int placeNo3) {
//		ArrayList<PlanDTO> result = pdao.choiceplace3(placeNo3);
//		result.get(0).setPlaceNo3(placeNo3);
//		return result;
//	}
//	
	public void plandelete(int planno) {
		pdao.deleteplan(planno);
	}

	public List<String> getplacename(int pno) {
		List<String> result = pdao.getplacename(pno);
		return result;
	}
	
	public List<PlanDTO> getplanno(String mem_id) {
		List<PlanDTO> result = pdao.getplanum(mem_id);
		return result;
	}

	public List<PlaceDto> getplacenamelist(PlanPage page2) {
		List<PlaceDto> result = pdao.getplacenamelist(page2);
		return result;
	}

	public List<PlaceDto> getplaceinfo(int planno) {
		List<PlaceDto> result = pdao.getplaceinfo(planno);
		return result;
	}

	public List<PlanDTO> getplanNofromplaceno(int placeno) {
		
		List<PlanDTO> result = pdao.getplanNofromplaceno(placeno);
		return result;
	}

}