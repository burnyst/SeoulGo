<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:path>
<c:set var="defaultImage" value="${imagePath}/place/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/place" />
</t:path>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>장소찾기</title>
<link rel="stylesheet" href="${resourcePath}/css/place/list.css">
<script type="text/javascript" src="${resourcePath}/js/place/list.js"></script>
</head>
<body>
<form id="searchForm">
	<input name="pageNo" type="hidden" value="${page.pageNo}" />
	<input name="pageNum" type="hidden" value="${page.pageNum}" />
	<input name="pageRowNum" type="hidden" value="${page.pageRowNum}" />
	<input name="type" type="hidden" value="${page.type}" />
	<div class="form-group">
		<div class="input-group">
			<input name="keyword" type="text" class="form-control" placeholder="Search" value="${page.keyword}">
			<div class="input-group-append">
				<button id="searchBtn" class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
			</div>
		</div>
	</div>
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item">
			<a class="nav-link${page.type != '즐길거리' && page.type != '음식점'?' active':''}" href="#">전체</a>
		</li>
		<li class="nav-item">
			<a class="nav-link${page.type == '즐길거리'?' active':''}" href="#">즐길거리</a>
		</li>
		<li class="nav-item">
			<a class="nav-link${page.type == '음식점'?' active':''}" href="#">음식점</a>
		</li>
	</ul>
	<div class="d-flex pt-3">
		<h4 class="flex-grow-1">검색결과</h4>
		<div class="form-group">
			<select id="order" name="order" class="form-control" >
				<option ${page.order != '평점순'?'selected':''}>리뷰순</option>
				<option ${page.order == '평점순'?'selected':''}>평점순</option>
			</select>
		</div>
	</div>
	<div class="tab-content p-3">
		<div id="all" class="tab-pane active">
			<div class="row">
				<t:placeList
					imagePath="${imagePath}/place"
					defaultImage="${defaultImage}"
					detailUri="${pagePath}/detail"
					list="${page.content}">
				</t:placeList>
			</div>
		</div>
	</div>
</form>
<t:pageNav
	endPage="${page.endPage}"
	pageNo="${page.pageNo}"
	totalPage="${page.totalPage}"
	startPage="${page.startPage}"
	uri="${pagePath}/list"
	params="&keyword=${page.keyword}&type=${page.type}&order=${page.order}">
</t:pageNav>
</body>
</html>