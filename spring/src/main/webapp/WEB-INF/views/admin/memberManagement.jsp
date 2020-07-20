<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.seoulmate.seoulgo.page.PageObject" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="libPath" value="${resourcePath}/lib" />
<c:set var="imagePath" value="${resourcePath}/img" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SeoulGo 회원 리스트</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/memberManagement.js"></script>
<style type="text/css">
	table {
		margin: 0 auto;
	}

	#thead {
		background-color: #3fc1c9;
	}
	
	#pro {
		width: 50px;
		height: 50px;
	}
</style>
</head>
<body>
	<!-- 검색 부분 -->
	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="">검색유형</option>
				<option value="memberID">아이디</option>
				<option value="mName">이름</option>
				<option value="nickname">닉네임</option>
				<option value="email">이메일</option>
			</select>
		</div>
		<div class="w400" style="padding-right:10px">
			<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" placeholder="검색어를 입력해주세요">
		</div>	
		<div>
			<button class="btn btn-sm btn-outline-primary" name="btnSearch" id="btnSearch">검색</button>
		</div>
	</div>
	<form>
		<table class="table table-hover">
			<thead id="thead">
				<tr>
					<th class="text-center">프로필 사진</th>
					<th class="text-center">아이디</th>
					<th class="text-center">이름</th>
					<th class="text-center">닉네임</th>
					<th class="text-center">생년월일</th>
					<th class="text-center">성별</th>
					<th class="text-center">이메일</th>
					<th class="text-center">전화번호</th>
					<th class="text-center">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty memberList}">
						<tr>
							<td colspan="8" align="center">회원 데이터가 없습니다</td>
						</tr>
					</c:when>
					<c:when test="${not empty memberList}">
						<c:forEach items="${memberList}" var="list">
							<tr>
								<td class="text-center">
									<c:choose>
										<c:when test="${list.proSaveName eq null}">
											<img id="pro" src="${imagePath}/member/default.png">
										</c:when>
										<c:when test="${list.proSaveName != null}">
											<img id="pro" src="${imagePath}/member/${list.proSaveName}">
										</c:when>
									</c:choose>
								</td>
								<td class="text-center">
									${list.memberID}
									<input type="hidden" value="${list.memberID}" name="memberID" id="memberID" class="memberID">
								</td>
								<td class="text-center">${list.mName}</td>
								<td class="text-center">${list.nickname}</td>
								<td class="text-center">${list.birth}</td>
								<td class="text-center">
									<c:if test="${fn:contains(list.gender,'M')}">
										<c:out value="남자"/>
									</c:if>
									<c:if test="${fn:contains(list.gender,'F')}">
										<c:out value="여자"/>
									</c:if>
								</td>
								<td class="text-center">${list.email}</td>
								<td class="text-center">${list.phone1}-${list.phone2}-${list.phone3}</td>
								<td class="text-center">
									<input type="button" id="deleteBtn" name="deleteBtn" class="btn btn-outline-secondary btn-sm" value="탈퇴"/>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</form>
</body>
</html>