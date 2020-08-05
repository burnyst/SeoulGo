<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html> 
<head>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services">
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
	<div id="map" style="width:500px;height:300px;float:center;'" ></div>
<script>
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
		
	var addr = getParameterByName("planplace")
	var planTitle = getParameterByName("planTitle")
	//	
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
	        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+planTitle+'</div>'
	    });
	    infowindow.open(map, marker);
	
	    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    map.setCenter(coords);
	} 
	});    
	</script>
</div>

<div align="center">
 	<form action="../plan/plandeleteok" method="post">
	 	<input type="hidden" id="planNo" name="planNo" value="${param.planNo}">
	 	<table>
	 		<tr>
	 			<th>일정을 삭제하시겠습니까?</th>
	 			
	 		</tr>
	 		<tr>
	 			<td><input type="submit" class="btn btn-outline-danger"  value="예"></td>
	 			<td><input type="button" class="btn btn-outline-primary" name="delno" id="delno"value="아니오"></td>
	 		</tr>
 		</table>
 	</form>
</div>
</body>
</html>