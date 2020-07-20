<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageNo" required="true" type="java.lang.Integer" %>
<%@ attribute name="startPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="endPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="totalPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="uri" required="true" type="java.lang.String" %>
<%@ attribute name="params" required="false" type="java.lang.String" %>
<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${pageNo > 1}">
			<li class="page-item"><a class="page-link" href="${uri}?pageNo=${pageNo-1}${params}">Prev</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled"><a class="page-link" href="#">Prev</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${i == pageNo}">
				<li class="page-item active">
					<a class="page-link" href="${uri}?pageNo=${i}${params}">${i}</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${uri}?pageNo=${i}${params}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageNo < totalPage}">
			<li class="page-item"><a class="page-link" href="${uri}?pageNo=${pageNo+1}${params}">Next</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
		</c:otherwise>
	</c:choose>
</ul>