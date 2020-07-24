<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176"></script>
<meta charset="UTF-8">
<title>일정공유 게시판</title>
</head>
<body>
<c:forEach items="${pageview}" var="list" varStatus="status">
	<input type="hidden" id="addr1" value="${list.addr1}"/>
	<input type="hidden" id="addr2" value="${list.addr2}"/>
	<input type="hidden" id="placename" value="${list.place}">
	<input type="hidden" class="de_add">
</c:forEach>

<h1>일정공유 게시판</h1>
<div id="map" style="width:300px;height:300px;float:left; position:relative;'">
</div>
<script>
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
	geocoder.addressSearch( addr[i] , function(result, status) {
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
	    //map.setCenter(coords);
} 
});    
</script>

	
	<table border="1" width="60%">
	
			<tr>
				<th>여행장소</th>
				<th>아이디</th>
				<th>일정제목</th>
				<th>일정유형</th>
			</tr>
		<c:forEach items="${pageview}" var="list" varStatus="status">
				<tr>
					<td>
						<a href="/plan/planview?planNo=${list.planNo}">${list.addr1}${list.addr2}</a>
					</td>
					<td>
						${list.memberid}
					</td>
					<td>
						${list.planTitle}
					</td>
					<td>
						${list.planCate}
					</td>
				</tr>
			</c:forEach>
	
		</table>
	<t:pageNav
	   endPage="${page.endPage}"
	   pageNo="${page.pageNo}"
	   totalPage="${page.totalPage}"
	   startPage="${page.startPage}"
	   uri="${pageUri}">
	</t:pageNav>
	<div style=height:200px;></div>
</body>
</html>