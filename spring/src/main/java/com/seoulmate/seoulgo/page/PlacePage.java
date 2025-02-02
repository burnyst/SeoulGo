package com.seoulmate.seoulgo.page;

import java.util.List;

import com.seoulmate.seoulgo.dto.PlaceDto;

public class PlacePage {
	private int pageNo, pageNum, pageRowNum;
	private String keyword, type, order;
	private int startRow, endRow, totalRow;
	private int startPage, endPage, totalPage;
	private List<PlaceDto> content;
	public PlacePage() {
		pageNo = 1;
		pageNum = 5;
		pageRowNum = 12;
	}
	public PlacePage(int pageNo, int pageNum, int pageRowNum, String keyword, String type, String order, int totalRow) {
		this.pageNo = pageNo;
		this.pageNum = pageNum;
		this.pageRowNum = pageRowNum;
		this.keyword = keyword;
		this.type = type;
		this.order = order;
		this.totalRow = totalRow;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getPageRowNum() {
		return pageRowNum;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getType() {
		return type;
	}
	public String getOrder() {
		return order;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public List<PlaceDto> getContent() {
		return content;
	}
	public void setPageNo(int pageNo) {
		if (pageNo > 0) {
			this.pageNo = pageNo;
		}
	}
	public void setPageNum(int pageNum) {
		if (pageNum > 0) {
			this.pageNum = pageNum;
		}
	}
	public void setPageRowNum(int pageRowNum) {
		if (pageRowNum > 0) {
			this.pageRowNum = pageRowNum;
		}
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void setTotalRow(int totalRow) {
		if (totalRow < 1) {
			this.totalRow = 0;
			totalPage = 0;
			startPage = 0;
			endPage = 0;
			startRow = 0;
			endRow = 0;
			return;
		}
		this.totalRow = totalRow;
		totalPage = ((totalRow - 1) / pageRowNum) + 1;
		startPage = ((pageNo - 1) / pageNum * pageNum) + 1;
		endPage = startPage + pageNum - 1;
		if (totalPage < endPage) {
			endPage = totalPage;
		}
		
		startRow = (pageNo - 1) * pageRowNum + 1;
		endRow = startRow + pageRowNum - 1;
		if (totalRow < endRow) {
			endRow = totalRow;
		}
	}
	public void setContent(List<PlaceDto> content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PlacePage [pageNo=" + pageNo + ", pageNum=" + pageNum + ", pageRowNum=" + pageRowNum + ", keyword="
				+ keyword + ", type=" + type + ", order=" + order + ", startRow=" + startRow + ", endRow="
				+ endRow + ", totalRow=" + totalRow + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", totalPage=" + totalPage + ", content=" + content + "]";
	}
}
