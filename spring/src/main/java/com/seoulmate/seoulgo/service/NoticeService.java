package com.seoulmate.seoulgo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.NoticeDAO;
import com.seoulmate.seoulgo.dto.MemberDTO;
import com.seoulmate.seoulgo.dto.NoticeDTO;
import com.seoulmate.seoulgo.dto.NoticeReplyDTO;

@Service
public class NoticeService {

	@Autowired
	NoticeDAO nDAO;

	// 댓글 작성자의 정보 가져오기
	public List<MemberDTO> getMemberInfo(NoticeReplyDTO nrdto) {
		List<MemberDTO> list = nDAO.getMemberInfo(nrdto);
		return list;
	}
	
	// 대댓글(댓글의 답글) 작성
	public void reply(NoticeReplyDTO nrdto) {
		nDAO.reply(nrdto);
	}
	
	// 댓글 정보 가져오기
	public NoticeReplyDTO getReplyInfo(int nrNo) {
		NoticeReplyDTO replyInfo = nDAO.getReplyInfo(nrNo);
		return replyInfo;
	}
	
	// 댓글 삭제
	public void delete(int nrNo) {
		nDAO.delete(nrNo);
	}
	
	// 댓글 리스트
	public ArrayList<NoticeReplyDTO> replyList(int nNo) {
		return nDAO.replyList(nNo);
	}
	
	// 댓글 작성
	public void commentProc(NoticeReplyDTO nrdto) {
		nDAO.commentProc(nrdto);
	}
	
	// 공지사항 수정 처리
	public void modifyProc(NoticeDTO ndto) {
		nDAO.modifyProc(ndto);
	}
	
	// 공지사항 삭제
	public void delete(NoticeDTO ndto) {
		nDAO.delete(ndto);
	}
	
	// 공지사항 정보 가져오기
	public NoticeDTO findNo(int nNo) {
		NoticeDTO ndto = nDAO.findNo(nNo);
		return ndto;
	}
	
	// 조회수 증가
	public void cntUpdate(int nNo) {
		nDAO.cntUpdate(nNo);
	}

	// 관리자 권한 가져오기
	public String findMlevel(NoticeDTO ndto) {
		return nDAO.findMlevel(ndto);
	}
	
	// 글 상세보기
	public NoticeDTO detailView(int nNo) {
		NoticeDTO ndto = nDAO.detailView(nNo);
		return ndto;
	}
	
	// 글목록
	public ArrayList<NoticeDTO> list(NoticeDTO ndto) {
		return nDAO.list(ndto);
	}

	// 글쓰기 처리
	public void writeProc(NoticeDTO ndto) {
		nDAO.writeProc(ndto);
	}
}