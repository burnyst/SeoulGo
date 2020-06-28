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
	<h3>로그인</h1>
	<br>
	
	<!-- 로그인이 되지 않은 경우 -->
	<c:if test="">
		<form id="loginForm" class="" action="../loginProc" method="post">
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
							<a href="/user/registerForm">회원가입</a>
							<a href="/user/searchID">아이디 찾기</a>
							<a href="/user/searchPW">비밀번호 찾기</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</c:if>
	
	<%-- 로그인이 된 경우 --%>
	<c:if test="">
		<table align="center">
			<tbody>
				<tr>
					<th>${sessuinSecope.MID}님 접속 중입니다</th>
				</tr>
				<tr>
					<th>
						<input type="button" id="" value="Main"/>
						<input type="button" id="" value="로그아웃"/>
					</th>
				</tr>
			</tbody>
		</table>
	</c:if>	
</body>
</html>