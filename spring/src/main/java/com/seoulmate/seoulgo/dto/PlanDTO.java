package com.seoulmate.seoulgo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDTO {
	private int planNo;
	private Date planDate;
	private String planTitle;
	private String planCate;
	private String memberid;
	
	
	private String place;
	
	private String addr1;
	private String addr2;
	private int	   placeNo;
	private int    addrNo;
	private int		start; 		//시작글번호
	private	int		end;		//끝글번호
	private int		nowPage;	//보고싶은 글 번호
	
	
	

	
	
	
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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
		return planDate;}
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
	@Override
	public String toString() {
		return "PlanDTO [planNo=" + planNo + ", planDate=" + planDate + ", planTitle=" + planTitle + ", planCate="
				+ planCate + ", memberid=" + memberid + ", place=" + place + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", placeNo=" + placeNo + ", addrNo=" + addrNo + ", start=" + start + ", end=" + end + ", nowPage="
				+ nowPage + "]";
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

	public String getPlanplace() {
		return place;
	}

	public void setPlanplace(String planplace) {
		this.place = planplace;
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
	

}
