<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:path>
<c:set var="defaultImage" value="${imagePath}/place/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/place" />
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
</t:path>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>장소 상세정보</title>
<link rel="stylesheet" href="${resourcePath}/css/place/detail.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="${resourcePath}/js/place/detail.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/likecnt.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/dislikecnt.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/deleteReview.js"></script>
<script type="text/javascript">
$(function(){
	var morememberID = document.getElementsByName("morememberID");
	var list = new Array();	
	
	for (let i=0; i<morememberID.length; i++){
		list[i] = morememberID[i].value
		$("#moreView"+morememberID[i].value).click(function(){
			location.href = "../review/detailView?pageNo=1&memberID="+list[i];
		});
	}
});
</script>
<style type="text/css">
#pro{
	width:50px;
	height:50px;
}
</style>
</head>
<body>
<input id="basePath" type="hidden" value="${basePath}" />
<input id="pagePath" type="hidden" value="${pagePath}" />
<input id="placeNo" type="hidden" value="${item.placeNo}" name="dplaceNo"/>
<input id="placeName" type="hidden" value="${item.placeName}"/>
<input id="addr1" type="hidden" value="${item.addr1}"/>
<input id="addr2" type="hidden" value="${item.addr2}"/>
<h4 class="title">${item.placeName}</h4>
<div class="pb-3">
	<c:forEach var="i" items="${item.words}">
		<a href="${pagePath}/list?keyword=${i}">#${i}&nbsp;&nbsp;</a>
	</c:forEach>
</div>
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
						<div class="thumbnail-wrapper">
							<div class="thumbnail">
								<div class="thumbnail-centered">
									<div class="place-image-container">
										<img src="${imagePath}/place/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="place 1">
									</div>
								</div>
							</div>
						</div>
					</div>
					<c:forEach var="i" begin="1" end="${imageCnt-1}">
						<div class="carousel-item">
							<div class="place-image-container">
								<img src="${imagePath}/place/${item.imageNames[i]}" onerror="this.src='${defaultImage}'" alt="place image ${i+1}">
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="carousel-item active">
						<div class="place-image-container">
							<img src="${defaultImage}" alt="place image">
						</div>
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
			<button id="writeBtn" type="button" class="btn btn-success mb-3 ml-3">리뷰 작성</button>
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
<br>
<!-- 리뷰 -->
<h4 class="my-3">리뷰</h4>
<hr>
<c:forEach items="${review}" var="review">
<div>
<input type="hidden" value="${review.rNo}" name="rNo">
<input type="hidden" value="${review.placeNo}" name="dplaceNo">
<input type="hidden" value="${review.memberID}" name="morememberID">
	<!-- 작성자 정보 표시 div -->
	<div class="row">
		<div id="profilepic">
			<c:if test="${review.proSaveName ne null}">
				<img id="pro" src="${imagePath}/member/${review.proSaveName}" class="rounded-circle" >
			</c:if>
			<c:if test="${review.proSaveName eq null}">
				<img id="pro" src="${imagePath}/member/default.png" class="rounded-circle" >
			</c:if>
		</div>
		<div class="col-sm-8">
			<span>${review.memberID}</span>님이 &nbsp;<span><fmt:formatDate value="${review.rDate}" pattern="yyyy년 MM월 dd일 "/>에</span>&nbsp;작성한 리뷰입니다.<br>
			<span>작성자의 다른 리뷰<button id="moreView${review.memberID}" style="border:0; outline:0; background: none; font-size:15px;"><b>보러가기</b></button></span>
		</div>
	</div>
	
	<!-- 버튼들, 나중에 c:if로 구분 -->
	<div class="float-right">
		<c:if test="${review.memberID eq mem.memberID}">
			<a href="../review/modifyReview?placeNo=${item.placeNo}&rNo=${review.rNo}" style="text-decoration:none;">
				<input type="button" class="btn btn-outline-primary btn-sm" id="mBtn" value="리뷰 수정"/>
			</a>
		</c:if>
		<c:if test="${review.memberID eq mem.memberID || fn:contains(mem.mLevel,'ROLE_ADMIN')}">
			<a href="../review/deleteReview?placeNo=${item.placeNo}&rNo=${review.rNo}" style="text-decoration:none;">
				<input type="button" class="btn btn-outline-secondary btn-sm" id="dBtn" value="리뷰 삭제"/>
			</a>
		</c:if>
	</div>
	<br>
	
	<!-- 제목 div -->
	<div>
		<h5>
		<b>제목: ${review.rTitle}</b>
			<button type="button"  
			data-toggle="collapse" 
			data-target="#demo${review.rNo}"
			style="border:0; outline:0; background: none;"><i class="fas fa-search-plus"></i></button>
		</h5>
	</div>
	<br>
	<div id="demo${review.rNo}" class="collapse">
		<!-- 방문일과 평점 div -->
		<div>
		
			<span>방문일 : 
				<c:if test="${review.rVisit eq null}">
					<fmt:formatDate value="${review.rDate}" pattern="yyyy년 MM월 dd일 "/>
				</c:if>
				<c:if test="${review.rVisit ne null}">
					<fmt:formatDate value="${review.rVisit}" pattern="yyyy년 MM월 dd일 "/>
				</c:if>
			</span>
			<br>
			<span>평점 : ${review.rRate}</span>
		</div>
		<br>
		
		<!-- 내용 div -->
		<div>
			<span>내용 : ${review.rContent}</span>
		</div>
		<br>
		<span>
		<c:forEach items="${img }" var="img">
			<c:if test="${review.memberID eq img.memberID}">
				<img class="img" src="<spring:url value='/resources/img/review/${img.iSaveName}'/>">
			</c:if>
		</c:forEach>
		</span>
		<!-- 여행 유형 -->
		<div>
			<span>여행 유형 : ${review.rCate}</span>
		</div>
		<br>
		<sec:authorize access="hasRole('ANONYMOUS')">
			추천은<a href="../user/login" style="text-decoration:none;">
			로그인</a>해야 가능해요!
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">
			<div>
				<!-- 좋아요 -->
				<button class="btn btn-outline-success btn-sm" id="good_update${review.rNo}">
				<i class="far fa-thumbs-up"></i>
				&nbsp; 좋아요 : <span class="good_count${review.rNo}"></span>
				</button>
				<!-- 싫어요 -->
				<button class="btn btn-outline-danger btn-sm" id="bad_update${review.rNo}">
				<i class="far fa-thumbs-down"></i>
				&nbsp; 싫어요 : <span class="bad_count${review.rNo}"></span>
				</button>
			</div>
		</sec:authorize>	
	</div>
</div>
<hr>
</c:forEach>
	<!-- 페이징 -->
	<div class="container">
		<t:pageNav
		   endPage="${page.endPage}"
		   pageNo="${page.pageNo}"
		   totalPage="${page.totalPage}"
		   startPage="${page.startPage}"
		   uri="${pageUri}"
		   params="&placeNo=${page.placeNo}">
		</t:pageNav>
	</div>
</body>
</html>