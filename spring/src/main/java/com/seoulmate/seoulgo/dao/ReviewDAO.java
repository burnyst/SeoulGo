package com.seoulmate.seoulgo.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoulmate.seoulgo.dto.PlaceDto;
import com.seoulmate.seoulgo.dto.ReviewDTO;
import com.seoulmate.seoulgo.page.PlacePage;
import com.seoulmate.seoulgo.page.ReviewPage;

public class ReviewDAO extends SqlSessionDaoSupport {

	@Autowired
	SqlSessionTemplate session;

	// 장소 전체 게시물 수 조회
	public Integer getTotalCnt() {
		return session.selectOne("place.listCount");
	}
	// 장소 목록 조회
	public List<PlaceDto> getListView(PlacePage placePage) {
		return session.selectList("place.list", placePage);
	}
	// 장소 정보 추출
	public ArrayList<PlaceDto> getPlaceInfo(int placeNo) {
		return (ArrayList)session.selectList("place.detail", placeNo);
	}
	// 글쓰기 처리 DAO
	public void insertReview(ReviewDTO rDTO, String hint) {
		if(hint.equals("reviewBoard")) {
			session.insert("review.writeProc",rDTO);
		}else if(hint.equals("imgInfo")) {
			session.insert("review.insertImgInfo",rDTO);
		}
	}
	// 전체 게시글 수 가져오기
	public Integer getCount(int placeNo) {
		return session.selectOne("review.getCount", placeNo);
	}	
	// 리뷰 상세정보 가져오기
	public ArrayList<ReviewDTO> getDetailList(ReviewPage reviewPage) {
		return (ArrayList)session.selectList("review.getList", reviewPage);
	}
	// 글 수정하기
	public void modifyReview(ReviewDTO rDTO) {
		session.update("review.modifyReview", rDTO);
	}
	//상세보기관련  첨부파일목록조회
	public ArrayList<ReviewDTO> getFileInfo(int rNo) {
		return (ArrayList)session.selectList("review.imgInfo", rNo);
	}
	// 글수정하기 - 기존 첨부파일 정보 삭제
	public void deleteFileInfo(int rNo) {
		session.delete("review.deleteImgInfo", rNo);
	}
	// 글 삭제 - 실은 업데이트
	public void deleteReview(ReviewDTO rDTO) {
		session.update("review.deleteReview", rDTO);
	}
	// 좋아요 체크 처리
	public Integer goodcheck (ReviewDTO rDTO){
		return session.selectOne("review.goodCheck", rDTO);
	}
	// 좋아요 처리
	public void goodupdate (ReviewDTO rDTO){
		session.update("review.goodUpdate", rDTO);
	}
	// 좋아요 삭제
	public void gooddelete (ReviewDTO rDTO){
		session.delete("review.goodDelete", rDTO);
	}
	// 좋아요 수 검색
	public Integer goodgetcnt(int rNo){
		return session.selectOne("review.goodCount", rNo);
	}
	// 싫어요 체크 처리
	public Integer badcheck (ReviewDTO rDTO){
		return session.selectOne("review.badCheck", rDTO);
	}
	// 싫어요 처리
	public void badupdate (ReviewDTO rDTO){
		session.update("review.badUpdate", rDTO);
	}
	// 싫어요 삭제
	public void baddelete (ReviewDTO rDTO){
		session.delete("review.badDelete", rDTO);
	}
	// 싫어요 수 검색
	public Integer badgetcnt(int rNo){
		return session.selectOne("review.badCount", rNo);
	}

	
}