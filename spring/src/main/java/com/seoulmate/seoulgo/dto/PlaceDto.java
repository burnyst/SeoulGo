package com.seoulmate.seoulgo.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaceDto {
	private int placeNo;
	private String placeName;
	private String addr1;
	private String addr2;
	private int cate;
	private String tel;
	private int reviewCount;
	private float placeRate;
	private List<String> imageNames;
	
	public PlaceDto() {
	}
	public PlaceDto(String placeName, String addr1, String addr2, int cate, String tel) {
		this.placeName = placeName;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.cate = cate;
		this.tel = tel;
	}
	
	public int getPlaceNo() {
		return placeNo;
	}
	public String getPlaceName() {
		return placeName;
	}
	public String getAddr1() {
		return addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public int getCate() {
		return cate;
	}
	public String getTel() {
		return tel;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public float getPlaceRate() {
		return placeRate;
	}
	public int getPlaceRate10X() {
		return ((int)(placeRate * 20))/2;
	}
	public List<String> getImageNames() {
		return imageNames;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public void setCate(int cate) {
		this.cate = cate;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public void setPlaceRate(float placeRate) {
		this.placeRate = placeRate;
	}
	public void setImageNames(List<String> imageNames) {
		this.imageNames = imageNames;
	}
	
	@Override
	public String toString() {
		return "PlaceDto [placeNo=" + placeNo + ", placeName=" + placeName + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", cate=" + cate + ", tel=" + tel + ", reviewCount=" + reviewCount + ", placeRate=" + placeRate
				+ ", imageNames=" + imageNames + "]";
	}
}
