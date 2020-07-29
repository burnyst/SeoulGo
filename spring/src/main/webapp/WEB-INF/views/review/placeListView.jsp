<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- Responsive Web Design - The Viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장소 게시판</title>

<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	
	var placeNo = document.getElementsByName("placeNo");
	for (let i=0; i<placeNo.length; i++){
		// 데이터의 한 줄 클릭 이벤트 처리 -> 글보기로 이동
		$(".dataRow"+placeNo[i].value).click(function(){
			location="../review/writeReview?placeNo="+placeNo[i].value;		
		});
	}
});
</script>
</head>
<body>
	<h3 style="text-align: center;">방문한 장소 리뷰 작성하기</h3><br>
	<!-- 검색 부분 -->
	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="place">장소명</option>
				<option value="address">주소</option>
				<option value="phone">전화번호</option>
			</select>
		</div>
		
		<div class="w400" style="padding-right:10px">
			<input type="text" class="form-control form-control-sm" name="search" id="search" placeholder="장소, 음식점">
		</div>	
		
		<div>
			<button class="btn btn-sm btn-outline-primary" name="btnSearch" id="btnSearch">검색</button>
		</div>
	</div>
	<%-- 장소 목록 출력 --%>
	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th>장소</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>리뷰 수</th>
				<th colspan="2">평점</th>
			</tr>
		</thead>
		<tbody>
			<%-- 반복문을 이용하여 줄 출력 예정  --%>
			<c:forEach items="${list}" var="dto">
				<tr class="dataRow${dto.placeNo}"
				style="cursor: pointer;">
				<input type="hidden" value="${dto.placeNo}" name="placeNo">
				<%-- dto 안의 get()으로 가져와야 한다.  --%>
					<td width="20%">${dto.placeName}</td>
					<td width="45%">${dto.addr1}&nbsp;${dto.addr2}</td>
					<td width="15%">${dto.tel}</td>
					<td width="10%">100건</td>
					<td width="10%">4.8점</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<!-- 페이징 -->
	<div class="container">
	<t:pageNav
	   endPage="${page.endPage}"
	   pageNo="${page.pageNo}"
	   totalPage="${page.totalPage}"
	   startPage="${page.startPage}"
	   uri="${pageUri}"
	   params="&keyword=${page.keyword}&type=${page.type}">
	</t:pageNav>
	</div>
</body>
</html>