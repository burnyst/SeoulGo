package com.seoulmate.seoulgo.dto;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

// 리뷰 관련 데이터를 처리하는 DTO
public class ReviewDTO {

	// 게시글 필드 선언
	private int rNo; 			// 글 번호
	private String rTitle; 		// 글 제목
	private String rContent; 	// 글 내용
	private Date rDate; 		// 글 작성일
	private int rGood;			// 좋아요 수
	private int rBad;			// 싫어요 수
	private Date rVisit;		// 장소 방문일
	private String rCate;		// 여행자 유형
	private float rRate;		// 여행자 평점
	private int placeNo;		// 장소 번호
	
	// 파일 업로드 관련 필드 선언
	private String memberID;	// 글 작성자
	private long len; 			// 파일의 용량
	private String path;		// 파일의 저장위치
	private int fileCount;		// 첨부 파일 수
	private String oriName; 	// 원래 이름
	private String iSaveName; 	// 저장된 이름
	
	// 회원 프로필 사진 처리 필드
	private String proSaveName;
	
	// 첨부파일은  MultipartFile 타입으로 처리한다.
	private MultipartFile[] files; // 첨부파일들

	// getter, setter
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public int getrGood() {
		return rGood;
	}
	public void setrGood(int rGood) {
		this.rGood = rGood;
	}
	public int getrBad() {
		return rBad;
	}
	public void setrBad(int rBad) {
		this.rBad = rBad;
	}

	public String getrVisit2() { // View에서 날짜 변환용 getter
		SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd");
		return fmt.format(rVisit); 
	}
	public Date getrVisit() {
		return rVisit;
	}
	public void setrVisit(Date rVisit) {
		this.rVisit = rVisit;
	}
	public String getrCate() {
		return rCate;
	}
	public void setrCate(String rCate) {
		this.rCate = rCate;
	}
	public float getrRate() {
		return rRate;
	}
	public void setrRate(float rRate) {
		this.rRate = rRate;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getplaceNo() {
		return placeNo;
	}
	public void setplaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getiSaveName() {
		return iSaveName;
	}
	public void setiSaveName(String iSaveName) {
		this.iSaveName = iSaveName;
	}
	public String getProSaveName() {
		return proSaveName;
	}
	public void setProSaveName(String proSaveName) {
		this.proSaveName = proSaveName;
	}
	@Override
	public String toString() {
		return "ReviewDTO [rNo=" + rNo + ", rTitle=" + rTitle + ", rContent=" + rContent + ", rDate=" + rDate
				+ ", rGood=" + rGood + ", rBad=" + rBad + ", rVisit=" + rVisit + ", rCate=" + rCate + ", rRate=" + rRate
				+ ", placeNo=" + placeNo + ", memberID=" + memberID + ", len=" + len + ", path=" + path + ", fileCount="
				+ fileCount + ", oriName=" + oriName + ", iSaveName=" + iSaveName + ", proSaveName=" + proSaveName
				+ ", files=" + Arrays.toString(files) + "]";
	}



}