$(document).ready(function() {
	$(document).on("change", "#order", function() {
		document.forms[0].submit();
	});
	$(document).on("click", "#searchBtn", function() {
		document.forms[0].submit();
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		document.forms[0].type.value = $(this).text();
		document.forms[0].submit();
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
});