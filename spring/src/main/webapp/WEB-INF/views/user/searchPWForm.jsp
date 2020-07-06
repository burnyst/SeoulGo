<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>비밀번호 찾기</h3>
	<br>
	
	<form id="searchPWForm" class="" action="../user/searchPWProc" method="post">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="" id="" placeholder="아이디를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="" id="" placeholder="이름을 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="" id="" placeholder="이메일을 입력해주세요" required="required"/><br>
						<input type="button" id="" value="인증번호 전송" required="required"/>
					</td>
				</tr>
				<tr>
					<th>인증번호</th>
					<td>
						<input type="text" name="" id="" placeholder="인증번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td>
						<input type="password" name="" id="" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td>
						<input type="password" name="" id="" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="" value="비밀번호 재설정"/>
						<input type="reset" id="" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>