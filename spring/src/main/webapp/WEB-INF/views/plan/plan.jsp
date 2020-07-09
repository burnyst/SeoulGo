<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획짜기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
	 $(function(){
		 $("#ibtn").click(function(){
			 alert("함수 작동");
			 
			 $(location).attr("href","../plan/planwrite");
			
		 })
		 
		 $("#mobtn").click(function(){
		 var form = document.planlist;
		 form.submit([]);
			 // $(location).attr("href","../plan/planmodi");
		 })
		 
	 })
	 
 </script>
</head>

<body>
<div align="center">
<h3>나의 일정 페이지</h3>
${DTOlist}
플랜번호 <br>

http://127.0.0.1:9000/plan/plan
<form method="get" id="planlist" name="planlist" action="../plan/planmodi">
	<table border="1" width="600">
		<tr>
			<th align="center">체크박스</th><th>일정날짜</th><th>	일정장소	</th><th>		일정이름	</th>
		</tr>
		<c:if test="${planNo=null}">
			<tr>
				<td>
					일정을 만들어주세요!
				</td>
			</tr>	
		</c:if>
			<c:forEach var="list" items="${DTOlist}"  varStatus="status">
					<tr>
						<td align="center">
							<input type="radio" name="listradio" id="listradio"/>
						</td>
						
						<td>
							<label for ="${list[index]}">${list.planDate}</label>
						</td>
						<td>
							<label for ="${list[index]}">${list.planTitle}</label>
						</td>
						<td>
							<label for ="${list[index]}">${list.planTitle}</label>
						</td>
							
					</tr>
			</c:forEach>
	</table>
</form>
	<table class="right" width="500" >
		<tbody>
			<tr class="right" align="center">
				<td><input type="button" id="ibtn" name="ibtn" value="일정짜기"></td>
				<td><input type="button" id="mobtn" name="mobtn" value="일정수정하기"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
<!-- ${modelData[0][1][key2]}  ${list[status.index][planNo]}-->