<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
	<form action="/member/changePW" method="post">
		<table>
			<tbody>
				<tr>
					<th>기존 비밀번호</th>
					<td>
						<input type="password">
					</td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td>
						<input type="password">
					</td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td>
						<input type="password">
					</td>
				</tr>
				<tr>
					<td>
						<button>수정하기</button>
						<button type="button" id="cancelBtn">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>