<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="resourcePath" value="${basePath}/resources" />
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/planSboard.js"></script>
<meta charset="UTF-8">
<title>일정공유 게시판</title>
<style>
 table, th, td {
    border: 1px solid #bcbcbc;
  }
</style>
</head>
<body>
<!-- 
<c:forEach items="${place}" var="list">
	<input type="hidden" id="addr1" name="addr1" value="${list.addr1}"/>
	<input type="hidden" id="addr2" name="addr2" value="${list.addr2}"/>
	<input type="hidden" id="address" name="address" value="${list.addr1}${list.addr2}"/>
</c:forEach> 
-->
<c:forEach items="${pageview}" var="list" varStatus="status">
	<input type="hidden" id="placename" name="placename"value="${list.placenamelist}">
</c:forEach>

<h1>일정공유 게시판</h1>
<div id="map" style="width:400px;height:500px;float:left; position:relative;'">
</div>
<div style="height: 400px; width=50%; text-align: center; ">
	<table border="1" >
		 
				<tr>
					<th>여행장소</th>
					<th>아이디</th>
					<th>일정제목</th>
					<th>일정유형</th>
				</tr>
	<c:forEach items="${pageview}" var="list" varStatus="status">
			<tr onclick="location.href='/plan/planview?&planNo=${list.planNo}'" style="cursor:hand">
				<td>
					${list.placenamelist}
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
</div>
</body>
</html>