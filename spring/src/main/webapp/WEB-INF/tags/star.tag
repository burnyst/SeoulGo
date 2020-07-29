<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="score" required="true" type="java.lang.Integer" %>
<div class="overlap-bg">
	<i class="far fa-star text-secondary"></i>
	<i class="far fa-star text-secondary"></i>
	<i class="far fa-star text-secondary"></i>
	<i class="far fa-star text-secondary"></i>
	<i class="far fa-star text-secondary"></i>
	<span class="overlap-fg w-${score}">
		<i class="fa fa-star text-danger"></i>
		<i class="fa fa-star text-danger"></i>
		<i class="fa fa-star text-danger"></i>
		<i class="fa fa-star text-danger"></i>
		<i class="fa fa-star text-danger"></i>
	</span>
</div>