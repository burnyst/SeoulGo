<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ attribute name="imagePath" type="java.lang.String" required="true" %>
<%@ attribute name="defaultImage" type="java.lang.String" required="true" %>
<%@ attribute name="detailUri" type="java.lang.String" required="true" %>
<c:forEach var="item" items="${list}" varStatus="status">
	<div class="card col-md-6 col-lg-4">
		<img class="card-img-top img-fluid" src="${imagePath}/${item.imageNames[0]}" onerror="this.src='${defaultImage}'" alt="place">
		<div class="card-body">
			<h4 class="card-title">${item.placeName}</h4>
			<span class="card-text">
				리뷰: ${item.reviewCount}<br />
				평점: <t:star score="${item.placeRate20X}"></t:star><br />
				주소: ${item.addr1}<br />
				${item.addr2}
			</span>
			<a href="${detailUri}?placeNo=${item.placeNo}" class="stretched-link"></a>
		</div>
	</div>
</c:forEach>