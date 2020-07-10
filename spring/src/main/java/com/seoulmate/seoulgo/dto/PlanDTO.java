package com.seoulmate.seoulgo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDTO {
	private int planNo;
	private Date planDate;
	private String planTitle;
	private int planCate;
	
	//리다이렉션
	private String Result2;
	
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

	private String memberid;
	
	private int		start; 		//시작글번호
	private	int		end;		//끝글번호
	
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
		this.planDate =  planDate;
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
				+ planCate + ", Result2=" + Result2 + ", memberid=" + memberid + ", start=" + start + ", end=" + end
				+ "]";
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getPlanCate() {
		return planCate;
	}

	public void setPlanCate(int planCate) {
		this.planCate = planCate;
	}

	public String getResult2() {
		return Result2;
	}

	public void setResult2(String result2) {
		Result2 = result2;
	}
	

}
