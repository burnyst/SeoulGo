$(document).ready(function() {
	$(document).on("change", "#order", function() {
		$("#searchBtn").click();
	});
	$(document).on("click", "#searchBtn", function() {
		$("#searchForm").submit();
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		$("#searchForm input[name='type']").val($(this).text());
		$("#searchForm").submit();
		return false;
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
});