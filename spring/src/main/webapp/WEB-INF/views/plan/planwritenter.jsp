<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	 $("#placesarch").click(function(){
		 alert("장소검색페이지로 넘어갑니다.");
		 $(location).attr("href","../plan/placesarch")
	 })
	 
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
</style><!--.show는 block형태로 a링크를 보여줄수 있도록 한다.  -->
<meta charset="UTF-8">
<title>일정짜기</title>
</head>
<body>

<!-- 이 페이지는 일정을 짜는 페이지이다. 
 일정짜는데에는 PlanController에 페이지를 보여줄수 있는 컨트롤러를 집어넣을 예정이다.
   -->
<c:forEach items="${place }" var="list">
	<script>
		
	</script>
</c:forEach>
<div id="map" style="width:500px;height:400px;float:left;'" ></div>
<script>
// url 에서 parameter 추출
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
var addr1 = getParameterByName("addr1");
var addr2 = getParameterByName("addr2");
var placename = getParameterByName("placename");
var addr = addr1+addr2

//	
$(function(){
	var idval =$('#planCate');
	 $('#plancate').change(function(){
		var element =$(this).find('option:selected');
		var myTag = element.attr('value');
		idval.val(myTag);
	 });
	 var addr = $("#addr1").val() + $("#addr2").val();
	 alert(addr);
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

geocoder.addressSearch( addr , function(result, status) {

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
} 
});    
</script>
	<div id="clickLatlng"></div>
<div style="float:left">
	<div class="dropdown">
  	<button onclick="dropdownfunction()" class="dropbtn">일정장소</button>
	  	<div id="myDropdown" class="dropdown-content">
		    <a href="/plan/planwrite/iljung1" onclick="javascript:document.myform.submit">장소1</a>
		    <a href="/plan/planwrite/iljung2">장소2</a>
		    <a href="/plan/planwrite/iljung3">장소3</a>
	  	</div>
	</div>
<c:forEach items="${place }" var="list" >
</c:forEach>
</div>
<form action="/plan/planwrited" method="post">
	<div id="planlist" style="margin-top: 100px;">
		<div style="float:left; margin-right:10px">여행날짜</div> 
		<div><input type="date" id="plandate"name="plandate" class="date"></div>
		<script>
	  		document.getElementById('plandate').value = new Date().toISOString().substring(0, 10);
		</script>
		<div style="float:left; margin-right:10px">여행장소</div>
		
		<div><c:forEach items="${choice}" var="list" >
				<input type="text" id="planplace" name="planplace" readonly="readonly" value="${list.place}">
				<input type="hidden" id="placeNo" name="placeNo" value="${list.placeNo}">
				<input type="hidden" id="addr1" name="addr1" value="${list.addr1}">
				<input type="hidden" id="addr2" name="addr2" value="${list.addr2}">
			</c:forEach>
			 <input type="button" id="placesarch" value="검색하기"></div>
		<div style="float:left; margin-right:10px border-top-width: 10px;">여행 제목</div>
		<div><input type="text" id="planTitle" name="planTitle"></div>
		<div style="float:left; margin-right:10px">여행유형</div>
		<div>
			<select name="plancate" id="plancate">
				<option value="" selected disabled hidden>==여행 유형을 선택하세요==</option>
				<option value="가족">가족과 함께</option>
				<option value="커플">커플 여행</option>
				<option value="단독">나만의 여행</option>
				<option value="비즈니스">비즈니스 여행</option>
				<option value="친구">우정 여행</option>
			</select>  
			<input type="hidden" name="planCate"id="planCate" >
		</div>
	</div>
	<div style="height:250px">
	</div>
	<div style="text-align: center;">
	<input class="btn btn-success" type="submit" value="일정짜기"> <input class="btn btn-info" type="reset" value="다시쓰기">
	<a class ="btn btn-warning" id="backBtn">뒤로가기</a>
</div>
</form>
</body>
</html>