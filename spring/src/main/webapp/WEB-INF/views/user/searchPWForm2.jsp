<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/user/js/searchPW2.js"></script>
</head>
<body>
	<h3>비밀번호 찾기</h3>
	<br>
	
	<form id="searchPWForm" class="" action="/user/searchPWProc" method="post">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" value="${result.memberID}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="mName" id="mName" value="${result.mName}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="email" id="email" value="${result.email}" readonly="readonly"/><br>
					</td>
				</tr>
				<tr>
					<th>인증번호</th>
					<td>
						<input type="text" name="emailNum" id="emailNum" placeholder="인증번호를 입력해주세요" required="required" autofocus="autofocus"/>
						<input type="button" name="confirmChk" id="confirmChk" class="btn btn-outline-secondary btn-sm" value="인증번호 확인"/>
						<input type="hidden" name="confirmNum" id="confirmNum" value="${confirmNum}"/>
					</td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td>
						<input type="password" name="memberPW" id="memberPW" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td>
						<input type="password" name="memberPW2" id="memberPW2" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="searchPWProc" value="비밀번호 재설정"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>