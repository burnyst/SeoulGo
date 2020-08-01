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
	var pNo = document.getElementsByName("pNo");
	var list = new Array();   
	var nameList = new Array();
	   
	for(let i=0; i<pNo.length; i++){
		list[i] = parseInt(pNo[i].value);
		nameList[i] = $(".media-body").children("a[id='aName"+list[i]+"']").text();
		console.log(list[i]);
		console.log(nameList[i]);
		
		$("#pAdd"+list[i]).click(function(){
			var placeList = document.createElement("span");
			var str = nameList[i];
			if(placeCount >= 1) {
				str = ' <i class="fas fa-chevron-right"></i> ' + str
			}
			placeCount++;
			if(placeCount == 11) {
				alert("일정장소는 최대 10곳까지 가능합니다");
				placeCount = 10;
				str = "";
			}
			placeList.innerHTML=str;
			document.querySelector("div#placeName").appendChild(placeList);
			
			$.ajax({
				url: $("#basePath").val() + "/plan/planWrited",
				data: {
					placeNo: list[i],
					planTitle: $("#planTitle").val(),
					planCate: $("#plancate").val(),
					plandate: $("#plandate").val()
				},
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType: 'html',
				type: "POST",
				success: function(result) {
					console.log("success");
					console.log(result);
				},
				error: function() {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			})
			
		});
	}
	
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