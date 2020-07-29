<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="libPath" value="${resourcePath}/lib" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${basePath}/resources/js/member/changePW.js"></script>
</head>
<body>
	<form action="${basePath}/member/pwUpdate" method="post">
		<table class="table table-hover">
			<tr>
				<th class="text-center">기존 비밀번호</th>
				<td class="text-center"><input type="password" id="pw" name="pw" required="required"></td>
			</tr>
			<tr>
				<th class="text-center">새 비밀번호</th>
				<td class="text-center"><input type="password" id="memberPW" name="memberPW" required="required"></td>
			</tr>
			<tr>
				<th class="text-center">새 비밀번호 확인</th>
				<td class="text-center"><input type="password" id="memberPW2" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="hidden" value="${mem.memberID}" name="memberID">
					<input type="submit" class="btn btn-primary btn-sm" value="비밀번호 변경">
					<input type="reset" class="btn btn-secondary btn-sm" id="cancelBtn" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>