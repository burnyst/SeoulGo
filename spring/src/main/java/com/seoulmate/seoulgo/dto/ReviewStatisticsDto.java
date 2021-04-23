package com.seoulmate.seoulgo.dto;

public class ReviewStatisticsDto {
	private int rNo;
	private int placeNo;
	private int reviewCount;
	private float placeRate;
	
	public ReviewStatisticsDto() {
	}
	public ReviewStatisticsDto(int rNo) {
		this.rNo = rNo;
	}
	
	public int getrNo() {
		return rNo;
	}
	public int getPlaceNo() {
		return placeNo;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public float getPlaceRate() {
		return placeRate;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public void setPlaceRate(float placeRate) {
		this.placeRate = placeRate;
	}

	@Override
	public String toString() {
		return "ReviewStatisticsDto [rNo=" + rNo + ", placeNo=" + placeNo + ", reviewCount=" + reviewCount
				+ ", placeRate=" + placeRate + "]";
	}
}
