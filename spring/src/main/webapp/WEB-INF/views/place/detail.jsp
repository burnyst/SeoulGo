<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:path>
<c:set var="defaultImage" value="${imagePath}/place/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/place" />
</t:path>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>장소 상세정보</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="${resourcePath}/js/place/detail.js"></script>
</head>
<body>
<input id="pagePath" type="hidden" value="${pagePath}" />
<input id="placeNo" type="hidden" value="${item.placeNo}" />
<h4 class="title">${item.placeName}</h4>
<div class="row">
	<c:set var="imageCnt" value="${fn:length(item.imageNames)}" />
	<div id="images" class="carousel slide" data-ride="carousel">
		<ul class="carousel-indicators">
			<li data-target="#images" data-slide-to="0" class="active"></li>
			<c:if test="${imageCnt > 0}">
				<c:forEach var="i" begin="1" end="${imageCnt-1}">
					<li data-target="#images" data-slide-to="${i}"></li>
				</c:forEach>
			</c:if>
		</ul>
		<div class="carousel-inner">
			<c:choose>
				<c:when test="${imageCnt > 0}">
					<div class="carousel-item active">
						<img src="${imagePath}/place/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="place 1">
					</div>
					<c:forEach var="i" begin="1" end="${imageCnt-1}">
						<div class="carousel-item">
							<img src="${imagePath}/place/${item.imageNames[i]}" onerror="this.src='${defaultImage}'" alt="place image ${i+1}">
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="carousel-item active">
						<img src="${defaultImage}" alt="place image">
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<a class="carousel-control-prev" href="#images" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a class="carousel-control-next" href="#images" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
	</div>
	<div class="mt-3">
		<span>
			리뷰: ${item.reviewCount}<br />
			평점: <t:star score="${item.placeRate20X}"></t:star><br />
			주소: ${item.addr1}<br />
			${item.addr2}
		</span>
		<div class="mt-3 text-center">
			<button type="button" class="btn btn-primary mb-3">일정 계획</button>
			<button type="button" class="btn btn-success mb-3 ml-3">리뷰 작성</button>
			<button id="backBtn" type="button" class="btn btn-secondary mb-3 ml-3">이전</button>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button id="updateBtn" type="button" class="btn btn-warning mb-3 ml-3">수정</button>
				<button id="deleteBtn" type="button" class="btn btn-danger mb-3 ml-3">삭제</button>
			</sec:authorize>
		</div>
	</div>
</div>
<h4 class="my-3">위치</h4>
<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>