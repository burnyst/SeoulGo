<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:path>
<c:set var="defaultImage" value="${imagePath}/place/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/place" />
</t:path>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소입력</title>
<script type="text/javascript" src="${resourcePath}/js/place/insert.js"></script>
</head>
<body>
<input id="pageUri" type="hidden" value="${pageUri}" />
<div class="p-3">
	<h4>장소 입력</h4>
</div>
<div class="p-3">
	<form method="post" class="form" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th>장소명</th>
				<td>
					<input name="placeName" class="form-control" type="text" />
				</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>
					<select name="cate" class="form-control">
						<option value="0">즐길거리</option>
						<option value="1">음식점</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input name="addr1" class="form-control" type="text" />
				</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>
					<input name="addr2" class="form-control" type="text" />
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					<input name="tel" class="form-control" type="text" />
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<div class="image">
						<div class="input-group">
							<input name="files" class="form-control form-control-file border" type="file" accept="image/*" />
							<div class="input-group-append">
								<button class="btn btn-primary add" type="button"><i class="fas fa-plus"></i></button>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr class="text-center">
				<td colspan="2">
					<button id="uploadBtn" type="button" class="btn btn-primary m-2"><span class="spinner-border spinner-border-sm"></span> 확인</button>
					<button id="cancelBtn" type="button" class="btn btn-secondary m-2">취소</button>
					<button id="backBtn" type="button" class="btn btn-secondary m-2">이전</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>