<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="resourcePath" value="${basePath}/resources" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/plan/plan.js"></script>
</head>
<body>  


<div align="center">
<h3>마이 플랜</h3>
<!-- {Viewlist}-->
<form method="post" id="planlist" name="planlist" action="/plan/planmodi">
<table border="1" style="width:100%"  class="table table-bordered table-hover text-center" id="example-table-2">
	 <tbody>
		<tr>
			<th>글 번호</th><th>일정날짜</th><th>	일정이름	</th><th>여행 유형</th><th>	일정장소	</th>
		</tr>
		<c:if test="${PLANNO=null}">
			<tr>
				<td>
					일정을 만들어주세요!
				</td>
			</tr>	
		</c:if>
			<tbody>
			<c:forEach var="list" items="${plist}" varStatus="status">
					<tr onclick="location.href='/plan/planview?&planNo=${list.planNo}'" style="cursor:hand" class="info">
						<td>
							${page.endRow-status.index} <input type="hidden" value="${list.planNo}" id=planNo name="planNo">
						</td>
							<td>
								<fmt:formatDate value="${list.planDate}" pattern="yy년MM월dd일"/>
							</td>
						<td>
							${list.planTitle}
							<input type="hidden" id="plantitle" value="${list.planTitle}">
						</td>
						<td>
							${list.planCate}
							<input type="hidden" value="${list.planCate}">
						</td>
						
						<td>${list.placenamelist}<br>
							<br>
							<input type="hidden" id="placeName" value="${list.placenamelist }">
						</td>
					</tr>
				</c:forEach>
		
		</tbody>
	</table>
</form>
	<%-- 페이징 처리 --%>
<t:pageNav
   endPage="${page.endPage}"
   pageNo="${page.pageNo}"
   totalPage="${page.totalPage}"
   startPage="${page.startPage}"
   uri="${pageUri}">
</t:pageNav>	
	
<div class="Result2" id="Result2" >
</div>
	<table class="right" width="700" >
		<tbody>
			<tr class="right" align="center">
				<td><input type="button" class="btn btn-primary" id="ibtn" name="ibtn" value="일정 작성"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
<!-- ${modelData[0][1][key2]}  ${list[status.index][planNo]}-->