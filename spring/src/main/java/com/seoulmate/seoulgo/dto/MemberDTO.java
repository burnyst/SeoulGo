package com.seoulmate.seoulgo.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	
	private String memberID;
	private String memberPW;
	private String mName;
	private String nickname;
	private Character gender;
	private String birth;
	private Date enrollDate;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String mLevel;
	private String proSaveName;
	private String enabled;
	private MultipartFile files;
	
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
	
	public Date getEnrollDate() {
		return enrollDate;
	}
	
	public void setEnrollDate(Date enrollDate) {
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
	
	public String getProSaveName() {
		return proSaveName;
	}
	
	public void setProSaveName(String proSaveName) {
		this.proSaveName = proSaveName;
	}

	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberID=" + memberID + ", memberPW=" + memberPW + ", mName=" + mName + ", nickname="
				+ nickname + ", gender=" + gender + ", birth=" + birth + ", enrollDate=" + enrollDate + ", phone1="
				+ phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", email=" + email + ", mLevel=" + mLevel
				+ ", proSaveName=" + proSaveName + ", enabled=" + enabled + ", files=" + files + "]";
	}

}