<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${basePath}/resources" />
<c:set var="imagePath" value="${resourcePath}/img/place" />
<c:set var="defaultImage" value="${imagePath}/noimage.jpg" />
<c:set var="pagePath" value="${basePath}/place" />
<c:set var="pageUri" value="${pagePath}/update" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소수정</title>
<style type="text/css">
th {
	width:8rem;
}
</style>
<script type="text/javascript" src="${resourcePath}/js/place/update.js"></script>
</head>
<body>
<input id="pageUri" type="hidden" value="${pageUri}" />
<div class="p-3">
	<h4>장소 수정</h4>
</div>
<div class="p-3">
	<form method="post" class="form" enctype="multipart/form-data">
		<input id="placeNo" type="hidden" value="${item.placeNo}" />
		<table class="table">
			<tr>
				<th>장소명</th>
				<td>
					<input name="placeName" class="form-control" type="text" value="${item.placeName}" />
				</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>
					<select name="cate" class="form-control">
						<c:choose>
							<c:when test="${item.cate == 0}">
								<option value="0" selected>즐길거리</option>
								<option value="1">음식점</option>
							</c:when>
							<c:otherwise>
								<option value="0">즐길거리</option>
								<option value="1" selected>음식점</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input name="addr1" class="form-control" type="text" value="${item.addr1}" />
				</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>
					<input name="addr2" class="form-control" type="text" value="${item.addr2}" />
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					<input name="tel" class="form-control" type="text" value="${item.tel}" />
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<c:set var="imageCnt" value="${fn:length(item.imageNames)}" />
					<c:forEach var="item" items="${item.imageNames}">
						<div class="image">
							<button class="btn btn-danger hide" type="button"><i class="fas fa-minus"></i></button>
							<img class="img-fluid img-thumbnail" src="${imagePath}/${item}" onerror="this.src='${defaultImage}'" alt="uploaded image" />
							<input name="uploadedImageNames" class="imageNames" type="hidden" value="${item}" />
						</div>
					</c:forEach>
					<c:if test="${imageCnt < 10}">
						<div class="image">
							<div class="input-group">
								<input name="files" class="form-control form-control-file border" type="file" accept="image/*" value="${defaultImage}" />
								<div class="input-group-append">
									<button class="btn btn-primary add" type="button"><i class="fas fa-plus"></i></button>
								</div>
							</div>
						</div>
					</c:if>
					<!--
					<div class="image">
						<c:choose>
							<c:when test="${imageCnt > 0}">
								<div class="input-group">
									<input name="files" class="form-control form-control-file border" type="file" accept="image/*" value="${imagePath}/${item.imageNames[0]}" />
									<div class="input-group-append">
										<button class="btn btn-primary add" type="button"><i class="fas fa-plus"></i></button>
									</div>
								</div>
								<input name="imageNames" type="hidden" value="${item.imageNames[0]}" />
								<img class="img-fluid img-thumbnail" src="${imagePath}/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="preview" />
							</c:when>
							<c:otherwise>
								<div class="input-group">
									<input name="files" class="form-control form-control-file border" type="file" accept="image/*" value="${defaultImage}" />
									<div class="input-group-append">
										<button class="btn btn-primary add" type="button"><i class="fas fa-plus"></i></button>
									</div>
								</div>
								<img class="img-fluid img-thumbnail" src="${defaultImage}" alt="preview" />
							</c:otherwise>
						</c:choose>
					</div>
					<c:if test="${imageCnt > 0}">
						<c:forEach var="i" begin="1" end="${imageCnt-1}">
							<div class="image">
								<div class="input-group">
									<input name="files" class="form-control form-control-file border" type="file" accept="image/*" value="${imagePath}/${item.imageNames[i]}" />
									<div class="input-group-append">
										<button class="btn btn-danger del" type="button"><i class="fas fa-minus"></i></button>
									</div>
								</div>
								<img class="img-fluid img-thumbnail" src="${imagePath}/${item.imageNames[i]}" onerror="this.src='${defaultImage}'" alt="preview" />
							</div>
						</c:forEach>
					</c:if>
					-->
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