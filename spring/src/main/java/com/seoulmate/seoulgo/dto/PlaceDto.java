package com.seoulmate.seoulgo.dto;

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
	private List<String> words;
	
	public PlaceDto() {
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
	public int getPlaceRate20X() {
		return (int)(placeRate * 20);
	}
	public List<String> getImageNames() {
		return imageNames;
	}
	public List<String> getWords() {
		return words;
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
	public void setWords(List<String> words) {
		this.words = words;
	}
	
	@Override
	public String toString() {
		return "PlaceDto [placeNo=" + placeNo + ", placeName=" + placeName + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", cate=" + cate + ", tel=" + tel + ", reviewCount=" + reviewCount + ", placeRate=" + placeRate
				+ ", imageNames=" + imageNames + ", words=" + words + "]";
	}
}
