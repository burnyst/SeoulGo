<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획짜기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./jquery.cookie.js"></script>]
 <script>
$(function(){
		 $("#ibtn").click(function(){
			 alert("함수 작동");
			 $(location).attr("href","/plan/planwrite");
		 })
})
 </script>
</head>
<body>
<div align="center">
<h3>장소 검색 페이지</h3>
<!-- {Viewlist}-->
<table border="1" width="100%" class="table table-bordered table-hover text-center" id="example-table-2">
	 <tbody>
		<tr>
			<th>장소 번호</th><th>	일정장소	</th><th>	장소이름	</th>
		</tr>
		<c:forEach var="list" items="${placeview}"  varStatus="status">
			<form method="get" id="placesarch" name="placesarch" action="/plan/planwritenter">
					<tr>
						<td>
							 ${list.placeNo}<input type="hidden" value="${list.placeNo}" id="placeNo" name="placeNo">
						</td>
						<td>
							${list.addr1 }${list.addr2 } 
						<input type="hidden" name="addr1"value="${list.addr1}">
						<input type="hidden" name="addr2"value="${list.addr2}">
						</td>
						<td>
							${list.placename} <input type="hidden" name="placename"value="${list.placename}">
						</td>
						<td><input type="submit" id="enter" name="enter" value="장소 고르기"></td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>
	<%-- 페이징 처리 --%>

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
<!-- ${modelData[0][1][key2]}  ${list[status.index][planNo]}-->