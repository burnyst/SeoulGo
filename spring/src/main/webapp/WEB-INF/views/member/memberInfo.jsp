<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<form action="/member/memberInfoUpdate" method="post">
		<table>
			<tbody>
				<tr>
					<th>프로필 사진</th>
					<td>
						<input type="file" name="files" id="files" accept=".jpg, .jpeg, .gif, .png, .bmp"/>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>수정불가</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>수정불가</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="nickname" id="nickname" placeholder="nickname" required="required"/>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>수정불가</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>수정불가</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" id="email" name="email" placeholder="Email" required="required"/>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>수정불가</td>
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