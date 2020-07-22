package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.NoticeDTO;

public class NoticeDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 공지사항 삭제
	public void delete(NoticeDTO ndto) {
		session.update("notice.delete", ndto);
	}
	
	// 공지사항 정보 가져오기
	public NoticeDTO findNo(int nNo) {
		return session.selectOne("notice.findNo", nNo);
	}
	
	// 조회수 증가
	public void cntUpdate(int nNo) {
		session.update("notice.cntUpdate", nNo);
	}

	// 글 상세보기
	public NoticeDTO detailView(int nNo) {
		return session.selectOne("notice.detailView", nNo);
	}
	
	// 글목록
	public ArrayList<NoticeDTO> list(NoticeDTO ndto) {
		ArrayList<NoticeDTO> list = (ArrayList)session.selectList("notice.list", ndto);
		return list;
	}

	// 글쓰기 처리
	public void writeProc(NoticeDTO ndto) {
		session.insert("notice.writeProc", ndto);
	}
}