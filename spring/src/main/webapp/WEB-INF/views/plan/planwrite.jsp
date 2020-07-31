<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="date" value="<%= new java.util.Date() %>"/>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="./jquery.cookie.js"></script> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="${basePath}/resources/js/plan/planWrite.js"></script>
<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function dropdownfunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}
$(function(){
	 $("#backBtn").click(function(){
		 alert("함수 작동");
		 $(location).attr("href","../plan/plan");
	 })
	 
	 var idval =$('#planCate');
	 $('#plancate').change(function(){
		var element =$(this).find('option:selected');
		var myTag = element.attr('value');
		idval.val(myTag);
	 });
	 
	 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	 mapOption = {
	     center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	     level: 3 // 지도의 확대 레벨
	 };  

	 //지도를 생성합니다    
	 var map = new kakao.maps.Map(mapContainer, mapOption); 

	 //주소-좌표 변환 객체를 생성합니다
	 var geocoder = new kakao.maps.services.Geocoder();

	 //주소로 좌표를 검색합니다
	 geocoder.addressSearch('서울 중구 세종대로 110', function(result, status) {

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
	         content: '<div style="width:150px;text-align:center;padding:6px 0;">광화문</div>'
	     });
	     infowindow.open(map, marker);

	     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	     map.setCenter(coords);
	 } 
	 });   
	 
})
// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>
<style>
.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}
.dropdown {
  position: relative;
  display: inline-block;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.dropdown a:hover {background-color: #ddd;}
.show {display: block;} 
.container2:after {
	content: "";
	display:block;
	clear: both;
} 
form {
	width: 33%;
	float:left; 
}
.scroll {
	width: 20px;
	height: 450px;
	overflow-x: hidden;
	overflow-y: auto; 
}
.btn-outline-info {
	margin-right: 3px;
}
#map {
	margin:0 5px;
}
</style><!--.show는 block형태로 a링크를 보여줄수 있도록 한다.  -->
<meta charset="UTF-8">
<title>일정짜기</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<!-- 이 페이지는 일정을 짜는 페이지이다. 
 일정짜는데에는 PlanController에 페이지를 보여줄수 있는 컨트롤러를 집어넣을 예정이다.
   -->
<input type="hidden" value="${basePath}" id="basePath">
<div class="container2">
	<div class="form-group scroll" id="placeList" style="float:left; width:25%;">
		<div class="form-group row justify-content-center">
			<div class="w100 form-group" style="padding-right:10px; margin-bottom: 5px;">
				<select class="form-control form-control-sm" name="searchType" id="searchType">
					<option value="">검색유형</option>
					<option value="placeName">장소명</option>
					<option value="addr">주소</option>
				</select>
			</div>&nbsp;&nbsp;
			<div class="w400 form-group" style="padding-right:5px;">
				<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" placeholder="검색어를 입력해주세요">
			</div>	
			<div>
				<button class="btn btn-sm btn-outline-primary" name="btnSearch" id="btnSearch">검색</button>
			</div>
		</div>
		<c:forEach var="list" items="${placeview}">
			<div id="div">
				<span><a href="${basePath}/place/detail?placeNo=${list.placeNo}">${list.placeName}</a></span><br>
				<span>${list.addr1} ${list.addr2}</span><br>
				<button class="btn btn-sm btn-outline-danger float-right">
					<i class="fas fa-trash"></i>
				</button>&nbsp;
				<button class="btn btn-sm btn-outline-info float-right">
					<i class="fas fa-calendar-check"></i>
				</button>
				<input type="hidden" id="placeName" name="placeName" value="${list.placeName}">
				<input type="hidden" id="placeNo" name="placeNo" value="${list.placeNo}">
				<input type="hidden" id="addr1" name="addr1" value="${list.addr1}">
				<input type="hidden" id="addr2" name="addr2" value="${list.addr2}">
			</div>
			<hr>
		</c:forEach>
		<t:pageNav
		   endPage="${page.endPage}"
		   totalPage="${page.totalPage}"
		   startPage="${page.startPage}"
		   uri="${pageUri}"
		   params="&keyword=${page.keyword}&searchType=${page.searchType}"
		   pageNo="${page.pageNo}">
		</t:pageNav>
	</div>
	<div id="map" style="width:40%; height:450px; float:left;"></div>
	<form action="${basePath}/plan/planwrited" method="post" id="planWrite" enctype="multipart/form-data">
		<div id="planlist">
			<div class="form-group">
				<div style="float:left; margin-right:10px">여행날짜</div> 
				<div><input type="date" id="plandate" name="plandate" class="date" value="<fmt:formatDate value="${date}" pattern='yyyy-MM-dd'/>"></div>
			</div>
			<div class="form-group">
				<div style="float:left; margin-right:10px;">여행 제목</div>
				<div><input type="text" id="planTitle" name="planTitle" required="required"></div>
			</div>
			<div class="form-group">
				<div style="float:left; margin-right:10px">여행유형</div>
				<div>
					<select name="plancate" id="plancate" required="required">
						<option value="">여행 유형</option>
						<option value="가족">가족과 함께</option>
						<option value="커플">커플 여행</option>
						<option value="단독">나만의 여행</option>
						<option value="비즈니스">비즈니스 여행</option>
						<option value="친구">우정 여행</option>
					</select>  
					<input type="hidden" name="planCate" id="planCate">
				</div>
			</div>
			<div class="form-group">
				<div style="float:left; margin-right:10px">여행장소</div>
				<div class="form-group">
					<input type="text" id="planplace1" name="planplace1" required="required">
				</div>
				<div class="form-group" id="copy"></div>
			</div>
			<div class="form-group">
				<div style="text-align: center;">
					<i class="far fa-plus-square fa-2x" style="margin-right: 5px;"></i>
					<i class="far fa-minus-square fa-2x"></i>
				</div>
			</div>
		</div>
		<!-- <div style="height:250px">
		</div> -->
		<div style="text-align: center;">
			<input class="btn btn-success" type="submit" id="wBtn" value="일정짜기"> 
			<input class="btn btn-info" type="reset" value="다시쓰기">
			<a class ="btn btn-warning" id="backBtn">뒤로가기</a>
		</div>
	</form>
</div><br>
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<button id="bla" type="button" class="btn btn-info">로그인이 필요한 페이지입니다.</button>
</sec:authorize>
</body>
</html>