package com.seoulmate.seoulgo.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoulmate.seoulgo.dao.ReviewDAO;
import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.ReviewDTO;
import com.seoulmate.seoulgo.page.PlacePage;
import com.seoulmate.seoulgo.page.ReviewPage;


@Service
public class ReviewService {

	@Autowired
	ReviewDAO rDAO;
	
	// 장소목록 조회
	public Object getPlaceListView(PlacePage placePage) {
		placePage.setTotalRow(rDAO.getTotalCnt());
		return rDAO.getListView(placePage);
	}
	// 장소정보 추출
	public ArrayList<PlaceDto> getPlaceInfo(int placeNo) {
		ArrayList<PlaceDto> Info = rDAO.getPlaceInfo(placeNo);
		return Info;
	}
	// 글 작성 처리
	public void insertReview(ReviewDTO rDTO, HttpSession session, ArrayList list) {

		rDAO.insertReview(rDTO, "reviewBoard");
		
		// DAO의 insertBoard() 함수 내 imgInfo 테이블에 insert 실행함수 호출
		for(int i=0; i<list.size(); i++) {
			HashMap map = (HashMap)list.get(i);
			rDTO.setPath((String)map.get("path"));
			rDTO.setOriName((String)map.get("oriName"));
			rDTO.setiSaveName((String)map.get("iSaveName"));
			rDTO.setLen((Long)map.get("len"));
			
			// DAO의 함수호출
			rDAO.insertReview(rDTO, "imgInfo");
		}
	}
	// 리뷰 상세정보 가져오기
	public ArrayList<ReviewDTO> getDetailList(ReviewPage reviewPage) {
		reviewPage.setTotalRow(rDAO.getCount(reviewPage.getPlaceNo()));
		ArrayList<ReviewDTO> list = rDAO.getDetailList(reviewPage);
		return list;
	}
	// 글수정하기 
	public void modifyReview(ReviewDTO rDTO) {
		rDAO.modifyReview(rDTO);
	}
	//상세보기관련  첨부파일목록조회
	public ArrayList<ReviewDTO> getFileInfo(int rNo) {
		ArrayList<ReviewDTO> list =rDAO.getFileInfo(rNo);
		return list;
	}
	// 글수정하기 - 기존 첨부파일 정보 삭제
	public void deleteFileInfo(int rNo) {
		rDAO.deleteFileInfo(rNo);
	}
	// 글수정하기 - 첨부파일 등록관련
	public void insertFileInfo(ReviewDTO rDTO1) {
		rDAO.insertReview(rDTO1, "imgInfo");
	}
	// 글 삭제
	public void deleteReview(ReviewDTO rDTO) {
		rDAO.deleteReview(rDTO);
	}
	// 좋아요 체크
	public int goodcheck(ReviewDTO rDTO) {
		return rDAO.goodcheck(rDTO);
	}
	// 좋아요 처리
	public void goodupdate (ReviewDTO rDTO){
		rDAO.goodupdate(rDTO);
	}
	// 좋아요 삭제
	public void gooddelete (ReviewDTO rDTO){
		rDAO.gooddelete(rDTO);
	}
	// 좋아요 수 검색
	public int goodgetcnt(int rNo){
		return rDAO.goodgetcnt(rNo);
	}
	// 싫어요 체크
	public int badcheck(ReviewDTO rDTO) {
		return rDAO.badcheck(rDTO);
	}
	// 싫어요 처리
	public void badupdate (ReviewDTO rDTO){
		rDAO.badupdate(rDTO);
	}
	// 싫어요 삭제
	public void baddelete (ReviewDTO rDTO){
		rDAO.baddelete(rDTO);
	}
	// 싫어요 수 검색
	public int badgetcnt(int rNo){
		return rDAO.badgetcnt(rNo);
	}
	// 더보기 상세정보 가져오기
	public ArrayList<ReviewDTO> getMoreList(ReviewPage reviewPage, String memberID) {
		reviewPage.setTotalRow(rDAO.getMoreCount(memberID));
		ArrayList<ReviewDTO> list = rDAO.getMoreList(reviewPage);
		return list;
	}
}
