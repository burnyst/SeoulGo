package com.seoulmate.seoulgo.dto;

import java.util.Date;

public class NoticeReplyDTO {

	private int nrNo;
	private int nNo;
	private String nrWriter;
	private String nrContent;
	private Date nrDate;
	private int nrParent;
	private int nrOrder;

	public int getNrNo() {
		return nrNo;
	}

	public void setNrNo(int nrNo) {
		this.nrNo = nrNo;
	}

	public int getnNo() {
		return nNo;
	}

	public void setnNo(int nNo) {
		this.nNo = nNo;
	}

	public String getNrWriter() {
		return nrWriter;
	}

	public void setNrWriter(String nrWriter) {
		this.nrWriter = nrWriter;
	}

	public String getNrContent() {
		return nrContent;
	}

	public void setNrContent(String nrContent) {
		this.nrContent = nrContent;
	}

	public Date getNrDate() {
		return nrDate;
	}

	public void setNrDate(Date nrDate) {
		this.nrDate = nrDate;
	}

	public int getNrParent() {
		return nrParent;
	}

	public void setNrParent(int nrParent) {
		this.nrParent = nrParent;
	}

	public int getNrOrder() {
		return nrOrder;
	}

	public void setNrOrder(int nrOrder) {
		this.nrOrder = nrOrder;
	}

	@Override
	public String toString() {
		return "NoticeReplyDTO [nrNo=" + nrNo + ", nNo=" + nNo + ", nrWriter=" + nrWriter + ", nrContent=" + nrContent
				+ ", nrDate=" + nrDate + ", nrParent=" + nrParent + ", nrOrder=" + nrOrder + "]";
	}

}