<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획짜기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
$(function(){
		 $("#ibtn").click(function(){
			 //alert("함수 작동");
			 $(location).attr("href","/plan/planwrite");
		 })		
})
$(function(){
		 $(".viewbutton").click(function(){
		    var str = ""
				var tdArr = new Array();	// 배열 선언
				var viewbutton = $(this);
			var tr = viewbutton.parent().parent();
			var td = tr.children();
			
			var pno = td.eq(0).text();
			
			var param = pno
			 //alert(pno+"번 글입니다.");
			 $(location).attr("href","/plan/planview"+'?&planNo='+pno);
		 })	
})
 </script>
</head>
<body>
<div align="center">
<h3>나의 일정 페이지</h3>

http://127.0.0.1:9000/plan/plan
<!-- {Viewlist}-->
<table border="1" width="100%" class="table table-bordered table-hover text-center" id="example-table-2">
	 <tbody>
		<tr>
			<th>일정 번호</th><th>일정날짜</th><th>	일정장소	</th><th>	일정이름	</th><th>여행 유형</th>
		</tr>
		<c:if test="${planNo=null}">
			<tr>
				<td>
					일정을 만들어주세요!
				</td>
			</tr>	
		</c:if>
			<c:forEach var="list" items="${plist}"  varStatus="status">
			<form method="post" id="planlist" name="planlist" action="/plan/planmodi">
					<tr>
						<td>
							${list.planNo } <input type="hidden" value="${list.planNo }" id="pno" name="pno">
						</td>
						<td>
							<fmt:formatDate value="${list.planDate}" pattern="yyyy년MM월dd일"/>
						</td>
						<td>
							${list.addr1 } <input type="hidden" value="${list.addr1}">
										   <input type="hidden" value="${list.addr2}">
						</td>
						<td>
							${list.planTitle}<input type="hidden" value="${list.planTitle }">
						</td>
						<td>
							${list.planCate}<input type="hidden" value="${planCate }">
						</td>
						<td><input type="submit" id="mobtn" name="mobtn" value="일정수정하기"></td>
						
						<td><input type="button" class="viewbutton" name="viewbutton"  value="일정상세보기" ></td>
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
	
<div class="Result2" id="Result2" >
</div>
	<table class="right" width="700" >
		<tbody>
			<tr class="right" align="center">
				<td><input type="button" id="ibtn" name="ibtn" value="일정짜보기"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
<!-- ${modelData[0][1][key2]}  ${list[status.index][planNo]}-->