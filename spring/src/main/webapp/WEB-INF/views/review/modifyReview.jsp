<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- Responsive Web Design - The Viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/review/starRating.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/review/fileUpload.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review/starRating.css">
</head>
<body>
<div class="text">
	<%-- 반복문을 이용하여 줄 출력  --%>
	<c:forEach items="${Info}" var="dto">	
		<h3>${dto.placeName}&nbsp;&nbsp;<a href="detailView?placeNo=${placeNo}" class="btn btn-outline-primary btn-sm">리뷰 보기</a></h3>
		<h5>${dto.addr1}&nbsp;${dto.addr2}</h5>
		<h5>${dto.tel}</h5>
	</c:forEach>
</div>

<div class="container">
	
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
    	<img src="<spring:url value='/resources/img/review/11.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/22.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/33.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/44.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/55.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/66.jpg'/>">
    </div>
    <div class="carousel-item">
    	<img src="<spring:url value='/resources/img/review/77.jpg'/>">
    </div>
  </div>
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#myCarousel" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>
</div>

<form action="modifyProc?placeNo=${placeNo}&rno=${rno}" method="post" encType="multipart/form-data">
	<input type="hidden" name="placeNo" value="${placeNo}"/>
	<input type="hidden" name="rno" value="${rno}"/>
	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th class="text-center" colspan="5">리뷰 수정하기</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="text-center">평점</th>
				<td colspan="4">
			<span class="rRate">
				<span class="input">
					<input type="radio" name="rRate" id="p1" value="0.5" required="required"><label for="p1">&nbsp;최악이에요</label>
					<input type="radio" name="rRate" id="p2" value="1.0" required="required"><label for="p2">&nbsp;최악이에요</label>
					<input type="radio" name="rRate" id="p3" value="1.5" required="required"><label for="p3">&nbsp;기대이하였어요</label>
					<input type="radio" name="rRate" id="p4" value="2.0" required="required"><label for="p4">&nbsp;기대이하였어요</label>
					<input type="radio" name="rRate" id="p5" value="2.5" required="required"><label for="p5">&nbsp;나쁘지 않아요</label>
					<input type="radio" name="rRate" id="p6" value="3.0" required="required"><label for="p6">&nbsp;나쁘지 않아요</label>
					<input type="radio" name="rRate" id="p7" value="3.5" required="required"><label for="p7">&nbsp;좋아요!</label>
					<input type="radio" name="rRate" id="p8" value="4.0" required="required"><label for="p8">&nbsp;좋아요!</label>
					<input type="radio" name="rRate" id="p9" value="4.5" required="required"><label for="p9">&nbsp;행복해요!</label>
					<input type="radio" name="rRate" id="p10" value="5.0" required="required"><label for="p10">&nbsp;행복해요!</label>
				</span>
				<output for="rRate"><b></b></output>
			</span>
				</td>
			</tr>
			<tr>
				<th class="text-center">리뷰제목</th>
				<td colspan="4"><input type="text" name="rTitle" class="form-control" placeholder="제목을 입력하세요" required="required"></td>
			</tr>
			<tr>
				<th class="text-center">리뷰내용</th>
				<td colspan="4"><textarea name="rContent" class="form-control" placeholder="내용을 입력하세요" required="required"></textarea></td>
			</tr>
			<tr>
				<th class="text-center">여행한 날짜</th>
				<td colspan="4"><input type="date" name="rVisit" required="required"/></td>
			</tr>
			<tr>
				<th class="text-center">여행자 유형</th>
				<td colspan="4">
				<select name="rCate" required="required">
	    		<option value="" selected="selected">선택</option>
	    		<option value="가족">가족</option>
	    		<option value="커플">커플</option>
	    		<option value="단독">단독</option>
	    		<option value="비즈니스">비즈니스</option>
	    		<option value="친구">친구</option>
				</select>
				</td>
			</tr>
			<tr>
 				<th class="text-center">첨부파일</th>
 				<td colspan="4">
 					<input type="button" value="추가" id="aBtn"/>
 					<input type="button" value="삭제" id="dtn"/>
 				</td>
 			</tr>
			<tr>
 				<th class="text-center"></th>
 				<td colspan="4">
 					<input type="file" name="files" id="files"/>
 				</td>
 			</tr>
 			<tr id="copy">
			<td colspan="5" class="text-center">
				<input type="checkbox" required="required"/>
				이 리뷰는 나의 경험을 바탕으로 작성한  의견입니다. 나는 이 시설과 개인적 혹은 업무적인 관련이 없으며, 이 리뷰를 작성하는 조건으로 해당 시설로부터 어떠한 금전적 또는 물질적인 대가를 제공받지 않았습니다.
			</td>
			</tr>
		</tbody>
	</table>
		<div class="col-sm-12" style="text-align:center;">
			<button type="submit" class="btn btn-outline-primary">수정 완료</button>
			<button type="reset" class="btn btn-outline-secondary">다시 작성</button>
		</div>
</form>

</body>
</html>