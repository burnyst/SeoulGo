package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PlanPage;

@Repository
public class PlanDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<PlanDTO> getplanboard(PlanPage page2) {
		List<PlanDTO> result = session.selectList("plandata.getplanboard", page2);
		return result;
	}

	// planSboard에서 사용하는 DAO
	public List<PlanDTO> planview(PlanPage page2) {
		List result = (List) session.selectList("plandata.plandatav", page2);
		return result;
	}

	public ArrayList getDown(PlanDTO plan) {
		System.out.println("planDAO의 getdown()도착");
		ArrayList result = (ArrayList) session.selectList("plandata.planSview", plan);
		// System.out.println("result의 값 내용 "+result);
		return result;
	}

	public ArrayList detailView(int pno) {
		return (ArrayList) session.selectList("plandata.planview", pno);
	}

	public void planWrited(PlanDTO plan, PlaceDto place, String hint) {
		System.out.println("planDAO 글쓰기 진입 plan=" + plan + ", place=" + place);
		if(hint.equals("plan")) {
			session.insert("plandata.planinsert", plan);
		} else if(hint.equals("planplace")) {
			HashMap map = new HashMap();
			System.out.println("PlanNo 들고오니? "+plan.getPlanNo());
			map.put("planNo", plan.getPlanNo());
			map.put("placeNo", place.getPlaceNo());
			session.insert("plandata.planinsert2", map);
		}
	}
	
	public void planmodifin(PlanDTO plan) {
		session.update("plandata.planupdate", plan);
	}

	// 공유 게시판에서의 리스트 보기.
	public ArrayList<PlanDTO> planSboard(PlanDTO plan) {
		ArrayList<PlanDTO> result = (ArrayList) session.selectList("plandata.listView", plan);
		return result;
	}

	public ArrayList<PlanDTO> placeview(PlanDTO plan) {
		ArrayList<PlanDTO> result = (ArrayList) session.selectList("plandata.placeview", plan);
		return result;
	}

	// plan의 총 게시글 수 조회
	public int totalRow(PlanPage page2) {
		int result = session.selectOne("plandata.totalCnt", page2);
		return result;
	}

	// 개인창의 총 게시글 조회
	public int personrow(PlanPage page2) {
		int result = session.selectOne("personalCnt", page2);
		return result;
	}

	public List<PlanDTO> getplanlist(PlanPage page) {
		System.out.println("getplanlist" + page);
		List<PlanDTO> list = session.selectList("plandata.paging", page);
		System.out.println("getplanlist 나감" + list);
		return list;
	}

	public ArrayList<PlanDTO> getSBoard(PlanPage page2) {
		ArrayList result = (ArrayList) session.selectList("plandata.planSboard", page2);
		return result;
	}

	public ArrayList<PlanDTO> getplanno() {
		ArrayList planno = (ArrayList) (session.selectList("plandata.getplanno"));
		return planno;
	}

	public ArrayList<PlanDTO> plist(PlanDTO plan) {

		ArrayList result = (ArrayList) session.selectList("plandata.plist", plan);
		return result;
	}

	// 선택한 장소의 정보를 찾아온다
	public ArrayList<PlanDTO> choiceplace(int placeNo) {
		ArrayList result = (ArrayList) session.selectList("plandata.placechoice", placeNo);
		return result;
	}

	// 선택한 장소의 정보를 찾아온다2
	public ArrayList<PlanDTO> choiceplace2(int placeNo2) {
		ArrayList result = (ArrayList) session.selectList("plandata.placechoice2", placeNo2);
		return result;
	}

	// 선택한 장소의 정보를 찾아온다3
	public ArrayList<PlanDTO> choiceplace3(int placeNo3) {
		ArrayList result = (ArrayList) session.selectList("plandata.placechoice3", placeNo3);
		return result;
	}

	public void deleteplan(int planno) {
		session.delete("plandata.deleteplan", planno);
	}

	public List<String> getplacename(int pno) {
		List<String> result = session.selectList("plandata.getplaceName", pno);
		return result;
	}

	public List<PlanDTO> getplanum(String mem_id) {
		List<PlanDTO> result = session.selectList("plandata.getplannum", mem_id);
		return result;
	}

	public List<PlaceDto> getplacenamelist(PlanPage page2) {
		List<PlaceDto> result = session.selectList("plandata.getplacenamelist",page2);
		return result;
	}

	public List<PlaceDto> getplaceinfo(int planno) {
		List<PlaceDto> result = session.selectList("plandata.getplaceinfo",planno);
		return result;
	}

	public List<PlanDTO> getplanNofromplaceno(int placeno) {
		List<PlanDTO> result = session.selectList("plandata.getplanNofromplaceno",placeno);
		return result;
	}

	public List<String> getplaceimagename(int placeno) {
		List<String> result = session.selectList("getplaceimagename", placeno);
		return result;
	}

	public List getpNo(int pno) {
		return session.selectList("plandata.getpNo", pno);
	}
	
}