$(function(){
	$(document).on("change", "#order", function() {
		$("#searchBtn").click();
	});
	$(document).on("click", "#searchBtn", function(e) {
		$("#searchBtn").prop("searchBtn", true);
		let formData = new FormData(document.forms[1]);
		let requrl = $("#basePath").val()+"/plan/placeList";
		console.log(document.forms[1].id);
		console.log(requrl);
		$.ajax({
			url: $("#basePath").val()+"/plan/placeList",
			processData: false,
			contentType: false,
			data: formData,
			type: "GET",
			dataType: "json",
			success: function(result) {
				console.log("success");
				console.log(result);
			},
			error: function() {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			complete: function() {
				$("#uploadBtn").prop("disabled", false);
			}
		});
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		$("#searchForm").type.value = $(this).text();
		$("#searchBtn").click();
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
	$("#backBtn").click(function(){
		$(location).attr("href","../plan/plan");
	});

	var idval =$('#planCate');
	$('#plancate').change(function(){
		var element =$(this).find('option:selected');
		var myTag = element.attr('value');
		idval.val(myTag);
	});
	
	var placeCount = 0;
	$(".fa-plus").click(function(){
		if (placeCount >= 10) {
			alert("일정장소는 최대 10곳까지 가능합니다");
		}
		placeCount++;
	});
	
	
	var mapContainer = document.getElementById('map');
	mapOption = {
		center: new kakao.maps.LatLng(37.5009565732326, 126.884660819027),
		level : 7
	};
	
	// 지도를 생성
	var map = new kakao.maps.Map(mapContainer, mapOption);
	var geocoder = new kakao.maps.services.Geocoder();
	
	function makePageList(placePage) {
		console.log(placePage);
	}
});