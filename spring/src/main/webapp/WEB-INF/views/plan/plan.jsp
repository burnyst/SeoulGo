<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<c:set var="resourcePath" value="${basePath}/resources" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/plan.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.w3-bar-block .w3-bar-item {padding:20px}
</style>
</head>
<body>  
<h3>마이 플랜</h3>
<div class="text-left">
	<input type="button" class="btn btn-outline-primary" id="ibtn" name="ibtn" value="새로운 일정" />
</div>
<hr>
<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:10px">

	<!-- First Photo Grid-->
	<div class="w3-row-padding w3-padding-16 w3-center" id="food">
		<c:forEach var="list" items="${plist}" varStatus="status" begin="0" end="3">
			<div class="w3-quarter" onclick="location.href='/plan/planview?&planNo=${list.planNo}'" style="cursor:hand" class="info">
					<img src="<spring:url value='/resources/img/review/11.jpg'/>" style="width:100%">
					<div><h5><b><fmt:formatDate value="${list.planDate}" pattern="yy년 MM월 dd일"/></b>&nbsp;<b>${list.planCate}</b></h5></div>
					<div><h5><b>${list.planTitle}</b></h5></div>
					<div>${list.placenamelist}</div>  
			</div>
		</c:forEach>
	</div>
	<!-- Second Photo Grid-->
	<div class="w3-row-padding w3-padding-16 w3-center">
		<c:forEach var="list" items="${plist}" varStatus="status" begin="4" end="7">
			<div class="w3-quarter" onclick="location.href='/plan/planview?&planNo=${list.planNo}'" style="cursor:hand" class="info">
					<img src="<spring:url value='/resources/img/review/11.jpg'/>" style="width:100%">
					<div><h5><b><fmt:formatDate value="${list.planDate}" pattern="yy년 MM월 dd일"/></b>&nbsp;<b>${list.planCate}</b></h5></div>
					<div><h5><b>${list.planTitle}</b></h5></div>
					<div>${list.placenamelist}</div>  
			</div>
		</c:forEach>
	</div>
</div>
<hr>
<%-- 페이징 처리 --%>
<t:pageNav
   endPage="${page.endPage}"
   pageNo="${page.pageNo}"
   totalPage="${page.totalPage}"
   startPage="${page.startPage}"
   uri="${pageUri}">
</t:pageNav>	
</body>
</html>