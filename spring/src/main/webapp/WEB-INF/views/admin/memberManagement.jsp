<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SeoulGo 회원관리</title>
</head>
<body>
	<form>
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>생년월일</th>
					<th>성별</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>회원 강퇴</th>
				</tr>
				<c:choose>
					<c:when test="${empty memberList}">
						<tr>
							<td colspan="8" align="center">데이터가 없습니다</td>
						</tr>
					</c:when>
					<c:when test="${not empty memberList}">
						<c:forEach items="${memberList}" var="list">
							<tr>
								<td>${list.memberID}</td>
								<td>${list.mName}</td>
								<td>${list.nickname}</td>
								<td>${list.birth}</td>
								<td>${list.gender}</td>
								<td>${list.email}</td>
								<td>${list.phone1}-${list.phone2}-${list.phone3}</td>
								<td><input type="checkbox"></td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</form>
</body>
</html>