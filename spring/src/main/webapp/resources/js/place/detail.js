$(document).ready(function() {
	$(document).on("click", "#backBtn", function() {
		history.back();
	});
	$(document).on("click", "#updateBtn", function() {
		location.href = $("#pagePath").val()+"/update?placeNo="+$("#placeNo").val();
	});
	$(document).on("click", "#deleteBtn", function() {
		var formData = new FormData($("form")[0]);
		$.ajax({
			url: $("#pagePath").val()+"/delete?placeNo="+$("#placeNo").val(),
			processData: false,
			contentType: false,
			data: formData,
			type: "POST",
			dataType: "json",
			success: function(result) {
				if (result) {
					alert("삭제 완료");
					history.back();
				} else {
					alert("입력 실패");
				}
			},
			error: function() {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
	$(document).on("click", "#writeBtn", function() {
		location.href = "../review/writeReview?placeNo="+$("#placeNo").val();
	});
	document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	$(window).resize(function () {
		document.documentElement.style.setProperty('--scrollbar-width', (window.innerWidth - document.documentElement.clientWidth) + "px");
	});
	let morememberID = document.getElementsByName("morememberID");
	let list = [];	
	for (let i=0; i<morememberID.length; i++){
		list[i] = morememberID[i].value
		$("#moreView"+morememberID[i].value).click(function(){
			location.href = "../review/detailView?pageNo=1&memberID="+list[i];
		});
	}

	var mapContainer = document.getElementById('map');
	var mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
    };
	var map = new kakao.maps.Map(mapContainer, mapOption);
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
	        map.setCenter(new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude));
		});
	}
	map.setZoomable(false);
	map.addControl(new kakao.maps.MapTypeControl(), kakao.maps.ControlPosition.TOPRIGHT);
	map.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.RIGHT);
	var geocoder = new kakao.maps.services.Geocoder();
	geocoder.addressSearch($("#addr2").val(), function(result, status) {
	     if (status === kakao.maps.services.Status.OK) {
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	        marker.setMap(map);
	        
	        var content = '<div class ="bg-light p-1 border border-secondary rounded text-dark"><span class="left"></span><span class="center">'+$("#placeName").val()+'</span><span class="right"></span></div>';
	        var customOverlay = new kakao.maps.CustomOverlay({
	        	content: content, 
	        	map: map,
	            position: marker.getPosition(),
	            yAnchor: 2.4
	        });
	        customOverlay.setMap(map);
	        
	        map.setCenter(coords);
	    } else {
	    	coords = null;
	    }
	});
});

