package com.seoulmate.seoulgo.page;

public class PlanPage {
	
	// 현재 페이지를 DB에서 가져 올때 필요한 정보
	private int pageNo; // 현재 페이지
	private int perPageNum; // 페이지당 보여지는 글의 갯수
	private int startRow, endRow; // 현재 페이지의 시작 줄번호, 끝번호
	private String planTitle, planCate, addr1, addr2, memberid;
	private int planNo, addrNo;

	//JSP 화면에 하단부분 페이지 표시를 할때 필요한 정보.
	private int perGroupPageNum; // 기본값 10을 생성자에서 셋팅
	private int startPage;
	private int endPage;
	private int totalPage;
	private int totalRow; // DB에서 구한다.

	// 검색에 필요한 변수 선언
	private String searchType;
	private String keyword;

	// 공지 분류 (기간) 를 적용시키는 변수 - period
	// pre : 현재공지, old : 지난공지 , res : 예약공지, all : 전체공지
	private String period;

	// 메시지 처리를 위한 변수
	// 받는 사람의 id
	private String accepter;

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getPlanCate() {
		return planCate;
	}

	public void setPlanCate(String planCate) {
		this.planCate = planCate;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public PlanPage() {
		// 처음 리스트로 들어올 때는 데이터가 안 넘어 오므로 초기값을 1페이지에
		// 한페이당 10개의 글을 보이도록 셋팅한다.
		pageNo = 1;
		perPageNum = 10;
		// JSP 하단 부분에 몇개의 페이지를 표시할지 정한다.
		perGroupPageNum = 3;

		// 기본적으로 찾는 공지 분류 - 현재 공지 :pre
		this.period = "pre";

	}

	public PlanPage(int pageNo, int perPageNum, int perGroupPageNum) {
		this.pageNo = pageNo;
		this.perPageNum = perPageNum;
		this.perGroupPageNum = perGroupPageNum;

		// JSP 하단 부분에 몇개의 페이지를 표시할지 정한다.
		perGroupPageNum = 3;
		System.out.println("PageObject(페이지,페이지 당 글수)");
	}

	// Spring에서는 파라메터 값을 전달 받을때 기본 생성자와 setter()를 이용해서 데이터를 전달 받는다.

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

	public int getEndPage() {
		return endPage;
	}

	public int getTotalPage() {
		return totalPage;
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

	public String getsearchType() {
		return searchType;
	}

	public void setsearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getkeyword() {
		return keyword;
	}

	public void setkeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	@Override
	public String toString() {
		return "PlanPage [perPageNum=" + perPageNum + ", startRow=" + startRow + ", endRow=" + endRow + ", planTitle="
				+ planTitle + ", planCate=" + planCate + ", addr1=" + addr1 + ", addr2=" + addr2 + ", memberid="
				+ memberid + ", planNo=" + planNo + ", perGroupPageNum=" + perGroupPageNum + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", totalPage=" + totalPage + ", totalRow=" + totalRow + ", pageNo=" + pageNo
				+ ", searchType=" + searchType + ", keyword=" + keyword + ", period=" + period + ", accepter="
				+ accepter + "]";
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public int getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}
}