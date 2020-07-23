<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- Responsive Web Design - The Viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/review/likecnt.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/review/dislikecnt.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/review/deleteReview.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review/gbcnt.css">
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
<style type="text/css">
	#pro {
		width: 50px;
		height: 50px;
	}
</style>
</head>
<body>
<div class="text">
	<%-- 반복문을 이용하여 줄 출력  --%>
	<c:forEach items="${Info}" var="dto">
	<input type="hidden" value="${dto.placeNo}" name="dplaceNo">	
		<h3>${dto.placeName}&nbsp;&nbsp;<a href="../review/writeReview?placeNo=${dto.placeNo}" class="btn btn-outline-primary btn-sm">리뷰 작성</a></h3>
		<h5>${dto.addr1}&nbsp;${dto.addr2}</h5>
		<h5>${dto.tel}</h5>
	</c:forEach>
</div>
<hr>
<c:forEach items="${review}" var="review">	
<div class="container">
	<input type="hidden" value="${review.rNo}" name="rNo">
	<input type="hidden" value="${review.memberID}" name="dmemberID">
	<!-- 작성자 정보 표시 div -->
	<div class="row">
			<div id="profilepic">
				<c:if test="${review.proSaveName ne null}">
					<img id="pro" src="${imagePath}/member/${review.proSaveName}">
				</c:if>
				<c:if test="${review.proSaveName eq null}">
					<img id="pro" src="${imagePath}/member/default.png">
				</c:if>
			</div>
			<div class="col-sm-8">
				<span>${review.memberID}</span>&nbsp;가 &nbsp;&nbsp;<span><fmt:formatDate value="${review.rDate}" pattern="yyyy년 MM월 dd일 "/>에</span>&nbsp;작성한 리뷰입니다.<br>
				<span>00 건의 다른 장소 리뷰&nbsp;&nbsp;
				<button style="border:0; outline:0; background: none; font-size:15px;"><b>더보기</b></button></span>
			</div>
		</div>
		
		<!-- 버튼들, 나중에 c:if로 구분 -->
		<div class="float-right">
		<c:forEach items="${Info}" var="dto">
			<a href="../review/writeReview?placeNo=${dto.placeNo}" style="text-decoration:none;">
				<input type="button" class="btn btn-outline-primary btn-sm" id="wBtn" value="리뷰 작성"/>
			</a>
			<c:if test="${review.memberID eq mem.memberID || fn:contains(mem.mLevel,'ROLE_ADMIN')}">
				<a href="../review/modifyReview?placeNo=${dto.placeNo}&rNo=${review.rNo}" style="text-decoration:none;">
					<input type="button" class="btn btn-outline-primary btn-sm" id="mBtn" value="리뷰 수정"/>
				</a>
				<a href="../review/deleteReview?placeNo=${dto.placeNo}&rNo=${review.rNo}" style="text-decoration:none;">
					<input type="button" class="btn btn-outline-secondary btn-sm" id="dBtn" value="리뷰 삭제"/>
				</a>
			</c:if>
			<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">
				<input type="button" class="btn btn-outline-info btn-sm" id="pBtn" value="사진 보기"/>
			</sec:authorize>
		</c:forEach>
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
		
		<!-- 여행 유형 -->
		<div>
			<span>여행 유형 : ${review.rCate}</span>
		</div>
		<br>
		<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">	
			<div>
				<!-- 좋아요 -->
				<button class="btn btn-outline-success btn-sm" id="good_update${review.rNo}">
				<i class="far fa-thumbs-up faPointer"></i>
				&nbsp; 좋아요 : <span class="good_count${review.rNo}"></span>
				</button>
				<!-- 싫어요 -->
				<button class="btn btn-outline-danger btn-sm" id="bad_update${review.rNo}">
				<i class="far fa-thumbs-down faPointer"></i>
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