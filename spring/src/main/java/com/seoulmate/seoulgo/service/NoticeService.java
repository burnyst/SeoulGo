package com.seoulmate.seoulgo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.NoticeDAO;
import com.seoulmate.seoulgo.dto.NoticeDTO;

@Service
public class NoticeService {

	@Autowired
	NoticeDAO nDAO;

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