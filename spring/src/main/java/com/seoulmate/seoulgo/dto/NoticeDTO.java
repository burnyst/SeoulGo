package com.seoulmate.seoulgo.dto;

import java.util.Date;

public class NoticeDTO {

	private int nNo;
	private String nTitle;
	private String nWriter;
	private String nContent;
	private Date nDate;
	private boolean nExp;
	private int nCnt;

	public int getnNo() {
		return nNo;
	}

	public void setnNo(int nNo) {
		this.nNo = nNo;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nWriter) {
		this.nWriter = nWriter;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public boolean isnExp() {
		return nExp;
	}

	public void setnExp(boolean nExp) {
		this.nExp = nExp;
	}

	public int getnCnt() {
		return nCnt;
	}

	public void setnCnt(int nCnt) {
		this.nCnt = nCnt;
	}

	@Override
	public String toString() {
		return "NoticeDTO [nNo=" + nNo + ", nTitle=" + nTitle + ", nWriter=" + nWriter + ", nContent=" + nContent
				+ ", nDate=" + nDate + ", nExp=" + nExp + ", nCnt=" + nCnt + "]";
	}
}