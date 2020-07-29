<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
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
	<input id="basePath" type="hidden" value="${basePath}" />
	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="">검색유형</option>
				<option value="nTitle">제목</option>
				<option value="nContent">내용</option>
			</select>
		</div>
		<div class="w400" style="padding-right:10px">
			<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" placeholder="검색어를 입력해주세요">
		</div>	
		<div>
			<button class="btn btn-sm btn-outline-primary" name="btnSearch" id="btnSearch">검색</button>
		</div>
	</div>
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
		<a class="btn btn-primary" href="${basePath}/notice/write">작성</a>
	</sec:authorize>
	<c:if test="${notice.totalRow > notice.perPageNum}">
		<tr class="dataRow">
			<td colspan="5">
				<pageNav:pageNav endPage="${notice.endPage}" 
								 totalPage="${notice.totalPage}" 
								 startPage="${notice.startPage}" 
								 uri="${pageUri}"
								 params="&keyword=${notice.keyword}&searchType=${notice.searchType}"
								 pageNo="${notice.pageNo}"/>
			</td>
		</tr>
	</c:if>
</body>
</html>