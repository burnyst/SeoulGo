package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoulmate.seoulgo.dto.PlanDTO;

@Repository
public class PlanDAO {
	
	@Autowired
	SqlSessionTemplate session;
	 
	public ArrayList getDown(PlanDTO plan) {
		
	System.out.println("planDAO의 getdown()도착"+plan);
	
	ArrayList result = (ArrayList) session.selectList("plandata.plandatat",plan);
	
	//System.out.println("result의 값 내용 "+result);
		return result;
	}
	public ArrayList addrdao(int addrNo) {
		System.out.println("addrdao도착"+addrNo);
		ArrayList result = (ArrayList)session.selectList("plandata.addr2", addrNo);
		System.out.println("addrdao나감"+addrNo);
		return result;
	}
	public ArrayList viewplan(PlanDTO plan) {
		
		ArrayList result = (ArrayList)session.selectList("plandata.plandatav", plan);
		
		return result;
	}
	public ArrayList detailView(int pno) {
		// TODO Auto-generated method stub
		return (ArrayList)session.selectList("plandata.planview",pno);
	}

	public void planwriteddao(PlanDTO plan) {
		// TODO Auto-generated method stub
		session.insert("plandata.planinsert", plan);
		System.out.println("planDAO 글쓰기 진입:"+plan);
		session.insert("plandata.planinsert2",plan);
	}

	public void planmodifin(PlanDTO plan) {
		// TODO Auto-generated method stub
		session.update("plandata.planupdate",plan);
	}

	public List planSboard(PlanDTO plan) {
		
		return (List)session.selectList("plandata.planSview");
	}
	public   int   getTotalCnt() {
		//select실행결과로 리턴받는 행수가 한개이면      selectOne()
		//select실행결과로 리턴받는 행수가 한개이상이면 selectList()	
		return (Integer)session.selectOne("plandata.totalCnt");
	}
	public ArrayList<PlanDTO> placeview(PlanDTO plan) {
		
		
		ArrayList<PlanDTO> result = (ArrayList)session.selectList("plandata.placeview", plan);
		return result;
	}
	

}
