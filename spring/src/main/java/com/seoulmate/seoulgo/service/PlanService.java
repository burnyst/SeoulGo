package com.seoulmate.seoulgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.PlanDAO;
import com.seoulmate.seoulgo.dto.PlanDTO;

//이 클래스는 일정짜기의 서비스를 지원하는 서비스 클래스이다.
@Service
public class PlanService {
	  
	@Autowired
	PlanDAO pdao;
	//내용목록형식으로 보기.
	public List<PlanDTO> viewplan(PlanDTO plan) {
		//페이지 보기 요청이 들어오면 db내의plan 테이블의 정보를 보여주어야 한다.	
		System.out.println("PlanService의 viewplan 진입"+plan);
		//null값을 줘도 전체를 셀렉트해서 보여주어야 한다.
		//dao에서 받은 값을 plandto에 넣어야한다

		return pdao.getDown(plan);
	}
	
	
}
