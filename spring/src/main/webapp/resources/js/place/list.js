$(document).ready(function() {
	$(document).on("click", "#searchBtn", function() {
		$("#searchForm").submit();
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		$('#searchForm input[name="pageNo"]').val(1);
		$('#searchForm input[name="type"]').val($(this).text());
		$("#searchBtn").click();
		return false;
	});
	$(document).on("change", "#order", function() {
		$('#searchForm input[name="order"]').val($(this).text());
		$("#searchBtn").click();
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
});