<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<t:path></t:path>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;" charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><decorator:title /></title>
	<link rel="stylesheet" href="${libPath}/fontawesome/5.13.1/css/all.css">
	<link rel="stylesheet" href="${libPath}/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${resourcePath}/css/base.css">
	<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
	<script src="${libPath}/popper/1.16.0/umd/popper.min.js"></script>
	<script src="${libPath}/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<decorator:head/>
</head>
<body>
	<div class="text-center mt-3">
		<h1><a class="navbar-brand" href="${basePath}/place/list"><img src="${imagePath}/logo.png" width="308" height="154"/></a></h1>
	</div>
	<nav class="navbar navbar-expand-sm navbar-dark font-weight-bold navbar-custom">
		<a class="navbar-brand" href="${basePath}/place/list">SeoulGo</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item p-1">
					<a class="nav-link" href="${basePath}/place/list">장소찾기</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="${basePath}/plan/plan">플래너</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="${basePath}/review/PlaceListView">리뷰</a>
				</li>
				<sec:authorize access="hasRole('ROLE_MEMBER')">
					<li class="nav-item p-1">
						<a class="nav-link" href="${basePath}/member/mypage">마이페이지</a>
					</li>
				</sec:authorize>
				<li class="nav-item p-1">
					<a class="nav-link" href="${basePath}/notice/list">공지사항</a>
				</li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item p-1">
						<a class="nav-link" href="${basePath}/admin/memberManagement">회원 리스트</a>
					</li>
					<li class="nav-item p-1">
						<a class="nav-link" href="${basePath}/place/insert">장소 입력</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item p-1 small">
						<a class="nav-link" href="${basePath}/member/logout">로그아웃</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li class="nav-item p-1 small">
						<a class="nav-link" href="${basePath}/user/login">로그인</a>
					<li class="nav-item p-1 small">
						<a class="nav-link" href="${basePath}/user/register">회원가입</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>
	<article class="container p-3 p-md-5">
		<decorator:body />
	</article>
	<footer class="jumbotron text-center">
		<p>copy right.</p>
	</footer>
</body>
</html>