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
	<input type="hidden" id="addr1" name="addr1" value="${list.addr1}"/>
	<input type="hidden" id="addr2" name="addr2" value="${list.addr2}"/>
	<input type="hidden" id="placename" name="placename"value="${list.placename}">
	<input type="hidden" id="address" name="address" value="${list.addr1}${list.addr2}"/>
</c:forEach>

<h1>일정공유 게시판</h1>
<div id="map" style="width:400px;height:500px;float:left; position:relative;'">
</div>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

//마커를 생성합니다
var marker = new kakao.maps.Marker({
position: markerPosition
});

//마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map); 




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