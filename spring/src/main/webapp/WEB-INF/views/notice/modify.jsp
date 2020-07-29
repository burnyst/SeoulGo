<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<style type="text/css">
	#nTitle {
		width: 370px;
	}
</style>
</head>
<body>
	<form action="${basePath}/notice/modifyProc?nNo=${ndto.nNo}" method="post">
		<table class="table">
			<tbody>
				<tr>
					<th class="text-center" width="35%">공지번호</th>
					<td class="text-center">${ndto.nNo}</td>
				</tr>
				<tr>
					<th class="text-center">공지제목</th>
					<td class="text-center">
						<input type="text" value="${ndto.nTitle}" name="nTitle" id="nTitle" required="required">
					</td>
				</tr>
				<tr>
					<th class="text-center">작성자</th>
					<td class="text-center">${ndto.nWriter}</td>
				</tr>
				<tr>
					<th class="text-center">공지일자</th>
					<td class="text-center"><fmt:formatDate value="${ndto.nDate}"
							pattern="yyyy년 MM월 dd일" /></td>
				</tr>
				<tr>
					<th class="text-center">조회수</th>
					<td class="text-center">${ndto.nCnt}</td>
				</tr>
				<tr>
					<th class="text-center">공지내용</th>
					<td class="text-center">
						<textarea name="nContent" id="nContent" required="required" rows="10" cols="50">${ndto.nContent}</textarea>
					</td>
				</tr>
			</tbody>
			<tr>
				<td colspan="2">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<input type="submit" class="btn btn-outline-primary" value="수정">
						<a class="btn btn-outline-danger" href="/notice/delete?nNo=${ndto.nNo}">삭제</a>
					</sec:authorize>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>