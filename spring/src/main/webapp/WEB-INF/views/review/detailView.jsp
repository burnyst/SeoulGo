<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:path>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
</t:path>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- Responsive Web Design - The Viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰 더보기</title>

<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/likecnt.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/dislikecnt.js"></script>
<script type="text/javascript" src="${resourcePath}/js/review/deleteReview.js"></script>
<style type="text/css">
	#pro {
		width: 100px;
		height: 100px;
	}
	.rimg {
		width: 100px;
		height: 100px;
	}
</style>
</head>
<body>
<input id="basePath" type="hidden" value="${basePath}" />
	<!-- 작성자 정보 표시 div -->
	<div class="row">
		<div id="profilepic">
			<c:if test="${info.proSaveName ne null}">
				<img id="pro" src="${imagePath}/member/${info.proSaveName}" class="rounded-circle" >
			</c:if>
			<c:if test="${info.proSaveName eq null}">
				<img id="pro" src="${imagePath}/member/default.png" class="rounded-circle" >
			</c:if>
		</div>
		<div class="col-sm-8">
			<h3>${info.memberID}&nbsp;님이 작성한 리뷰</h3>
			<h6><fmt:formatDate value="${info.enrollDate}" pattern="yyyy년 MM월 dd일 "/>부터 활동 시작</h6>
		</div>
	</div>
	<hr>

<c:forEach items="${more}" var="more">
<div class="container">
<input type="hidden" value="${more.rNo}" name="rNo">
<input type="hidden" value="${more.placeNo}" name="dplaceNo">
<input type="hidden" value="${more.memberID}" name="dmemberID">
		<!-- 주소 -->
		<div>
			<h5>${more.placeName}</h5>
			<h5>${more.addr1}&nbsp;${more.addr2}</h5>
			<h5>${more.tel}</h5>
		</div>
		<!-- 버튼들, 나중에 c:if로 구분 -->
		<div class="float-right">
			<sec:authorize access="hasAnyRole('ANONYMOUS', 'ROLE_MEMBER')">
				<c:if test="${more.memberID ne mem.memberID}">
				<a href="../review/writeReview?placeNo=${more.placeNo}" style="text-decoration:none;">
					<input type="button" class="btn btn-outline-primary btn-sm" id="wBtn" value="리뷰 작성"/>
				</a>
				</c:if>
			</sec:authorize>
			<c:if test="${more.memberID eq mem.memberID}">
				<a href="../review/modifyReview?placeNo=${more.placeNo}&rNo=${more.rNo}" style="text-decoration:none;">
					<input type="button" class="btn btn-outline-primary btn-sm" id="mBtn" value="리뷰 수정"/>
				</a>
			</c:if>
			<c:if test="${more.memberID eq mem.memberID || fn:contains(mem.mLevel,'ROLE_ADMIN')}">
				<a href="../review/deleteReview?placeNo=${more.placeNo}&rNo=${more.rNo}" style="text-decoration:none;">
					<input type="button" class="btn btn-outline-secondary btn-sm" id="dBtn" value="리뷰 삭제"/>
				</a>
			</c:if>
		</div>
		<br>
		<!-- 제목 div -->
		<div>
			<h5><b>제목: ${more.rTitle}</b>
			<button type="button"  
			data-toggle="collapse" 
			data-target="#demo${more.rNo}"
			style="border:0; outline:0; background: none;"><i class="fas fa-search-plus"></i></button></h5>
		</div>
		<br>
		<div id="demo${more.rNo}" class="collapse">

		<!-- 방문일과 평점 div -->
		<div>
			<span>방문일 : 
				<c:if test="${more.rVisit eq null}">
					<fmt:formatDate value="${more.rDate}" pattern="yyyy년 MM월 dd일 "/>
				</c:if>
				<c:if test="${more.rVisit ne null}">
					<fmt:formatDate value="${more.rVisit}" pattern="yyyy년 MM월 dd일 "/>
				</c:if>
			</span>
			<br>
			<span>평점 : ${more.rRate}&nbsp;/&nbsp;5.0</span>
		</div>
		<br>
		
		<!-- 내용 div -->
		<div>
			<span>내용 : ${more.rContent}</span>
		</div>
		<br>
		<span>
		<c:forEach items="${img}" var="img">
			<c:if test="${more.rNo eq img.rNo}">
				<img class="rimg" src="<spring:url value='/resources/img/review/${img.iSaveName}'/>">
			</c:if>
		</c:forEach>
		</span>
		<br>
		
		<!-- 여행 유형 -->
		<div>
			<span>여행 유형 : ${more.rCate}</span>
		</div>
		<br>
		<sec:authorize access="hasRole('ANONYMOUS')">
			추천은<a href="../user/login" style="text-decoration:none;">
			로그인</a>해야 가능해요!
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">	
			<div>
				<!-- 좋아요 -->
				<button class="btn btn-outline-success btn-sm" id="good_update${more.rNo}">
				<i class="far fa-thumbs-up faPointer"></i>
				&nbsp; 좋아요 : <span class="good_count${more.rNo}"></span>
				</button>
				<!-- 싫어요 -->
				<button class="btn btn-outline-danger btn-sm" id="bad_update${more.rNo}">
				<i class="far fa-thumbs-down faPointer"></i>
				&nbsp; 싫어요 : <span class="bad_count${more.rNo}"></span>
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
		   params="&memberID=${memberID}">
		</t:pageNav>
	</div>
</body>
</html>