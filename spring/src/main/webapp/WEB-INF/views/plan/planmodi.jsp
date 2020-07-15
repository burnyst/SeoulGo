<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176"></script>
<script>

$(function(){
	 $("#nomodi").click(function(){
		 alert("함수 작동");
		 $(location).attr("href","../plan/plan");
		
	 })
	 
	 $("#delmodi").click(function(){
		 alert("삭제페이지로 이동합니다.");
		 
		 $(location).attr("href","./plandelete");
		 
		 alert("./plandelete");
	 })
});


</script>
<style>
	#planlist{margin-top:20px;}
</style>
<meta charset="UTF-8">
<title>일정수정하기</title>
</head>
<body>
${Pdto}
<!-- 이 페이지는 일정을 짜는 페이지이다. 
 일정짜는데에는 PlanController에 페이지를 보여줄수 있는 컨트롤러를 집어넣을 예정이다.
   -->
<div id="map" style="width:500px;height:300px;float:left; position:relative;'">
</div>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3 
		};

		var map = new kakao.maps.Map(container, options);
	</script>
<form method="post" action="/plan/planmodifin">
  <c:forEach var="list"  items="${Pdto}"  varStatus="status" >
	<div class="planlist">
		<div style="float:left; margin-right:10px">여행 날짜</div>
		 <input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
		<div><input type="date" id="plandate" name="plandate" value="${list.planDate}"></div>
		
		<div style="float:left; margin-right:10px">여행장소</div>
		<div><input type="text" id="planplace" name="planplace" value="${list.planplace}"></div>
		
		<div style="float:left; margin-right:10px">일정제목</div>
		
		<div><input type="text" id="planTitle" name="planTitle" value="${list.planTitle}"></div>
		
		<div style="float:left; margin-right:10px">여행유형</div>
		<div>
			<select name="plancate" id="plancate">
					<option value="0" <c:if test="${list.planCate==0}">selected</c:if>>가족과 함께</option>
					<option value="1" <c:if test="${list.planCate==1}">selected</c:if>>커플 여행</option>
					<option value="2" <c:if test="${list.planCate==2}">selected</c:if>>나만의 여행</option>
					<option value="3" <c:if test="${list.planCate==3}">selected</c:if>>비즈니스 여행</option>
					<option value="4" <c:if test="${list.planCate==4}">selected</c:if>>우정 여행</option>
			</select>
		</div>
	</div>
  </c:forEach>
	<div style="height:250px;">여기는 사진이 출력되어 나올 위치입니다. </div>
	<div style="text-align: center;">
		<input type="submit" class="btn btn-primary"  value="수정하기"/>
		<button type="submit" id="delmodi" class="btn btn-danger">삭제하기</button> 
		<button id="nomodi" type="button" class="btn btn-info">수정취소하기</button>
	</div>
</form>
</body>
</html>