<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="libPath" value="${resourcePath}/lib" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="${libPath}/jquery/3.5.1/jquery.min.js"></script>
<script src="${basePath}/resources/js/user/register_info_check.js"></script>
<style>
	table {
		margin: 0 auto;
	}
	
	.filebox label {
		display: inline-block;
		padding: .5em .75em;
		color: #999;
		font-size: inherit;
		line-height: normal;
		vertical-align: middle;
		background-color: #fdfdfd;
		cursor: pointer;
		border: 1px solid #ebebeb;
		border-bottom-color: #e2e2e2;
		border-radius: .25em;
	}
	
	.filebox input[type="file"] { /* 파일 필드 숨기기 */
		position: absolute;
		width: 1px;
		height: 1px;
		padding: 0;
		margin: -1px;
		overflow: hidden;
		clip: rect(0, 0, 0, 0);
		border: 0;
	}
</style>
</head>
<body>
	<form id="registerForm" action="${basePath}/user/registerProc" method="post" enctype="multipart/form-data">
		<table class="table table-hover">
			<tbody>
				<tr>
					<th class="text-center" width="55%">프로필 사진</th>
					<td>
						<div class="filebox">
							<label for="files">업로드</label>
							<input type="file" name="files" id="files" accept=".jpg, .jpeg, .gif, .png, .bmp"/>
						</div>
						<div class="profile"><img class="rounded-circle" src=""></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">아이디</th>
					<td>
						<input type="text" name="memberID" id="memberID" placeholder="Username" required="required"/>
						<div class="check_font" id="id_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">비밀번호</th>
					<td>
						<input type="password" name="memberPW" id="memberPW" placeholder="Password" required="required"/>
						<div class="check_font" id="pw_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">비밀번호 확인</th>
					<td>
						<input type="password" name="memberPW2" id="memberPW2" placeholder="Password" required="required"/>
						<div class="check_font" id="pw2_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">이름</th>
					<td>
						<input type="text" name="mName" id="mName" placeholder="Name" required="required"/>
						<div class="check_font" id="name_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">닉네임</th>
					<td>
						<input type="text" name="nickname" id="nickname" placeholder="Nickname" required="required"/>
						<div class="check_font" id="nickname_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">생년월일</th>
					<td>
						<input type="date" id="birth" name="birth" value="${param.birth}" required="required"/>
					</td>
				</tr>
				<tr>
					<th class="text-center">성별</th>
					<td>
						<select name="gender" id="gender" required="required">
							<option value="">성별</option>
							<option value="M">남자</option>
							<option value="F">여자</option>
                       	</select>
                       	<div class="check_font" id="gender_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">이메일</th>
					<td>
						<input type="email" id="email" name="email" placeholder="Email" required="required"/>
						<div class="check_font" id="email_check"></div>
					</td>
				</tr>
				<tr>
					<th class="text-center">휴대전화</th>
					<td>
						<input type="text" id="phone" name="phone" placeholder="'-'없이 번호만 입력해주세요" required="required"/>
						<div class="check_font" id="phone_check"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="text-center">
			<input class="btn btn-primary btn-sm" type="submit" id="reg_submit" value="회원가입"/>
			<input class="btn btn-secondary btn-sm" type="reset" id="cancelBtn" value="취소"/>
		</div>
	</form>
</body>
</html>