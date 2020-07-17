package com.seoulmate.seoulgo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class AdminDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 회원 정보 조회
	public MemberDTO findMember(MemberDTO mdto) {
		return session.selectOne("admin.findMember", mdto);
	}

	// 회원 리스트
	public List<MemberDTO> gerMemberList() {
		return session.selectList("admin.getMemberList");
	}
	
}