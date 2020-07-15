<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html> 
<head>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176">
	</script>

<meta charset="UTF-8">
<title>일정삭제페이지</title>
<script>
$(function(){
	 $("#delno").click(function(){
		 alert("삭제를 취소하셨습니다.");
		 $(location).attr("href","../plan/plan");
		 
	 })
	 
})

</script>
</head>
<body>
<div align="center">
	<div id="map" style="width: 500px; height: 400px;"></div>
	<script>
		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(33.450701, 126.570667),
			level : 3
		};
		var map = new kakao.maps.Map(container, options);
	</script>
</div>
<div align="center">
 	<table>
 		<tr>
 			<th>일정을 삭제하시겠습니까?</th>
 		</tr>
 		<tr>
 			<td><input type="button" class="btn btn-info" name="delok" id="delok" value="예"></td>
 			<td><input type="button" class="btn btn-primary" name="delno" id="delno"value="아니오"></td>
 		</tr>
 	</table>
</div>
</body>
</html>