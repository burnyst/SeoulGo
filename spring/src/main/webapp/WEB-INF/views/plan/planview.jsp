<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script>

$(function(){
	 $("#nomodi").click(function(){
		 //alert("함수 작동");
		 $(location).attr("href","../plan/plan");
		
	 })
	 
	 $("#delmodi").click(function(){
		 alert("삭제페이지로 이동합니다.");
		 
		 $(location).attr("href","./plandelete");
		 
		 //alert("./plandelete");
	 })
	 $("#back").click(function(){
		 alert("일정공유 게시판으로 이동합니다.");
		 $(location).attr("href","../plan/planSboard");
	 })
	 
	 $("#bla").click(function(){
		 alert("이전페이지로 이동합니다.")
		 $(location).attr("href","./planSboard")
	 })
});
</script>
<style>
	#planlist{margin-top:20px;}
</style>
<meta charset="UTF-8">
<title>일정상세보기</title>
</head>
<body>
<c:forEach items="${Pdto}" var="list" varStatus="status">
	<input type="hidden" id="addr1"value="${list.addr1}">
	<input type="hidden" id="addr2"value="${list.addr2}">
	<input type="hidden" id="placename" value="${list.place }"> 
</c:forEach>
<!-- 이 페이지는 일정을 짜는 페이지이다. 
 일정짜는데에는 PlanController에 페이지를 보여줄수 있는 컨트롤러를 집어넣을 예정이다.
   -->
<div id="map" style="width:500px;height:300px;float:left; position:relative;'">
</div>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
var addr1 = $('#addr1').val();
var addr2 = $('#addr2').val();
var placename = $('#placename').val();
var addr = addr1 + addr2
//alert (addr);
// 주소로 좌표를 검색합니다
geocoder.addressSearch(addr, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+placename+'</div>'
        });
        
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true;
    } 
});    
</script>

<form method="post" action="/plan/planmodifin">
  <c:forEach var="list"  items="${Pdto}"  varStatus="status" >
	<div class="planlist">
		<div style="float:left; margin-right:10px">여행 날짜</div>
		 <input type="hidden" id="planNo" name="planNo" value="${list.planNo}"/>
		<div><fmt:formatDate value="${list.planDate}" pattern="yyyy년MM월dd일"/></div>
		
		<div style="float:left; margin-right:10px">여행장소</div>
		<div>${list.addr1}${list.addr2}</div>
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
	</div>
  </c:forEach>
	<div style="height:250px;">여기는 사진이 출력되어 나올 위치입니다. </div>
	<div style="text-align: center;">
		<sec:authorize access="isAuthenticated()">
			<button id="nomodi" type="button" class="btn btn-info">리스트 페이지로</button>
			<button id="back" type="button" class="btn btn-primary">일정공유 게시판으로</button>
		</sec:authorize>
		
	</div>
</form>
</body>
</html>