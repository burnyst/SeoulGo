<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="${pageContext.request.contextPath}/js/register_info_check.js"></script>
</head>
<body>
	<form id="registerForm" class="" action="../registerProc" method="post">
		<table align="center">
			<tbody>
				<tr>
					<th>프로필 사진</th>
					<td>
						<input type="file" name="img" id="img"/>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" id="id" placeholder="Username" required="required"/>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="pw" id="pw" placeholder="Password" required="required"/>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="pw" id="pw" aria-describedby="pw" required="required"/>
						<p id="pw">비밀번호는 영어, 숫자, 특수문자를 합쳐 12자리 이상이여야 합니다.</p>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" id="name" required="required"/>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="date" id="mbd" name="mbd" value="${param.mbd}">
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<select name="gender" id="gender">
	                           <option value="">성별</option>
	                           <option value="M">남자</option>
	                          <option value="F">여자</option>
                       	</select>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" id="email" name="email" placeholder="Email" required="required"/>
					</td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td>
						<input type="text" id="phone" name="phone" placeholder="'-'없이 번호만 입력해주세요" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="" value="로그인"/>
						<input type="reset" id="" value="취소"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>