<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript" src="${resourcePath}/js/plan/plan.js"></script>
<style type="text/css">
	#pro {
		width: 50px;
		height: 50px;
	}
	#pwFail, #deleteFail {
		color: red;
		font-weight: bold;
		font-size: x-large;
		align: center;
		margin: auto;
	}
</style>
</head>
<body>

	<h3>마이페이지</h3>
	<br>
	
	<table class="table">
		<thead>
			<tr>
				<th class="text-center">프로필 사진</th>
				<th class="text-center">아이디</th>
				<th class="text-center">이름</th>
				<th class="text-center">닉네임</th>
				<th class="text-center">생년월일</th>
				<th class="text-center">성별</th>
				<th class="text-center">이메일</th>
				<th class="text-center">휴대전화</th>
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
	<c:if test="${msg=='pwFail'}">
		<tr class="text-center">
			<td id="pwFail" colspan="8">비밀번호 변경이 실패했습니다.</td>
		</tr>
	</c:if>
	<c:if test="${msg=='deleteFail'}">
		<tr class="text-center">
			<td id="deleteFail" colspan="8">비밀번호가 일치하지 않아 회원탈퇴 실패했습니다.</td>
		</tr>
	</c:if>
	</table>
	<div class="text-center">
		<a href="./changePW" class="btn btn-outline-secondary btn-sm">비밀번호 변경</a>
		<a href="./memberInfo" class="btn btn-outline-primary btn-sm">회원정보 수정</a>
		<a href="./deleteAccount" class="btn btn-outline-danger btn-sm">회원탈퇴</a>
	</div>
	
	<hr>
	
	<h3>마이 플랜</h3>
	<br>

	<table class="table table-hover">
		<thead>
			<tr>
				<th class="text-center">일정 날짜</th>
				<th class="text-center">일정 이름</th>
				<th class="text-center">일정 장소</th>
				<th class="text-center">여행 유형</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${plist}" var="plist">
				<tr onclick="location.href='/plan/planview?planNo=${plist.planNo}'" style="cursor:hand" class="info">					
					<td width="15%" class="text-center"><fmt:formatDate value="${plist.planDate}" pattern="yyyy년 MM월 dd일 "/></td>
					<td width="15%" class="text-center">${plist.planTitle}</td>
					<td width="50%" class="text-center">${plist.placenamelist}</td>
					<td width="20%" class="text-center">${plist.planCate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%-- 페이징 처리 --%>
	<t:pageNav
	   endPage="${page.endPage}"
	   pageNo="${page.pageNo}"
	   totalPage="${page.totalPage}"
	   startPage="${page.startPage}"
	   uri="${pageUri}">
	</t:pageNav>
</body>
</html>