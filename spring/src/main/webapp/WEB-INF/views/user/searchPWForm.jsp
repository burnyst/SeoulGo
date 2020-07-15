<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/user/js/searchPW.js"></script>
</head>
<body>
	<h3>비밀번호 찾기</h3>
	<br>
	
	<form id="searchPWForm" class="" action="/user/emailAuth" method="post">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" placeholder="아이디를 입력해주세요" required="required"/>
						<input type="button" id="memberIDChk" value="확인"/>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="mName" id="mName" placeholder="이름을 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="email" id="email" placeholder="이메일을 입력해주세요" required="required"/><br>
						
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" class="btn btn-outline-secondary btn-sm" id="emailBtn" value="인증번호 전송"/>
						<input type="reset" id="" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>