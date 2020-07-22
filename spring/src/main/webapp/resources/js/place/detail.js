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
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};
	var map = new kakao.maps.Map(container, options);
	
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('${item.addr2}', function(result, status) {
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">${item.placeName}</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    }
	});
});