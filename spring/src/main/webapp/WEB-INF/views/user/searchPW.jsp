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
	<form id="searchPWForm" action="/user/searchPWProc" method="post">
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th colspan="2" class="text-center">비밀번호 찾기</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="text-center" width="40%">아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" placeholder="아이디를 입력해주세요" required="required"/>
						<input type="button" id="memberIDChk" class="btn btn-outline-secondary btn-sm" value="확인"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">이름</th>
					<td>
						<input type="text" name="mName" id="mName" placeholder="이름을 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">이메일</th>
					<td>
						<input type="text" name="email" id="email" placeholder="이메일을 입력해주세요" required="required"/>
						<input type="button" class="btn btn-outline-secondary btn-sm" id="emailBtn" value="인증번호 전송"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">인증번호</th>
					<td>
						<input type="text" name="emailNum" id="emailNum" placeholder="인증번호를 입력해주세요" required="required"/>
						<input type="button" name="confirmChk" id="confirmChk" class="btn btn-outline-secondary btn-sm" value="인증번호 확인"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">새 비밀번호</th>
					<td>
						<input type="password" name="memberPW" id="memberPW" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">새 비밀번호 확인</th>
					<td>
						<input type="password" name="memberPW2" id="memberPW2" placeholder="새 비밀번호를 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" id="searchPWProc" class="btn btn-primary btn-sm" value="비밀번호 재설정"/>
						<input type="reset" id="cancelBtn" class="btn btn-secondary btn-sm" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>