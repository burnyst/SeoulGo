package com.seoulmate.seoulgo.dto;

public class MemberDTO {
	
	private String memberID;
	private String memberPW;
	private String mName;
	private String nickname;
	private Character gender;
	private String birth;
	private String enrollDate;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String mLevel;
	
	public String getMemberID() {
		return memberID;
	}
	
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	public String getMemberPW() {
		return memberPW;
	}
	
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	
	public String getmName() {
		return mName;
	}
	
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Character getGender() {
		return gender;
	}
	
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getEnrollDate() {
		return enrollDate;
	}
	
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	public String getPhone1() {
		return phone1;
	}
	
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	public String getPhone2() {
		return phone2;
	}
	
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	public String getPhone3() {
		return phone3;
	}
	
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getmLevel() {
		return mLevel;
	}
	
	public void setmLevel(String mLevel) {
		this.mLevel = mLevel;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberID=" + memberID + ", memberPW=" + memberPW + ", mName=" + mName + ", nickname="
				+ nickname + ", gender=" + gender + ", birth=" + birth + ", enrollDate=" + enrollDate + ", phone1="
				+ phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", email=" + email + ", mLevel=" + mLevel
				+ "]";
	}
}
