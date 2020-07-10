<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정공유 게시판</title>
</head>
<body>
<h1>일정공유 게시판</h1>
	<table border="1" width="100%">
		<tr>
			<th>글번호</th>
			<th>아이디</th>
			<th>일정제목</th>
			<th>일정유형</th>
		</tr>
		<c:if test="">
			<tr>
				<td>
					<a href="#">${""}</a>
				</td>
				<td>
					<a href="#">${""}</a>
				</td>
				<td>
					<a href="#">${""}</a>
				</td>
				<td>
					<a href="#">${""}</a>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>