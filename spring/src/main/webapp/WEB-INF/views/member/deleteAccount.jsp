<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
	<form action="/member/deleteSuccess" method="post">
		<table class="table table-borderless">
			<tr>
				<th class="text-center">아이디</th>
				<td class="text-center">${mem.memberID}</td>
			</tr>
			<tr>
				<th class="text-center">비밀번호</th>
				<td class="text-center"><input type="password" id="memberPW" name="memberPW" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="hidden" value="${mem.memberID}" name="memberID">
					<input type="submit" class="btn btn-danger btn-sm" value="회원탈퇴">
					<input type="reset" class="btn btn-secondary btn-sm" id="cancelBtn" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>