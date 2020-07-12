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
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${MDTO.memberID}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input name="pw" id="pw" type="password" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-danger btn-sm">탈퇴하기</button>
						<button type="button" id="cancelBtn" class="btn btn-secondary btn-sm">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>