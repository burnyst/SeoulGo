<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="date" value="<%= new java.util.Date() %>"/>
<t:path>
<c:set var="defaultImage" value="${imagePath}/place/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/plan" />
</t:path>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${resourcePath}/css/plan/insert.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script src="${resourcePath}/js/plan/planWrite.js"></script>
<style>
</style><!--.show는 block형태로 a링크를 보여줄수 있도록 한다.  -->
<meta charset="UTF-8">
<title>일정짜기</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<input type="hidden" value="${basePath}" id="basePath">
<div class="p-3">
	<h4>일정 짜기</h4>
</div>
<div class="row">
<div class="schedule-form col-lg">
	<form class="form" action="${basePath}/plan/planWrited" method="post" id="planWrite" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th>여행날짜</th>
				<td>
					<input type="date" id="plandate" name="plandate" class="form-control date" required="required" value="<fmt:formatDate value="${date}" pattern='yyyy-MM-dd'/>"/>
				</td>
			</tr>
			<tr>
				<th>여행제목</th>
				<td> 
					<input type="text" id="planTitle" name="planTitle" class="form-control" required="required" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>여행유형</th>
				<td>
					<select name="plancate" id="plancate" class="form-control" required="required">
						<option value="">여행 유형</option>
						<option value="가족">가족과 함께</option>
						<option value="커플">커플 여행</option>
						<option value="단독">나만의 여행</option>
						<option value="비즈니스">비즈니스 여행</option>
						<option value="친구">우정 여행</option>
					</select>  
					<input type="hidden" name="planCate" id="planCate">
				</td>
			</tr>
			<tr>
				<th>여행장소</th>
				<td class="row">
					<div id="placeName" class="d-flex flex-wrap"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="map"></div>
				</td>
			</tr>
		</table>
		<div style="text-align: center;">
			<input class="btn btn-success" type="button" id="wBtn" value="일정짜기"> 
			<input class="btn btn-info m-3" type="reset" value="다시쓰기">
			<a class ="btn btn-warning" id="backBtn">뒤로가기</a>
		</div>
	</form>
</div>
<div class="place-list-container col-lg border-top p-3">
	<form id="searchForm" class="form">
		<input name="pageNo" type="hidden" value="" />
		<input name="pageNum" type="hidden" value="" />
		<input name="pageRowNum" type="hidden" value="" />
		<input name="type" type="hidden" value="" />
		<div class="form-group">
			<div class="input-group">
				<input name="keyword" type="text" class="form-control" placeholder="장소명 또는 주소" value="${page.keyword}">
				<div class="input-group-append">
					<button id="searchBtn" class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
				</div>
			</div>
		</div>
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item">
				<a class="nav-link" href="#">전체</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">즐길거리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">음식점</a>
			</li>
		</ul>
		<div class="d-flex pt-3">
			<h4 class="flex-grow-1">검색결과</h4>
			<div class="form-group">
				<select id="order" name="order" class="form-control" >
					<option>리뷰순</option>
					<option>평점순</option>
				</select>
			</div>
		</div>
		<div class="tab-content p-3">
			<div id="searchResult" class="tab-pane active">
			</div>
		</div>
	</form>
	<ul id="pageNav" class="pagination justify-content-center"></ul>
</div>
</div>
</sec:authorize>
</body>
</html>