package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.dto.NoticeDTO;
import com.seoulmate.seoulgo.dto.NoticeReplyDTO;
import com.seoulmate.seoulgo.page.NoticePage;

public class NoticeDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 댓글 작성자의 정보 가져오기
	public List<MemberDTO> getMemberInfo(NoticeReplyDTO nrdto) {
		List<MemberDTO> list = session.selectList("reply.getMemberInfo", nrdto);
		System.out.println("NoticeDAO.getMemberInfo() "+list);
		return list;
	}
	
	// 대댓글(댓글의 답글) 작성
	public void reply(NoticeReplyDTO nrdto) {
		session.insert("reply.reply", nrdto);
	}
	
	// 댓글 정보 가져오기
	public NoticeReplyDTO getReplyInfo(int nrNo) {
		return session.selectOne("reply.getReplyInfo", nrNo);
	}
	
	// 댓글 삭제
	public void delete(int nrNo) {
		session.delete("reply.delete", nrNo);
	}
	
	// 댓글 리스트 
	public ArrayList<NoticeReplyDTO> replyList(int nNo) {
		ArrayList<NoticeReplyDTO> replyList = (ArrayList)session.selectList("reply.replyList", nNo);
		return replyList;
	}
	
	// 댓글 작성
	public void commentProc(NoticeReplyDTO nrdto) {
		session.insert("reply.commentProc", nrdto);
	}
	
	// 공지사항 수정 처리
	public void modifyProc(NoticeDTO ndto) {
		session.update("notice.modifyProc", ndto);
	}
	
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

	// 관리자 권한 가져오기
	public String findMlevel(NoticeDTO ndto) {
		String mLevel = session.selectOne("notice.findMlevel", ndto);
		return mLevel;
	}
	
	// 공지사항 상세보기
	public NoticeDTO detailView(int nNo) {
		return session.selectOne("notice.detailView", nNo);
	}
	
	// 공지사항 목록
	public List<NoticeDTO> list(NoticePage notice) {
		List<NoticeDTO> list = session.selectList("notice.list", notice);
		return list;
	}
	
	// 공지사항 검색 결과수
	public int getCnt(NoticePage notice) {
		return session.selectOne("notice.getCnt", notice);
	}

	// 공지사항 작성 처리
	public void writeProc(NoticeDTO ndto) {
		session.insert("notice.writeProc", ndto);
	}
}