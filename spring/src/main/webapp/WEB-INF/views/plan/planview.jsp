<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="resourcePath" value="${basePath}/resources" />

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/planview.js"></script>
<style>
	#planlist{margin-top:20px;}
</style>
<meta charset="UTF-8">
<title>일정상세보기</title>
</head>
<body>

<c:forEach items="${Pdto}" var="list" varStatus="status">

	<input type="hidden" id="planTitle" value="${list.planTitle }">  
</c:forEach>

<!-- 카카오 맵의 div  -->
<div id="map" style="width:500px;height:330px;float:left; position:relative;'">
</div>

<form method="post" action="/plan/planmodifin">
  <c:forEach var="list"  items="${Pdto}"  varStatus="status" >
	<!--  <div class="planlist" style="height:250px; position:relative;">-->
		<div style="float:left; margin-right:10px">여행 날짜</div>
		 <input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
		<div><fmt:formatDate value="${list.planDate}" pattern="yyyy년MM월dd일"/></div>
		
		<div style="float:left; margin-right:10px">여행장소</div>
		
		<div>${placelist } </div>
		
		<div style="float:left; margin-right:10px">일정제목</div>
		
		<div>${list.planTitle}</div>
		
		<div style="float:left; margin-right:10px">여행유형</div>
		<div>
			<c:if test="${list.planCate eq '가족'}">가족과 함께</c:if>
			<c:if test="${list.planCate eq '커플'}">커플 여행</c:if>
			<c:if test="${list.planCate eq '단독'}">나만의 여행</c:if>
			<c:if test="${list.planCate eq '비즈니스'}">비즈니스 여행</c:if>
			<c:if test="${list.planCate eq '친구'}">우정 여행</c:if>
		</div>
	<!--  </div>-->
  </c:forEach>
  <div style="height:250px;"> </div>
<div > 
	<!-- <img alt="" src="/resources/img/plan/default.jpg" width="25%" height="240px">
	 -->
</div>
		<div style="text-align: center;">
		<sec:authorize access="isAuthenticated()">
			<button id="nomodi" type="button" class="btn btn-info">리스트 페이지로</button>
			<button id="back" type="button" class="btn btn-primary">일정공유 게시판으로</button>
		</sec:authorize>
		
	</div>
</form>
</body>
</html>