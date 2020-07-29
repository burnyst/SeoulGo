<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
	#pro {
		width: 50px;
		height: 50px;
	}
	#pwFail {
		color: red;
		font-weight: bold;
		font-size: xx-large;
		align: center;
		margin: auto;
	}
</style>
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
				<td class="text-center">
					<c:choose>
						<c:when test="${mem.proSaveName eq null}">
							<img id="pro" class="rounded-circle" src="${imagePath}/member/default.png">
						</c:when>
						<c:when test="${mem.proSaveName != null}">
							<img id="pro" class="rounded-circle" src="${imagePath}/member/${mem.proSaveName}">
						</c:when>
					</c:choose>
				</td>
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
	<c:if test="${msg=='pwFail'}">
		<tr class="text-center">
			<td id="pwFail" colspan="2">비밀번호 변경이 실패했습니다.</td>
		</tr>
	</c:if>
	<div class="text-center">
		<a href="./changePW" class="btn btn-secondary btn-sm">비밀번호 변경</a>
		<a href="./memberInfo" class="btn btn-primary btn-sm">회원정보 수정</a>
		<a href="./deleteAccount" class="btn btn-danger btn-sm">회원탈퇴</a>
	</div>
	
	<hr>
	
	<h3>마이 플랜</h3>
	<br>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>일정 날짜</th>
				<th>일정 이름</th>
				<th>일정 장소</th>
				<th>여행 유형</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
			</tr>
		</tbody>
	</table>
</body>
</html>