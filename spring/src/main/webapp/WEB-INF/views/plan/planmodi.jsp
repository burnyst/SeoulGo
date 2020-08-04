<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="resourcePath" value="${basePath}/resources" />

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/planmodi.js"></script>
<script>

</script>
<style>
	#planlist{margin-top:20px;}
</style>
<meta charset="UTF-8">
<title>일정수정하기</title>
</head>
<body>
<c:forEach items="${Pdto}" var="list" varStatus="status">
	<input type="hidden" id="placename" value="${list.placenamelist}">
</c:forEach>
<c:forEach items="${placedto}" var="list" varStatus="status">
	<input type="hidden" id="addr1" value="${list.addr1 }">
	<input type="hidden" id="addr2" value="${list.addr2 }">
</c:forEach>
<div id="map" style="width:500px;height:300px;float:left; position:relative;'">
</div>
<form method="post" id="frm" action="/plan/planmodifin">
  <c:forEach var="list"  items="${Pdto}"  varStatus="status" >
	<div class="planlist">
		<div style="float:left; margin-right:10px">여행 날짜</div>
		 <input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
		<div>
		<input type="date" id="plandate" name="plandate" ></div>
		<input type="hidden" id="nowdate" name="nowdate" value="${list.planDate }">
		
		<div style="float:left; margin-right:10px">여행장소</div>
		<div id="place"><input type="text" id="planplace" name="planplace" value="${placedto[0].addr1}${placedto[0].addr2}" readonly="readonly"></div>
		
		<div style="float:left; margin-right:10px">일정제목</div>
		
		<div><input type="text" id="planTitle" name="planTitle" value="${list.planTitle}"></div>
		
		<div style="float:left; margin-right:10px">여행유형</div>
		<div>
			<select name="plancate" id="plancate">
					<option value="가족" <c:if test="${list.planCate eq '가족'}">selected</c:if>>가족과 함께</option>
					<option value="커플" <c:if test="${list.planCate eq '커플'}">selected</c:if>>커플 여행</option>
					<option value="단독" <c:if test="${list.planCate eq '단독'}">selected</c:if>>나만의 여행</option>
					<option value="비즈니스" <c:if test="${list.planCate eq '비즈니스'}">selected</c:if>>비즈니스 여행</option>
					<option value="친구" <c:if test="${list.planCate eq '친구'}">selected</c:if>>우정 여행</option>
			</select>
		</div>
	</div>
  </c:forEach>

	<div style="height:250px;"><img src="<c:if test=""> </c:if>"> </div>
	<div style="text-align: center;">
		<input type="submit" class="btn btn-primary"  value="수정하기"/>
		<button id="delmodi"type="submit" name="delmodi" class="btn btn-danger">삭제하기</button> 
		<button id="nomodi" type="button" class="btn btn-info">수정취소하기</button>
	</div>
</form>
</body>
</html>