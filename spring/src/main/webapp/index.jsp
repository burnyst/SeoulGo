<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h3>대문(index)</h3>
	<p>http://localhost:9000/index.jsp</p>
	<ul>
		<sec:authorize access="isAnonymous()">
			<li><a href="./user/loginForm">로그인폼(폼 요청)</a></li>		
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_MEMBER')">
		<li><a href="./member/mypageForm">마이페이지(폼 요청)</a></li>
		<li><a href="./member/memberInfo">회원정보수정(폼 요청)</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<li><a href="./member/logout">로그아웃</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="./admin/menuForm">관리자 메뉴(폼 요청)</a></li>
		</sec:authorize>
	</ul>
</body>
</html>