<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176"></script>
<script>
$(function(){
	 $("#nomodi").click(function(){
		 alert("함수 작동");
		 $(location).attr("href","../plan/plan");
		 //../주소부터는 컨트롤러를 만든 뒤 수정해야 한다..
	 })
	 $("#delmodi").click(function(){
		 alert("함수2 작동");
		 $(location).attr("href","../plan/plandelete");
		 //../주소부터는 컨트롤러를 만든 뒤 수정해야 한다..
	 })
})
function dropdownfunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

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
	#planlist{margin-top:20px;}
</style>
<meta charset="UTF-8">
<title>일정수정하기</title>
</head>
<body>
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
<div class="planlist">
	<div style="float:left; margin-right:10px">여행 날짜</div> 
	<div>몇월 몇일</div>
	<div style="float:left; margin-right:10px">여행장소</div>
	<div>경북궁</div>
	<div style="float:left; margin-right:10px">일정제목</div>
	<div>가족 패키지 여행</div>
	<div style="float:left; margin-right:10px">여행유형</div>
	<div>가족과 함께, 패키지 여행</div>
</div>
<div style="height:250px;">여기는 사진이 출력되어 나올 위치입니다.</div>
<div style="text-align: center;">

	<input type="submit" class="btn btn-primary"  value="수정하기"/>
	<button id="delmodi" class="btn btn-danger">삭제하기</button> 
	<button id="nomodi"type="button" class="btn btn-info">수정취소하기</button>
</div>
</body>
</html>