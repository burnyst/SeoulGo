<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
<c:set var="defaultImage" value="${imagePath}/plan/noimage.jpg" />
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/planview.js"></script>
<style>
</style>
<meta charset="UTF-8">
<title>일정상세보기</title>
</head>
<body>

<c:forEach items="${placeview }" var="list">
	<input type="hidden" id="addr1" value="${list.addr1}">
	<input type="hidden" id="addr2" value="${list.addr2}">
	<input type="hidden" id="placeName" value="${list.placeName}">
</c:forEach>
<c:forEach items="${Pdto}" var="list" varStatus="status">
	<input type="hidden" id="planTitle" value="${list.planTitle }">  
	
</c:forEach>

<!-- 카카오 맵의 div  -->
<h4>일정 상세보기</h4>
<hr>

<div class="row">
<div class="w-100 p-3 schedule-form col-lg" >
	<form method="get" action="${basePath}/plan/planmodi" >
	
		<div class="place-list-container col-lg p-3" >
			<!-- <div style="float:left; margin-right:10px">일정제목</div> -->
				<table class="table table-hover">
					<c:forEach var="list"  items="${Pdto}" varStatus="status" >
					<input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
						<thead class="thead-light">
							<tr class="text-center">
								<th colspan="2">${list.planTitle}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">여행 날짜</td>
								<td class="text-center">
									
									<input type="hidden" id="plandate" value="${list.planDate}">
									<fmt:formatDate value="${list.planDate}" pattern="yyyy년 MM월 dd일"/>
								</td>
							</tr>
							<tr>
								<td class="text-center">여행 유형</td>
								<td class="text-center">
									<c:if test="${list.planCate eq '가족'}">가족과 함께</c:if>
									<c:if test="${list.planCate eq '커플'}">커플 여행</c:if>
									<c:if test="${list.planCate eq '단독'}">나만의 여행</c:if>
									<c:if test="${list.planCate eq '비즈니스'}">비즈니스 여행</c:if>
									<c:if test="${list.planCate eq '친구'}">우정 여행</c:if>
								</td>
							</tr>
		 				</c:forEach>
						<tr>
							<td class="text-center">여행장소</td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<c:forEach var="item" items="${placeview}" varStatus="status">
					<div class="row" style="border: 1px solid #e6e6e6; margin: 0;">
						<div class="place-image-container mr-1">
							<img src="${imagePath}/place/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="place" width="100px" />
						</div>
						<div class="media-body">
							<a id="aName${item.placeNo}" href="../place/detail?placeNo=${item.placeNo}">${item.placeName}</a><br />
							리뷰 ${item.reviewCount} / 평점<t:star score="${item.placeRate20X}"></t:star><br />
							${item.addr1} ${item.addr2}
						</div>
					</div>
				</c:forEach>
			</div>
		<br>
	<div class="float-right">
		<sec:authorize access="isAuthenticated()">
			<button id="nomodi" type="button" class="btn btn-outline-primary">목록</button>
			<!-- <button id="back" type="button" class="btn btn-primary">일정공유 게시판으로</button> -->
			<td><input type="button" class="btn btn-outline-secondary" name="delno" id="delno"value="일정삭제"></td>
			<c:forEach items="${Pdto }" var="list"> 
				<c:if test="${list.memberid eq memberid }">
					<button id="planmodi" name="planmodi" class="btn btn-outline-info">수정</button>
				</c:if>
			</c:forEach>
		</sec:authorize>
		<%-- <sec:authorize access="isAnonymous()">
			<button id="back" type="button" class="btn btn-primary">일정공유 게시판으로 돌아가기</button>
		</sec:authorize> --%>
	</div>
	</form>
</div>
	<div id="map" style="width:500px; height:450px; float:right; position:relative; margin-top:50px;">
	</div>
</div>
</body>
</html>