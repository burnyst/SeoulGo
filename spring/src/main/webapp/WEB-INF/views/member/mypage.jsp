<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>마이페이지</h3>
	<br>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<td class="text-center">프로필 사진</td>
				<td class="text-center">아이디</td>
				<td class="text-center">이름</td>
				<td class="text-center">닉네임</td>
				<td class="text-center">생년월일</td>
				<td class="text-center">성별</td>
				<td class="text-center">이메일</td>
				<td class="text-center">휴대전화</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-center"></td>
				<td class="text-center">${mem.memberID}</td>
				<td class="text-center">${mem.mName}</td>
				<td class="text-center">${mem.nickname}</td>
				<td class="text-center">${mem.birth}</td>
				<td class="text-center">
					<c:if test="${fn:contains(mem.gender,'M')}">
						<c:out value="남자"/>
					</c:if>
					<c:if test="${fn:contains(mem.gender,'F')}">
						<c:out value="여자"/>
					</c:if>
				</td>
				<td class="text-center">${mem.email}</td>
				<td class="text-center">${mem.phone1}-${mem.phone2}-${mem.phone3}</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="./changePW" class="btn btn-secondary btn-sm">비밀번호 변경</a>
		<a href="./memberInfo" class="btn btn-primary btn-sm">회원정보 수정</a>
		<a href="./deleteAccount" class="btn btn-danger btn-sm">회원탈퇴</a>
	</div>
	
	<hr>
	
	<h3>마이 플랜</h3>
	<br>
	
	<table>
		<tr>
			<td></td>
		</tr>
	</table>
</body>
</html>