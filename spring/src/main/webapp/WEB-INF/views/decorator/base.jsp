<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><decorator:title /></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
	<script src="${pageContext.request.contextPath}/resources/lib/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/lib/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/lib/bootstrap.min.js"></script>
	<decorator:head/>
</head>
<body>
	<div class="text-center mt-3">
		<h1><img src="${pageContext.request.contextPath}/resources/img/logo4.png" width="308" height="154"/></h1>
	</div>
	<nav class="navbar navbar-expand-sm bg-success navbar-dark font-weight-bold">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/place/list">SeoulGo</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item p-1">
					<a class="nav-link" href="${pageContext.request.contextPath}/place/list">장소찾기</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="#">플래너</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="#">리뷰작성</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="#">공지사항</a>
				</li>
				<li class="nav-item p-1">
					<a class="nav-link" href="#">마이페이지</a>
				</li>
				<li class="nav-item p-1 small">
					<a class="nav-link" href="#">로그인</a>
				<li class="nav-item p-1 small">
					<a class="nav-link" href="#">회원가입</a>
				</li>
			</ul>
		</div>
	</nav>
	<article class="container">
		<decorator:body />
	</article>
	<footer class="jumbotron text-center">
		<p>copy right.</p>
	</footer>
</body>
</html>