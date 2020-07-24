<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" scope="request" />
<c:set var="libPath" value="${resourcePath}/lib" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${basePath}/resources/js/notice/list.js"></script>
</head>
<body>
	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th class="text-center">공지번호</th>
				<th class="text-center">공지제목</th>
				<th class="text-center">작성자</th>
				<th class="text-center">공지일자</th>
				<th class="text-center">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty LIST}">
					<tr>
						<td class="text-center" colspan="5">등록된 공지사항이 없습니다.</td>
					</tr>
				</c:when>
				<c:when test="${not empty LIST}">
					<c:forEach items="${LIST}" var="list">
						<tr id="dataRow" class="dataRow">
							<td class="text-center">
								${list.nNo}
								<input type="hidden" value="${list.nNo}" id="nNo" name="nNo">
							</td>
							<td class="text-center">${list.nTitle}</td>
							<td class="text-center">
								<c:if test="${list.nWriter ne null}">
									<c:out value="관리자"></c:out>
								</c:if>
							</td>
							<td class="text-center">
								<fmt:formatDate value="${list.nDate}" pattern="yyyy년 MM월 dd일"/>
							</td>
							<td class="text-center">${list.nCnt}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a class="btn btn-primary" href="/notice/write">작성</a>
	</sec:authorize>
</body>
</html>