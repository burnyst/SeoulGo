<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
	.center {
		text-align: center;
	}
</style>
</head>
<body>
	<h3>로그인</h3>
	<br>
	
	<form id="loginForm" class="" action="../user/loginProc" method="post">
		<table align="center">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" id="id" placeholder="Username" required="required"/>
				</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
					<input type="password" name="pw" id="pw" placeholder="Password" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="" value="로그인"/>
						<input type="reset" id="" value="취소"/>
					</td>
				</tr>
				<tr>
					<td>
						<a href="./registerForm">회원가입</a>
						<a href="./searchID">아이디 찾기</a>
						<a href="./searchPW">비밀번호 찾기</a>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>