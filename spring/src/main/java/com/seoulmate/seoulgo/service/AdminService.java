package com.seoulmate.seoulgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.AdminDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.page.MemberSearchPage;

@Service
public class AdminService {

	@Autowired
	AdminDAO aDAO;

	// 회원 리스트 검색 결과수
	public int searchList(MemberSearchPage search) {
		return aDAO.searchList(search);
	}
	
	// 회원 리스트에서 회원 탈퇴 시키기
	public void deleteAccount(MemberDTO mdto) {
		aDAO.deleteAccount(mdto);
	}
	
	// 회원 정보 조회
	public MemberDTO findMember(MemberDTO mdto) {
		return aDAO.findMember(mdto);
	}
	
	// 회원 리스트
	public List<MemberDTO> getMemberList(MemberSearchPage search) {
		System.out.println("AdminService.getMemberList()"+search);
		return aDAO.gerMemberList(search);
	}
}