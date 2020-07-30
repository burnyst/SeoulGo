package com.seoulmate.seoulgo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlanDTO {
	private int planNo;
	private Date planDate;
	private String planTitle;
	private String planCate;
	private String memberid;

	private String placeName;
	private List<String> placename;

	private String addr1;
	private String addr2;

	private int placeNo, placeNo2, placeNo3;
	private int addrNo;
	private int start; // 시작글번호
	private int end; // 끝글번호
	private int nowPage; // 보고싶은 글 번호
	private int startRow, endRow;// 현재 페이지의 첫글과 끝글번호

	private int perpagenum;
	private int rno;

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPlaceNo2() {
		return placeNo2;
	}

	public void setPlaceNo2(int placeNo2) {
		this.placeNo2 = placeNo2;
	}

	public int getPlaceNo3() {
		return placeNo3;
	}

	public void setPlaceNo3(int placeNo3) {
		this.placeNo3 = placeNo3;
	}

	public List<String> getPlacename() {
		return placename;
	}

	public void setPlacename(List<String> placename) {
		this.placename = placename;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public String getPlanDate2() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년-MM월-dd일");

		return fmt.format(planDate);
	}

	public void setPlanDate(Date planDate) {

		this.planDate = planDate;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPlanCate() {
		return planCate;
	}

	public void setPlanCate(String planCate) {
		this.planCate = planCate;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}

	public int getPerpagenum() {
		return perpagenum;
	}

	public void setPerpagenum(int perpagenum) {
		this.perpagenum = perpagenum;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public void setPlanplace(String planplace) {

	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Override
	public String toString() {
		return "PlanDTO [planNo=" + planNo + ", planDate=" + planDate + ", planTitle=" + planTitle + ", planCate="
				+ planCate + ", memberid=" + memberid + ", placeName=" + placeName + ", placename=" + placename
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", placeNo=" + placeNo + ", placeNo2=" + placeNo2
				+ ", placeNo3=" + placeNo3 + ", addrNo=" + addrNo + ", start=" + start + ", end=" + end + ", nowPage="
				+ nowPage + ", startRow=" + startRow + ", endRow=" + endRow + ", perpagenum=" + perpagenum + ", rno="
				+ rno + "]";
	}

}