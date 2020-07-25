<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="libPath" value="${resourcePath}/lib" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${basePath}/resources/js/notice/noticeReplyList.js"></script>
<script src="${basePath}/resources/js/notice/delete.js"></script>
<style type="text/css">
	a {
		float: right;
		font-size: small;
	}
	
	.btn-danger {
		margin: 0 5px;
	}
	
	hr {
		border-style: dashed;
	}

	h5 {
		/* margin-block-start: 1em; */
		margin-block-end: 0.5em;
	}
	
	small {
	  	font-size: small;
	}
</style>
</head>
<body>
	<div class="row float-right" style="margin-bottom: 10px;">
		<a class="btn btn-sm btn-secondary" href="/notice/list">목록</a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a class="btn btn-sm btn-danger text-white" id="deleteBtn">삭제</a>
			<a class="btn btn-sm btn-primary" href="/notice/modify?nNo=${ndto.nNo}">수정</a>
		</sec:authorize>
	</div>
	<table class="table table-striped">
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
				<td class="text-center">
					<c:if test="${'ROLE_ADMIN' eq mLevel}">
						<c:out value="관리자"></c:out>
					</c:if>
				</td>
			</tr>
			<tr>
				<th class="text-center">공지일자</th>
				<td class="text-center"><fmt:formatDate value="${ndto.nDate}" pattern="yyyy년 MM월 dd일"/></td>
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
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">New Reply</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="replyFrm" action="${basePath}/notice/replyProc?nNo=${ndto.nNo}" method="post">
						<textarea id="nrContent" name="nrContent" class="form-control" rows="3" placeholder="댓글을 입력해 주세요"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="nNo" value="${ndto.nNo}">
					<input type="submit" class="btn btn-sm btn-primary" id="btnReply" value="댓글 작성">
					<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade reReply" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">New Reply44</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="replyForm" name="replyForm" method="post">
						<textarea id="nrContent2" name="nrContent2" class="form-control" rows="3" placeholder="댓글의 답글을 입력해 주세요"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="nNo" value="${ndto.nNo}">
					<input type="submit" class="btn btn-sm btn-primary" id="btnReply2" name="btnReply2" value="댓글 작성">
					<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="my-3 p-3 bg-white rounded shadow-sm">
		<h6 class="border-bottom pb-2 mb-0" style="height: 40px;"><i class="fa fa-comments fa-fw"></i> Reply list
			<sec:authorize access="isAuthenticated()">
				<button type="button" style="float: right;" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal">New Reply</button>
			</sec:authorize>
		</h6>
		<div class="comment_list"></div>
	</div> 
</body>
</html>