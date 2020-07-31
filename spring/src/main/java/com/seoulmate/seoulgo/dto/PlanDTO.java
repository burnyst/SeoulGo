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

	private List<String> placeName;

	private String addr1;
	private String addr2;

	private int placeNo, placeNo2, placeNo3;
	private int addrNo;
	
	



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



	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}



	public int getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}





	public void setPlanplace(String planplace) {

	}



	@Override
	public String toString() {
		return "PlanDTO [planNo=" + planNo + ", planDate=" + planDate + ", planTitle=" + planTitle + ", planCate="
				+ planCate + ", memberid=" + memberid + ", placeName=" + placeName + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", placeNo=" + placeNo + ", placeNo2=" + placeNo2 + ", placeNo3=" + placeNo3 + ", addrNo="
				+ addrNo   + "]";
	}

	public List<String> getPlaceName() {
		return placeName;
	}

	public void setPlaceName(List<String> placeName) {
		this.placeName = placeName;
	}



}