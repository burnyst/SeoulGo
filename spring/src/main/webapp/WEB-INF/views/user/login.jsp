<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
	table {
		margin: 0 auto;
	}
</style>
</head>
<body>
	<form id="loginForm" action="/login" method="post">
		<table>
			<tbody>
				<c:if test="${msg=='pwUpdate'}">
					<tr>
						<td colspan="2">비밀번호 변경에 성공했습니다. 다시 로그인해주세요.</td>
					</tr>
				</c:if>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" placeholder="Username" required="required"/>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
					<input type="password" name="memberPW" id="memberPW" placeholder="Password" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="" class="btn btn-primary btn-sm"value="로그인"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
				<tr>
					<td>
						<a href="./register">회원가입</a><br>
						<a href="./searchID">아이디 찾기</a><br>
						<a href="./searchPW">비밀번호 찾기</a>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>