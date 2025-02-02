$(function(){
	 $("#nomodi").click(function(){
		 $(location).attr("href","../plan/plan");
	 })
	 var planno = $('#planNo').val();
	 $("#planmodi").click(function(){
		 //alert("수정페이지로 이동합니다.");
		 $(location).attr("href","../plan/planmodi?&planNo="+planno);
	 })
	 $("#back").click(function(){
		 //alert("일정공유 게시판으로 이동합니다.");
		 $(location).attr("href","../plan/planSboard");
	 })
	 
	 $("#bla").click(function(){
		 alert("이전페이지로 이동합니다.")
		 $(location).attr("href","./planSboard")
	 })
	 $("#delbtn").click(function(){
		 var result = confirm('정말로 일정을 삭제하시겠습니까?');
		 if(result){
			 $(location).attr("href","../plan/plandeleteok?&planNo="+planno);
		 }else{
			 return false;
		 }
	 })

	 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng( 37.576004, 126.976902 ), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
		
	var addr1 = $('#addr1').val();
	var addr2 = $('#addr2').val();
	var placeName = $('#placeName').val();
	var addr = addr1 + addr2
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(addr, function(result, status) {

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
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+placeName+'</div>'
	        });
	        
	        infowindow.open(map, marker);
	        
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	        var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	        iwRemoveable = true;
	    }
	}); 
});