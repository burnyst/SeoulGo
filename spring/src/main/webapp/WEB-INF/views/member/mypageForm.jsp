<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>마이페이지</h3>
	<br>
	
	<form id="mypageForm" action="" method="post">
		<table>
			<tr>
				<th>프로필 사진</th>
				<th>아이디</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>생년월일</th>
				<th>성별</th>
				<th>이메일</th>
				<th>휴대전화</th>
			</tr>
			<tr>
				<td></td>
				<td>${MDTO.memberID}</td>
				<td>${MDTO.mName}</td>
				<td>${MDTO.nickname}</td>
				<td>${MDTO.birth}</td>
				<td>${MDTO.gender}</td>
				<td>${MDTO.email}</td>
				<td>${MDTO.phone1} - ${MDTO.phone2} - ${MDTO.phone3}</td>
			</tr>
		</table>
	</form>
	<div>
		<a href="" class="btn btn-secondary btn-sm">비밀번호 변경</a>
		<a href="" class="btn btn-primary btn-sm">회원정보 수정</a>
		<a href="./deleteAccount" class="btn btn-danger btn-sm">회원탈퇴</a>
	</div>
	
	<hr>
	
	<h3>마이 플랜</h3>
	<br>
	
	<table>
		<tr>
			<th>일정번호</th>
			<th>일정날짜</th>
			<th>일정제목</th>
			<th>여행유형</th>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
</body>
</html>