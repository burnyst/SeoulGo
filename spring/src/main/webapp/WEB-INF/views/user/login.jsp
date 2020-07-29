<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="${basePath}/resources/js/user/msg.js"></script>
<style type="text/css">
	table {
		margin: 0 auto;
	}
	
	thead {
		font-size: xx-large;
		font-weight: bold;
	}
	
	#pwUpdate {
		color: blue;
		font-weight: bold;
		align: center;
		margin: auto;
	}
	
	#deleteSuccess {
		color: red;
		font-weight: bold;
		align: center;
		margin: auto;
	}
</style>
</head>
<body>
	<form id="loginForm" action="${basePath}/login" method="post">
		<table class="table table-borderless">
			<thead>
				<tr>
					<th colspan="2">Sign in</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-center msg"></tr>
				<tr>
					<td class="text-center">
						<input type="text" name="memberID" id="memberID" placeholder="Username" required="required"/>
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<input type="password" name="memberPW" id="memberPW" placeholder="Password" required="required"/>
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<input type="submit" id="" class="btn btn-primary btn-sm" value="로그인"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<ul>
							<li><a href="./register">회원가입</a><br></li>
							<li><a href="./searchID">아이디 찾기</a><br></li>
							<li><a href="./searchPW">비밀번호 찾기</a></li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>