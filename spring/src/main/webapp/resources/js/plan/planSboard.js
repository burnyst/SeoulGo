$(function(){
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	//마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

	//마커를 생성합니다
	var marker = new kakao.maps.Marker({
	position: markerPosition
	});

	//마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map); 
	
});
$( document ).ready(function() {
	
	$('td').each(function(){
		var texts = $(this).text();
		if(texts.includes('\[')){
			var texts = texts.replace(/\[/g,'');
			$(this).text(texts);
		}
		if(texts.includes('\]')){
			var texts = texts.replace(/\]/g,'');
			$(this).text(texts);
		
		}
		if(texts.includes('\,')){
			var texts = texts.replace(/\,/g,'->');
			$(this).text(texts);
		}
	})
});
