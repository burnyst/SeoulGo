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
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>
			</td>
			<th>아이디</th>
			<td>	
			</td>
			<th>일정제목</th>
			<td>
			</td>
			<th>일정내용</th>
			<td>
			</td>
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