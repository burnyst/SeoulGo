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

	public ArrayList detailView(int pno) {
		// TODO Auto-generated method stub
		return (ArrayList)session.selectList("plandata.planview",pno);
	}

	public void planwriteddao(PlanDTO plan) {
		// TODO Auto-generated method stub
		session.insert("plandata.planinsert", plan);
	}

	public void planmodifin(PlanDTO plan) {
		// TODO Auto-generated method stub
		session.update("plandata.planupdate",plan);
	}

	public List planSboard(PlanDTO plan) {
		
		return (List)session.selectList("plandata.planSview");
	}
}
