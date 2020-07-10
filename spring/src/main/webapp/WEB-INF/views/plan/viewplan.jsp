<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176">
</script>
</head>
<body>
<h1>일정 게시글 상세보기.</h1>
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
		    <a href="#iljung1">${jangso1}</a>
		    <a href="#iljung1">${jangso2}</a>
		    <a href="#iljung1">${jangso3}</a>
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