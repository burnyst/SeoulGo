<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html> 
<head>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04926447ff4e969a08d92e18379b0176&libraries=services">
	</script>

<meta charset="UTF-8">
<title>일정삭제페이지</title>
<script>
$(function(){
	 $("#delno").click(function(){
		 alert("삭제를 취소하셨습니다.");
		 $(location).attr("href","../plan/plan");
	 })
	 
})

</script>
</head>
<body>
<div align="center">
 	<form action="../plan/plandeleteok" method="post">
	 	<input type="hidden" id="planNo" name="planNo" value="${param.planNo}">
	 	<table>
	 		<tr>
	 			<th>일정을 삭제하시겠습니까?</th>
	 		</tr>
	 		<tr>
	 			<td><input type="submit" class="btn btn-outline-danger"  value="예"></td>
	 			<td><input type="button" class="btn btn-outline-primary" name="delno" id="delno"value="아니오"></td>
	 		</tr>
 		</table>
 	</form>
</div>
</body>
</html>