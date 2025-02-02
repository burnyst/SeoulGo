<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="libPath" value="${resourcePath}/lib" />
<c:set var="imagePath" value="${resourcePath}/img" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${basePath}/resources/js/member/memberInfo.js"></script>
<style type="text/css">
	#pro {
		width: 50px;
		height: 50px;
	}
</style>
</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}">
	<form action="${basePath}/member/memberInfoUpdate" method="post" enctype="multipart/form-data">
		<table class="table table-hover">
			<tbody>
				<tr>
					<th class="text-center" width="35%">프로필 사진</th>
					<td class="text-center">
						<c:choose>
							<c:when test="${mem.proSaveName eq null}">
								<div class="pro"><img id="pro" class="rounded-circle" src="${imagePath}/member/default.png"></div>
							</c:when>
							<c:when test="${mem.proSaveName != null}">
								<div class="pro"><img id="pro" class="rounded-circle" src="${imagePath}/member/${mem.proSaveName}"></div>
							</c:when>
						</c:choose>
						<input type="file" name="files" id="files" accept=".jpg, .jpeg, .gif, .png, .bmp"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">아이디</th>
					<td class="text-center">${mem.memberID}</td>
				</tr>
				<tr>
					<th class="text-center">이름</th>
					<td class="text-center">${mem.mName}</td>
				</tr>
				<tr>
					<th class="text-center">닉네임</th>
					<td class="text-center">
						<input type="text" name="nickname" id="nickname" placeholder="nickname" required="required"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">성별</th>
					<td class="text-center">
						<c:if test="${fn:contains(mem.gender,'M')}">
							<c:out value="남자"/>
						</c:if>
						<c:if test="${fn:contains(mem.gender,'F')}">
							<c:out value="여자"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th class="text-center">생년월일</th>
					<td class="text-center">${mem.birth}</td>
				</tr>
				<tr>
					<th class="text-center">이메일</th>
					<td class="text-center">
						<input type="email" id="email" name="email" placeholder="Email" required="required"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">전화번호</th>
					<td class="text-center">
						<input type="text" id="phone" name="phone" placeholder="'-'없이 번호만 입력해주세요" required="required"/>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="text-center">
			<input type="submit" class="btn btn-outline-primary btn-sm" value="수정하기">
			<input type="reset" id="cancelBtn" class="btn btn-outline-secondary btn-sm" value="취소">
		</div>
	</form>
</body>
</html>