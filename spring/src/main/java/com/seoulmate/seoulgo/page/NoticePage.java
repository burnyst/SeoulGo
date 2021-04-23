package com.seoulmate.seoulgo.page;

public class NoticePage {

	// 현재 페이지를 DB에서 가져 올때 필요한 정보
	private int pageNo; // 현재 페이지
	private int perPageNum; // 페이지당 보여지는 글의 갯수
	private int startRow, endRow; // 현재 페이지의 시작 줄번호, 끝번호

	// JSP 화면에 하단부분 페이지 표시를 할때 필요한 정보.
	private int perGroupPageNum; // 기본값 10을 생성자에서 셋팅
	private int startPage;
	private int endPage;
	private int totalPage;
	private int totalRow; // DB에서 구한다.

	// 검색에 필요한 변수 선언
	private String searchType;
	private String keyword;
	
	// Spring에서는 파라메터 값을 전달 받을때 기본 생성자와 setter()를 이용해서 데이터를 전달 받는다.
	public NoticePage() {
		// 처음 리스트로 들어올 때는 데이터가 안 넘어 오므로 초기값을 1페이지에
		// 한페이당 10개의 글을 보이도록 셋팅한다.
		pageNo = 1;
		perPageNum = 10;
		// JSP 하단 부분에 몇개의 페이지를 표시할지 정한다.
		perGroupPageNum = 10;
	}

	public NoticePage(int pageNo, int perPageNum, int perGroupPageNum, String searchType, String keyword, int totalRow) {
		this.pageNo = pageNo;
		this.perPageNum = perPageNum;
		this.perGroupPageNum = perGroupPageNum;
		this.searchType = searchType;
		this.keyword = keyword;
		this.totalRow = totalRow;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	// setStartRow()와 setEndRow()를 계산에 의해서 결정이 되므로 받는 않아서 만들지 않는다.
	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getPerGroupPageNum() {
		return perGroupPageNum;
	}

	public void setPerGroupPageNum(int perGroupPageNum) {
		this.perGroupPageNum = perGroupPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRow() {
		return totalRow;
	}

	// 페이지에 따른 데이터의 startRow와 endRow, totalPage, startPage, endPage
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;

		// 시작 줄번호와 끝 줄번호 계산
		// 현재 페이지의 이전 페이지 까지 데이터를 skip시키고 그 다음 번호 시작 번호로 한다.
		startRow = (pageNo - 1) * perPageNum + 1;
		endRow = startRow + perPageNum - 1;
		if (endRow > totalRow) {
			endRow = totalRow;
		}

		// 리스트 화면 하단 부분에 나타내는 페이지를 처리하기 위한 데이터들을 계산
		// 전체 페이지를 계산할 수 있다.
		totalPage = ((totalRow - 1) / perPageNum) + 1;
		// startPage, endPage
		startPage = ((pageNo - 1) / perGroupPageNum * perGroupPageNum) + 1;
		endPage = startPage + perGroupPageNum - 1;
		// endPage 가 총 페이지 수를 넘을 수 없다.
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "MemberSearchPage [pageNo=" + pageNo + ", perPageNum=" + perPageNum + ", startRow=" + startRow + ", endRow="
				+ endRow + ", perGroupPageNum=" + perGroupPageNum + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", totalPage=" + totalPage + ", totalRow=" + totalRow + ", searchType=" + searchType + ", keyword="
				+ keyword + "]";
	}
}