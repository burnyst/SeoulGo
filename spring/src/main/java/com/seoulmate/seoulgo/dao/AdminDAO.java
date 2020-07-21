package com.seoulmate.seoulgo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.page.MemberSearchPage;

public class AdminDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 회원 리스트 검색 결과수
	public int searchList(MemberSearchPage search) {
		return session.selectOne("admin.searchList", search);
	}
	
	// 회원 리스트에서 회원 탈퇴 시키기
	public void deleteAccount(MemberDTO mdto) {
		session.update("admin.deleteAccount", mdto);
	}

	// 회원 정보 조회
	public MemberDTO findMember(MemberDTO mdto) {
		return session.selectOne("admin.findMember", mdto);
	}

	// 회원 리스트
	public List<MemberDTO> gerMemberList(MemberSearchPage search) {
		List<MemberDTO> list = session.selectList("admin.getMemberList", search);
		return list;
	}
}