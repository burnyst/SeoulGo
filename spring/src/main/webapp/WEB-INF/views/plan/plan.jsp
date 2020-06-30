<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>여행계획짜기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
	 $(function(){
		 $("#ibtn").click(function(){
			 alert("함수 작동");
			 $(location).attr("href","../plan/planwrite");
			 //../주소부터는 컨트롤러를 만든 뒤 수정해야 한다..
		 })
		 
	 })
 </script>
</head>
 
<body>
<h3>나의 일정 페이지</h3>
<form>
<table border="1">
	<tr>
		<th>일정날짜</th><th>	일정장소	</th><th>		일정이름	</th>
	</tr>
	<tr>
		<c:if test="${planno eq 0}">
			<td>
				일정을 만들어주세요!
			</td>
		</c:if>
		<td>
			글2
		</td>
	</tr>
	<tr>
		<td>
			글3
		</td>
	</tr>
</table>
</form>
<table border="1" class="right">
	<tbody>
		<tr class="right">
			<td><input type="button" id="ibtn" name="ibtn" value="일정짜기"></td>
			<td><input type="button" id="dbtn" name="dbtn" value="일정삭제하기"/></td>
		</tr>
	</tbody>
</table>
</body>
</html>