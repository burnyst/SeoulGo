<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="resourcePath" value="${basePath}/resources" scope="request" />
<c:set var="libPath" value="${resourcePath}/lib" scope="request" />
<c:set var="imagePath" value="${resourcePath}/img" scope="request" />
<jsp:doBody></jsp:doBody>