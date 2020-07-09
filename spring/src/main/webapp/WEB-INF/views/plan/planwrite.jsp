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
	</script>
<form action="" method="post">
<div style="float:left">
	<div class="dropdown">
  	<button onclick="dropdownfunction()" class="dropbtn">일정장소</button>
	  	<div id="myDropdown" class="dropdown-content">
		    <a href="#iljung1">${list}</a>
		    <a href="#iljung1">${list}</a>
		    <a href="#iljung1">${list}</a>
	  	</div>
	</div>
</div>
	<div style="float:left">
		<table border="1" width="600px">
			<tr>
				<td>
					날짜
				</td>
				<td>
					시간
				</td>
				<td>
					여행제목
				</td>
			</tr>
				<tr>
					<td>
						<input type="date">
					</td>
					<td>
						<input type="time">
					</td>
					<td>
						<input type="text">
					</td>
				</tr>
		</table>
	<input class="btn btn-success" type="submit" value="일정짜기"> <input class="btn btn-info" type="reset" value="다시쓰기">
	<a href="javascript:history.back();" class = "btn btn-warning">뒤로가기</a>
</div>

</form>
</body>
</html>