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
	#planlist{margin-top:20px;}
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
<div id="map" style="width:500px;height:450px;float:right; position:relative; margin-top: 50px;'">
</div>
<div class="w-100 p-3" >
	<form method="get" action="/plan/planmodi" >
		<div class="place-list-container col-lg border-top p-3" >
					<input name="pageNo" type="hidden" value="${page.pageNo}" />
					<input name="pageNum" type="hidden" value="${page.perPageNum}" />
					
				<c:forEach var="list"  items="${Pdto}"  varStatus="status" >
				<!-- <div style="float:left; margin-right:10px">일정제목</div> -->
					<div><h4>${list.planTitle}</h4></div>
					<div style="float:left; margin-right:10px">여행 날짜</div>
					 <input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
					<div><fmt:formatDate value="${list.planDate}" pattern="yyyy년MM월dd일"/></div>
						<input type="hidden" id="plandate" value="${list.planDate }">
					<div style="float:left; margin-right:10px">여행유형</div>
					<div>
						<c:if test="${list.planCate eq '가족'}">가족과 함께</c:if>
						<c:if test="${list.planCate eq '커플'}">커플 여행</c:if>
						<c:if test="${list.planCate eq '단독'}">나만의 여행</c:if>
						<c:if test="${list.planCate eq '비즈니스'}">비즈니스 여행</c:if>
						<c:if test="${list.planCate eq '친구'}">우정 여행</c:if>
					</div>
			 	</c:forEach>
				
				<div class="d-flex pt-3" >
				<h5 class="flex-grow-1">여행장소</h5><br>
				</div>
				<div class="tab-content p-3" >
					<div id="all" class="tab-pane active" style="width=40%; height=250px">
						<c:forEach var="item" items="${placeview}" varStatus="status">
							<div class="media border">
								<div class="place-image-container mr-1 ">
									<img src="${imagePath}/place/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="place" width="100px" />
								</div>
								<div class="media-body">
								<a id="aName${item.placeNo}" href="../place/detail?placeNo=${item.placeNo}">${item.placeName}</a><br />
								리뷰 ${item.reviewCount} / 평점<t:star score="${item.placeRate20X}"></t:star><br />
								${item.addr1} ${item.addr2}
								</div>
								<input type="hidden" value="${item.placeNo}" name="pNo">
							</div>
						</c:forEach> 
					</div>
				</div>
			
			<div style="text-align: center;" >
				<sec:authorize access="isAuthenticated()">
					<button id="nomodi" type="button" class="btn btn-info">나의 일정  리스트 페이지로</button>
					<button id="back" type="button" class="btn btn-primary">일정공유 게시판으로</button>
					<c:forEach items="${Pdto }" var="list"> 
						<c:if test="${list.memberid eq memberid }">
							<button id="planmodi" name="planmodi" class="btn btn-success">일정 수정하기</button>
						</c:if>
					</c:forEach>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<button id="back" type="button" class="btn btn-primary">일정공유 게시판으로 돌아가기</button>
				</sec:authorize>
			</div>
		</div>
	</form>
 </div>
</body>
</html>