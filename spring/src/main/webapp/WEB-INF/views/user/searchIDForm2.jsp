<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/user/js/searchID2.js"></script>
</head>
<body>
	<h3>아이디 찾기</h3>
	<br>

	<form id="searchIDForm" class="" action="/user/searchIDProc" method="post">
		<table>
			<tbody>
				<c:if test="${msg == 'fail'}">
				<tr>
					<td colspan="2">입력한 정보와 일치하는 아이디가 존재하지 않습니다.</td>
				</tr>
				</c:if>	
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="mName" id="mName" value="${result.mName}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="email" id="email" value="${result.email}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>인증번호</th>
					<td>
						<input type="text" name="emailNum" id="emailNum" placeholder="인증번호를 입력해주세요" required="required" autofocus="autofocus"/>
						<input type="button" name="confirmChk" id="confirmChk" class="btn btn-outline-secondary btn-sm" value="인증번호 확인">
						<input type="hidden" name="confirmNum" id="confirmNum" value="${confirmNum}"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="searchIDProc" class="btn btn-primary btn-sm" value="아이디 찾기"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>