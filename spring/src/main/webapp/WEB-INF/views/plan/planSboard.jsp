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
<h1>일정공유 게시판</h1>
<div id="map" style="width:300px;height:300px;float:left; position:relative;'">
</div>
<script>
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3 
	};

	var map = new kakao.maps.Map(container, options);
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