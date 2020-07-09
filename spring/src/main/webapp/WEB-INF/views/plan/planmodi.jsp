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
<title>일정수정하기</title>
</head>
<body>
<!-- 이 페이지는 일정을 짜는 페이지이다. 
 일정짜는데에는 PlanController에 페이지를 보여줄수 있는 컨트롤러를 집어넣을 예정이다.
   -->

<div style="float:left">
 	<div class="dropdown">
  	<button onclick="dropdownfunction()" class="dropbtn">일정장소</button>
	  	<div id="myDropdown" class="dropdown-content">
		    <a href="#home">일정장소1</a>
		    <a href="#about">일정장소2</a>
		    <a href="#contact">일정장소3</a>
	  	</div>
	</div>
</div>
<div id="map" style="width:500px;height:400px;float:left;'" ></div>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3 
		};

		var map = new kakao.maps.Map(container, options);
	</script>

<div>
	<table border="1" width="600px">
		<tr>
			<td>
				여행날짜
			</td>
			<td>
			 	${planDate }
			</td>
		</tr>
		<tr>
			<td>
				여행시간
			</td>
			<td>
				${plantime}
			</td>
		</tr>
		<tr>	
			<td>
				일정이름
			</td>
			<td>
				${param.planTitle}
			</td>
		</tr>
		<tr>
			<td>
				일정메모내용
			</td>
			<td>
				${param.plancontent}
			</td>
		</tr>
</table>
<input type="submit" class="btn btn-primary"  value="수정하기"/>
<button id="delmodi" class="btn btn-danger">삭제하기</button> 
<button id="nomodi"type="button" class="btn btn-info">수정취소하기</button>
</div>
</body>
</html>