<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style type="text/css">
	a {
		float: right;
	}
	
	.btn-outline-danger {
		margin-left: 5px;
	}
	
	.btn-outline-secondary {
		margin-left: 5px;
	}
</style>
</head>
<body>
	<table class="table table-borderless">
		<tr>
			<td colspan="2">
				<a class="btn btn-outline-secondary" href="/notice/list">목록</a>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a class="btn btn-outline-danger" href="/notice/delete?nNo=${ndto.nNo}">삭제</a>
					<a class="btn btn-outline-primary" href="/notice/modify?nNo=${ndto.nNo}">수정</a>
				</sec:authorize>
			</td>
		</tr>
		<tbody>
			<tr>
				<th class="text-center">공지번호</th>
				<td class="text-center">${ndto.nNo}</td>
			</tr>
			<tr>
				<th class="text-center">공지제목</th>
				<td class="text-center">${ndto.nTitle}</td>
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
				<td class="text-center"><pre>${ndto.nContent}</pre></td>
			</tr>
		</tbody>
	</table>
	<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
		<form action="${basePath}/notice/replyProc" method="post">
			<div class="row">
				<div class="col-sm-10">
					<textarea id="nrContent" class="form-control" rows="3" placeholder="댓글을 입력해 주세요"></textarea>
				</div>
				<div class="col-sm-2">
					<input type="hidden" id="nNo" value="${ndto.nNo}">
					<input type="submit" class="btn btn-sm btn-primary" id="btnReplySave" value="댓글 작성">
				</div>
			</div>
		</form>
	</div>
	<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
		<h6 class="border-bottom pb-2 mb-0">Reply list</h6>
		<div id="replyList"></div>
	</div> 
</body>
</html>