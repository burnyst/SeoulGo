$(function(){
	var cnt = 1;
	 
	$(".fa-plus-square").click(function(){
		cnt++;
		if(cnt==11) {
			alert("일정장소는 최대 10곳까지 가능합니다");
			cnt=10;
			return;
		}
		
		var div = '<div class="form-group"><input type="text" id="planplace'+cnt+'" name="planplace"';
		div += ' required="required"></div>';
				
		$("#copy").before(div);
	});
	
	$(".fa-minus-square").click(function(){
		if(cnt==1) {
			alert("일정장소는 최소 1곳  있어야 합니다");
			return;
		}

		var div = $("#planplace"+cnt);
		div.remove();
		 
		cnt--;
	});
	
	$("#btnSearch").click(function(){
		var keyword = $("#keyword").val();
		var searchType = $("#searchType").val();
		
		if(keyword != null && keyword != '' && searchType != null && searchType != '') {
			var url = $("#basePath").val()+"/plan/planwrite";
			url = url + "?pageNo=1&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
			location.href = url;
			console.log(url);
		}else {
			alert("검색유형 혹은 검색어가 입력되지 않았습니다.");
		}
	})
	
	// 지도를 표시할 div
	var mapContainer = document.getElementById('map');
	mapOption = {
			// 지도의 중심좌표
			center : new kakao.maps.LatLng(33.450701, 126.570667),
			// 지도의 확대 레벨
			level : 7
	};
	
	// 지도를 생성
	var map = new kakao.maps.Map(mapContainer, mapOption);
	var geocoder = new kakao.maps.services.Geocoder();
	var addrArr = [];
	
	var btnArr = document.getElementsByClassName("btn-outline-info");
	var nameArr = document.getElementsByName("placeName");
	var noArr = document.getElementsByName("placeNo");
	var addr1Arr = document.getElementsByName("addr1");
	var addr2Arr = document.getElementsByName("addr2");
	var planplace = document.getElementsByName("planplace");
	var j = 1;
	//alert("placeName="+placeName+", placeNo="+placeNo+", addr1="+addr1+", addr2="+addr2)
	//alert("arr 몇개니? "+arr.length)
	
	for(let i=0; i<btnArr.length; i++) {
		$(btnArr[i]).click(function(){
			//alert(nameArr[i].value)
			$("#planplace"+j).attr("value", nameArr[i].value);
			j++;
			addrArr.push({
				'groupAddress' : addr1Arr[i].value + " " + addr2Arr[i].value
			})
		})
	}
	
	for(var i=0; i<addrArr.length; i++) {
		geocoder.addressSearch(addrArr[i].groupAddress, function(result, status, data){
			
			if(status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				
				// 결과값으로 받은 위치를 마커로 표시
				var marker = new kakao.maps.Marker({
					map: map,
					position: coords
				});
				
				// 마커를 지도에 표시
				maker.setMap(map);
				
				var content = '<div class="labelWish"><span class="leftWish"></span><span class="centerWish">' + result[0].address_name
				+ '</span><span class="rightWish">'
			}
		})
	}
});