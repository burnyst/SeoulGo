<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<style type="text/css">
	#fail {
		color: red;
		font-weight: bold;
		font-size: x-large;
		align: center;
		margin: auto;
	}
	
	#result {
		font-weight: bold;
		font-size: x-large;
		align: center;
		margin: auto;
	}
	
	#enrollDate {
		font-size: xx-small;
	}
</style>
</head>
<body>
	<form id="searchIDForm" action="${basePath}/user/login" method="post">
		<table class="table table-borderless">
			<thead>
				<c:if test="${msg == 'fail'}">
					<tr class="text-center">
						<th id="fail" colspan="2"><i class="fas fa-exclamation-circle "></i></th>
					</tr>
				</c:if>
				<tr class="text-center">
					<th id="result" colspan="2">아이디 찾기 결과입니다.</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${msg == 'fail'}">
					<tr class="text-center">
						<td id="fail" colspan="2">입력한 정보와 일치하는 아이디가 존재하지 않습니다.</td>
					</tr>
				</c:if>	
				<tr>
					<td class="text-center">
						<input type="radio" checked="checked"/>
						${result.memberID}&nbsp;&nbsp;
						<a id="enrollDate">(가입일: <fmt:formatDate pattern="yyyy/MM/dd" value="${result.enrollDate}"/>)</a>
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<input type="submit" id="searchIDProc" class="btn btn-primary btn-sm" value="로그인"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>