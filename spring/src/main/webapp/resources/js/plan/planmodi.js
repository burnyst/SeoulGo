$(function(){
 $("#nomodi").click(function(){
	 //alert("함수 작동");
	 $(location).attr("href","../plan/plan");
	
 });
 
 $("#delmodi").click(function(){
	 alert("삭제페이지로 이동합니다.");
	 $('#frm').attr("method", "get");
	 $('#frm').attr("action","../plan/plandelete").submit();
	
 });
 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
 mapOption = {
     center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
     level: 3 // 지도의 확대 레벨
 };  
//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
var addr1 = $('#addr1').val();
var addr2 = $('#addr2').val();
var placename = $('#planTitle').val();
var addr = addr1 + addr2
alert(addr);
//주소로 좌표를 검색합니다


geocoder.addressSearch( addr , function(result, status) {
	//zero_result가 나오면 
	if(status === kakao.maps.services.Status.ZERO_RESULT){
		alert("아직 지원되지 않는 주소입니다.");
	}
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
         content: '<div style="width:150px;text-align:center;padding:6px 0;">'+placename+'</div>'
     });
     
     infowindow.open(map, marker);

     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
     map.setCenter(coords);
     var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
     iwRemoveable = true;
 } 
});    
	 
	 
});