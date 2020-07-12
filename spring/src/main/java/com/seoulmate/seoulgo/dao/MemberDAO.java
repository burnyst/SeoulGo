package com.seoulmate.seoulgo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;

public class MemberDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;
	
	// 회원탈퇴
	public MemberDTO deleteSuccess(MemberDTO mdto) {
		return mdto;
	}

}
