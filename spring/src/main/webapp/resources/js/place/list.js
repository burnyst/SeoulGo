$(document).ready(function() {
	$(document).on("change", "#order", function() {
		$("#searchBtn").click();
	});
	$(document).on("click", "#searchBtn", function() {
		$("#searchForm").submit();
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		alert($("#searchForm").type.value);
		$("#searchForm").type.value = $(this).text();
		$("#searchForm").submit();
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
});