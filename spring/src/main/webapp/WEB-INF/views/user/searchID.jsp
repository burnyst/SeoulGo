<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="${basePath}/resources/js/user/searchID.js"></script>
</head>
<body>
	<form id="searchID" action="${basePath}/user/searchIDProc" method="post">
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th colspan="2" class="text-center">아이디 찾기</th>
				</tr>
			</thead>
			<tbody>			
				<tr>
					<th class="text-center">이름</th>
					<td>
						<input type="text" name="mName" id="mName" placeholder="이름을 입력해주세요" required="required"/>
						<div class="check_font" id="name_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center" width="35%">이메일</th>
					<td>
						<input type="text" name="email" id="email" placeholder="이메일을 입력해주세요" required="required"/>
						<button type="button" name="emailBtn" id="emailBtn" class="btn btn-outline-secondary btn-sm">인증번호 전송</button>
						<div class="check_font" id="email_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">인증번호</th>
					<td>
						<input type="text" name="emailNum" id="emailNum" placeholder="인증번호를 입력해주세요" required="required"/>
						<input type="button" name="confirmChk" id="confirmChk" class="btn btn-outline-secondary btn-sm" value="인증번호 확인">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="button" id="searchIDProc" class="btn btn-primary btn-sm" value="아이디 찾기"/>
						<input type="button" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>