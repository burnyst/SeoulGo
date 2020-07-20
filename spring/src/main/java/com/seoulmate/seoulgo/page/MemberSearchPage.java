package com.seoulmate.seoulgo.page;

public class MemberSearchPage extends PageObject {

	private String searchType;
	private String keyword;

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
		return "MemberSearchPage [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}