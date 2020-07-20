<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<style type="text/css">
	#updateSuccess {
		color: blue;
		font-weight: bold;
		align: center;
		margin: auto;
	}
</style>
</head>
<body>
	<table class="table table-borderless">
		<tbody>
			<c:if test="${msg=='updateSuccess'}">
				<tr class="text-center">
					<td id="updateSuccess" colspan="2">${mem.memberID}님 회원정보 수정이 완료되었습니다.</td>
				</tr>
			</c:if>
		</tbody>	
	</table>
</body>
</html>