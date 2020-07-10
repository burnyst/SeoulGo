<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" 
    	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" 
    	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/user/js/register_info_check.js"></script>
</head>
<body>

	<form id="registerForm" class="" action="../user/registerProc" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<th>프로필 사진</th>
					<td>
						<input type="file" name="files" id="files" accept=".jpg, .jpeg, .gif, .png, .bmp"/>
						<c:if test="${tImg != null}">
							<img src="${pageContext.request.contextPath}/upload/temp/${tImg}">
						</c:if>
						<input type="hidden" name="proSaveName" value="" id="profile-file">
						<div id="preview"></div>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" placeholder="Username" required="required"/>
						<div class="check_font" id="id_check"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="memberPW" id="memberPW" placeholder="Password" required="required"/>
						<div class="check_font" id="pw_check"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="memberPW2" id="memberPW2" placeholder="Password" required="required"/>
						<div class="check_font" id="pw2_check"></div>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="mName" id="mName" required="required"/>
						<div class="check_font" id="name_check"></div>
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="nickname" id="nickname" required="required"/>
						<div class="check_font" id="nickname_check"></div>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="date" id="birth" name="birth" value="${param.birth}" required="required"/>
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
                       	<div class="check_font" id="gender_check"></div>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" id="email" name="email" placeholder="Email" required="required"/>
						<div class="check_font" id="email_check"></div>
					</td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td>
						<input type="text" id="phone" name="phone" placeholder="'-'없이 번호만 입력해주세요" required="required"/>
						<div class="check_font" id="phone_check"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="reg_submit" value="회원가입"></input>
						<input type="reset" id="reg_cancel" value="취소"></input>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>