package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoulmate.seoulgo.dto.PlanDTO;
import com.seoulmate.seoulgo.page.PageObject;
import com.seoulmate.seoulgo.page.PlanPage;

@Repository
public class PlanDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public List<PlanDTO> getplanboard(PlanPage page2) {
		List result = (List) session.selectList("plandata.getplanboard", page2);
		return result;
	}
	//planSboard에서 사용하는 DAO
	public List<PlanDTO> planview(PlanPage page2) {
		
		List result = (List)session.selectList("plandata.plandatav", page2);
		return result;
	}
	public ArrayList getDown(PlanDTO plan) {
		
	System.out.println("planDAO의 getdown()도착");
	
	ArrayList result = (ArrayList) session.selectList("plandata.planSview",plan);
	
	//System.out.println("result의 값 내용 "+result);
		return result;
	}
	public ArrayList addrdao(int addrNo) {
		//System.out.println("addrdao도착"+addrNo);
		ArrayList result = (ArrayList)session.selectList("plandata.addr2", addrNo);
		System.out.println("addrdao나감"+addrNo);
		return result;
	}
	
	public ArrayList detailView(int pno) {
		// TODO Auto-generated method stub
		return (ArrayList)session.selectList("plandata.planview",pno);
	}

	public void planwriteddao(PlanDTO plan) {
		session.insert("plandata.planinsert", plan);
		System.out.println("planDAO 글쓰기 진입:"+plan);
		session.insert("plandata.planinsert2",plan);
	}

	public void planmodifin(PlanDTO plan) {
		// TODO Auto-generated method stub
		session.update("plandata.planupdate",plan);
	}
	// 공유 게시판에서의 리스트 보기.
	public ArrayList<PlanDTO> planSboard(PlanDTO plan) {
		ArrayList<PlanDTO> result = (ArrayList)session.selectList("plandata.listView",plan);
		return result;
	}
	
	public ArrayList<PlanDTO> placeview(PlanDTO plan) {
		ArrayList<PlanDTO> result = (ArrayList)session.selectList("plandata.placeview", plan);
		return result;
	}
	//plan의 총 게시글 수 조회
	public int totalRow(PlanPage page2) {
		int result = session.selectOne("plandata.totalCnt",page2);
		return result;
	}
	//place의 총 게시글 조회
	public int sarchplaceint(PlanPage page) {
		int result = session.selectOne("plandata.placetotalCnt",page);
		return result;
	}
	
	public List<PlanDTO> getplanlist(PlanPage page) {
		System.out.println("getplanlist"+page);
		List<PlanDTO> list = session.selectList("plandata.paging", page);
		System.out.println("getplanlist 나감"+list);
		return list;
	}
	public ArrayList<PlanDTO> getSBoard(PlanPage page2) {
		ArrayList result = (ArrayList) session.selectList("plandata.planSboard", page2);
		return result;
	}
	public  ArrayList<PlanDTO> getplanno() {
		ArrayList planno = (ArrayList) (session.selectList("plandata.getplanno"));
		return planno;
	}

	public ArrayList<PlanDTO> plist(PlanDTO plan) {

		ArrayList result = (ArrayList) session.selectList("plandata.plist", plan);
		return result;
	}
	//장소 리스트 검색
	public ArrayList<PlanDTO> placeSarch(PlanPage page) {
		
		ArrayList result = (ArrayList) session.selectList("plandata.placesarch", page);
		return result;
	}
	//선택한 장소의 정보를 찾아온다
	public ArrayList<PlanDTO> choiceplace(int placeNo) {
		ArrayList result  =(ArrayList) session.selectList("plandata.placechoice", placeNo);
		return result;
	}
	//memberid를 찾아온다.
	public String getmemberid(String memberid) {
		String result = session.selectOne("plandata.getmemberid",memberid); 
		return result;
	}
	
	

	

	
}
