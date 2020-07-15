<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176"></script>
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
		 //../주소부터는 컨트롤러를 만든 뒤 수정해야 한다..
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
<div id="map" style="width:500px;height:400px;float:left;'" ></div>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3 
		}; 

		var map = new kakao.maps.Map(container, options);
		var marker = new kakao.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker.setPosition(latlng);
		    
		    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다';
		    
		    var resultDiv = document.getElementById('clickLatlng'); 
		    resultDiv.innerHTML = message;
		    
		});
$(function(){
	var idval =$('#planCate');
	 $('#plancate').change(function(){
		var element =$(this).find('option:selected');
		var myTag = element.attr('value');
		idval.val(myTag);
	 });
});
</script>
	<div id="clickLatlng"></div>
<div style="float:left">
	<div class="dropdown">
  	<button onclick="dropdownfunction()" class="dropbtn">일정장소</button>
	  	<div id="myDropdown" class="dropdown-content">
		    <a href="#iljung1">장소1</a>
		    <a href="#iljung2">장소2</a>
		    <a href="#iljung3">장소3</a>
	  	</div>
	</div>
</div>
<form action="/plan/planwrited" method="post">
	<div id="planlist" style="margin-top: 100px;">
		<div style="float:left; margin-right:10px">여행날짜</div> 
		<div><input type="date" id="plandate"name="plandate" class="date"></div>
		<div style="float:left; margin-right:10px">여행장소</div>
		<div><input type="text" id="planplace" name="planplace">경북궁</div>
		<div style="float:left; margin-right:10px border-top-width: 10px; ">여행 제목</div>
		<div><input type="text" id="planTitle" name="planTitle"></div>
		<div style="float:left; margin-right:10px">여행유형</div>
		<div>
			<select name="plancate" id="plancate">
				<option value="" selected disabled hidden>==여행 유형을 선택하세요==</option>
				<option value="0">가족과 함께</option>
				<option value="1">커플 여행</option>
				<option value="2">나만의 여행</option>
				<option value="3">비즈니스 여행</option>
				<option value="4">우정 여행</option>
			</select>  
			<input type="hidden" name="planCate"id="planCate">
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