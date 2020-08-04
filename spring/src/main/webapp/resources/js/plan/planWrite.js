$(document).ready(function() {
	let basePath = $("#basePath").val();
	let placeCount = 0;
	let mapContainer = document.getElementById('map');
	let markerImage = new kakao.maps.MarkerImage('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', new kakao.maps.Size(33, 36));
	let baseCoords = new kakao.maps.LatLng(33.450701, 126.570667);
	let mapOption = { 
        center: baseCoords,
        level: 3
    };
	let map = new kakao.maps.Map(mapContainer, mapOption);
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			baseCoords = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
	        map.setCenter(baseCoords);
		});
	}
	map.setZoomable(false);
	map.addControl(new kakao.maps.MapTypeControl(), kakao.maps.ControlPosition.TOPRIGHT);
	map.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.RIGHT);
	let geocoder = new kakao.maps.services.Geocoder();
	let bounds = new kakao.maps.LatLngBounds();
	let markers = [];
	function clearPlaceList() {
		placeCount = 0;
		for (i of markers) {
			i.marker.setMap(null);
			i.customOverlay.setMap(null);
		}
		markers = [];
		bounds = new kakao.maps.LatLngBounds();
		map.setCenter(baseCoords);
		sessionStorage.removeItem("placeList");
		$("#placeName").html("");
	}
	function addPlaceList(placeNo, placeName, addr) {
		if (placeCount == 10) {
			alert("일정장소는 최대 10곳까지 가능합니다");
			return;
		}
		
		let placeList = JSON.parse(sessionStorage.getItem("placeList"));
		if (placeList == null) {
			placeList = [];
		}
		let newPlaceList = [];
		for (let i of placeList) {
			if (i.placeNo != placeNo) {
				newPlaceList.push(i);
			} else {
				return;
			}
		}
		
		placeCount++;
		let tags = '<div class="border border-secondary rounded text-secondary'+placeCount+'">';
		tags += '<input type="hidden" name="placeNo" value="'+placeNo+'">';
		tags += '<input type="hidden" name="placeName" value="'+placeName+'">';
		tags += '<input type="hidden" name="addr" value="'+addr+'">';
		tags += '&nbsp&nbsp'+placeName;
		tags += '<button type="button" class="del btn btn-sm btn-outline-light text-secondary" id="delBtn'+placeCount+'" style="border: 0; outline: 0;">'
		tags += '<span class="text-secondary">×</span></button></div>';
		$("#placeName").append(tags);
		
		newPlaceList.push({placeNo: placeNo, placeName: placeName, addr: addr});
		sessionStorage.setItem("placeList", JSON.stringify(newPlaceList));
		geocoder.addressSearch(addr, function(result, status) {
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        var marker = new kakao.maps.Marker({
		            map: map,
		            title: placeName,
		            position: coords
		        });
		        marker.setMap(map);
		        
		        var content = '<div class ="bg-light p-1 border border-secondary rounded text-dark"><span class="left"></span><span class="center">'+placeName+'</span><span class="right"></span></div>';
		        var customOverlay = new kakao.maps.CustomOverlay({
		        	content: content, 
		        	map: map,
		            position: marker.getPosition(),
		            yAnchor: 2.4
		        });
		        customOverlay.setMap(map);
		        markers.push({marker: marker, customOverlay: customOverlay});
		        bounds.extend(coords);
		        map.setBounds(bounds);
		        let proj = map.getProjection();
		        let sw = proj.pointFromCoords(bounds.getSouthWest());
		        let ne = proj.pointFromCoords(bounds.getNorthEast());
		        sw.x -= 80;
		        sw.y += 80;
		        ne.x += 80;
		        ne.y -= 80;
		        map.setBounds(new kakao.maps.LatLngBounds(proj.coordsFromPoint(sw), proj.coordsFromPoint(ne)));
		    } else {
		    	coords = null;
		    }
		});
	}
	
	$(document).on("click", "#searchBtn", function(e) {
		$("#searchBtn").prop("disabled", true);
		let formData = new FormData($("#searchForm")[0]);
		let requrl = basePath+"/plan/placeList";
		$.ajax({
			url: basePath+"/plan/placeList",
			processData: false,
			contentType: false,
			data: formData,
			type: "POST",
			dataType: "json",
			success: function(page) {
				$('#searchForm input[name="pageNo"]').val(page.pageNo);
				$('#searchForm input[name="pageNum"]').val(page.pageNum);
				$('#searchForm input[name="pageRowNum"]').val(page.pageRowNum);
				$('#searchForm input[name="type"]').val(page.type);
				$('#searchForm input[name="keyword"]').val(page.keyword);
				if (page.type == "") {
					$(".nav-tabs .nav-link").eq(0).addClass("active");
				} else {
					$(".nav-tabs .nav-link").each(function (index, item) {
						if ($(item).text() == page.type) {
							$(item).addClass("active");
						} else {
							$(item).removeClass("active");
						}
					});
				}
				$("#order option").each(function (index, item) {
					if ($(item).text() == page.order) {
						$(item).prop("selected", true);
					}
				});
				let imagePath = basePath+"/resources/img";
				let defaultImage = imagePath+"/place/noimage.jpg";
				$("#searchResult").html("");
				for (var item of page.content) {
					let tags = '<div class="media border">';
					tags += '<div class="place-image-container mr-1">';
					tags += '<img src="'+imagePath+'/place/'+item.imageNames[0]+'" onerror="this.src=\''+defaultImage+'\'" alt="place" />';
					tags += '</div>';
					
					tags += '<div class="media-body p-1">';
					tags += '<a href="'+basePath+'/place/detail?placeNo='+item.placeNo+'">'+item.placeName+'</a><br />';
					tags += '리뷰 '+item.reviewCount;
					tags += ' / 평점 <div class="overlap-bg">';
					tags += '<i class="far fa-star text-secondary"></i>';
					tags += '<i class="far fa-star text-secondary"></i>';
					tags += '<i class="far fa-star text-secondary"></i>';
					tags += '<i class="far fa-star text-secondary"></i>';
					tags += '<i class="far fa-star text-secondary"></i>';
					tags += '<span class="overlap-fg w-'+item.placeRate20X+'">';
					tags += '<i class="fa fa-star text-danger"></i>';
					tags += '<i class="fa fa-star text-danger"></i>';
					tags += '<i class="fa fa-star text-danger"></i>';
					tags += '<i class="fa fa-star text-danger"></i>';
					tags += '<i class="fa fa-star text-danger"></i>';
					tags += '</span></div><br />';
					tags += item.addr1+' '+item.addr2;
					tags += '</div>';
					tags += '<input name="searchResultPlaceNo" type="hidden" value="'+item.placeNo+'">';
					tags += '<input name="searchResultPlaceName" type="hidden" value="'+item.placeName+'">';
					tags += '<input name="searchResultAddr" type="hidden" value="'+item.addr1+' '+item.addr2+'">';
					tags += '<button class="add btn btn-outline-primary align-self-center mx-3" type="button"><i class="fas fa-plus"></i></button>';
					tags += '</div>'
					$("#searchResult").append(tags);
				}
				$("#pageNav").html("");
				let endPage = parseInt(page.endPage);
				let pageNo = parseInt(page.pageNo);
				let totalPage = parseInt(page.totalPage);
				let startPage = parseInt(page.startPage);
				let uri = basePath+"/plan/placeList"
				let params = "&keyword="+page.keyword+"&type="+page.type+"&order="+page.order;
				if (pageNo > 1) {
					$("#pageNav").append('<li class="page-item"><a class="page-link" href="'+(pageNo-1)+'">Prev</a></li>');
				} else {
					$("#pageNav").append('<li class="page-item disabled"><a class="page-link" href="'+(pageNo-1)+'">Prev</a></li>');
				}
				for (let i=startPage; i <= endPage; i++) {
					if (i == pageNo) {
						$("#pageNav").append('<li class="page-item active"><a class="page-link" href="'+i+'">'+i+'</a></li>');
					} else {
						$("#pageNav").append('<li class="page-item"><a class="page-link" href="'+i+'">'+i+'</a></li>');
					}
				}
				if (pageNo < totalPage) {
					$("#pageNav").append('<li class="page-item"><a class="page-link" href="'+(pageNo+1)+'">Next</a></li>');
				} else {
					$("#pageNav").append('<li class="page-item disabled"><a class="page-link" href="'+(pageNo+1)+'">Next</a></li>');
				}
				
				$(document).on("click", ".add", function(){
					let placeNo = $(this).parent().children('input[name="searchResultPlaceNo"]').val();
					let placeName = $(this).parent().children('input[name="searchResultPlaceName"]').val();
					let addr = $(this).parent().children('input[name="searchResultAddr"]').val();
					addPlaceList(placeNo, placeName, addr);
				});
			},
			error: function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			complete: function() {
				$("#searchBtn").prop("disabled", false);
			}
		});
	});
	$(document).on("click", ".nav-tabs .nav-link", function() {
		$('#searchForm input[name="pageNo"]').val(1);
		$("#searchForm input[name='type']").val($(this).text());
		$("#searchBtn").click();
		return false;
	});
	$(document).on("change", "#order", function() {
		$("#searchForm input[name='order']").val($(this).text());
		$("#searchBtn").click();
	});
	$(document).on("click", ".pagination .page-link", function() {
		var pageNo = $(this).prop("href").split("/").pop();
		$("#searchForm input[name='pageNo']").val(pageNo);
		$("#searchBtn").click();
		return false;
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
	
	var pNo = document.getElementsByName("pNo");
	var list = new Array();   
	var nameList = new Array();
	var str = "";
	
	var count = sessionStorage.length;
	if(count !== 0) {
		$("#planTitle").val(sessionStorage.getItem("planTitle"))
		$("#plancate").val(sessionStorage.getItem("planCate"))
		$("#plandate").val(sessionStorage.getItem("plandate"))
	}
	
	let placeList = JSON.parse(sessionStorage.getItem("placeList"));
	if (placeList == null) {
		placeList = [];
	}
	sessionStorage.removeItem("placeList");
	for (let i of placeList) {
		addPlaceList(i.placeNo, i.placeName, i.addr);
	}
	
	$(document).on("click", ".del", function(){
		let placeNo = $(this).parent().children('input[name="placeNo"]').val();
		$(this).parent().remove();
		let placeList = JSON.parse(sessionStorage.getItem("placeList"));
		if (placeList == null) {
			placeList = [];
		}
		let newPlaceList = [];
		for (let i of placeList) {
			if (i.placeNo != placeNo) {
				newPlaceList.push(i);
			}
		}
		clearPlaceList();
		for (let i of newPlaceList) {
			addPlaceList(i.placeNo, i.placeName, i.addr);
		}
	});
	$("#wBtn").click(function(){
		var r = confirm("플랜을 등록하시겠습니까?")
		if(r==true){
			$("#planWrite").submit();
			sessionStorage.clear();
		}
	});
	
	$("#planTitle").blur(function(){
		var title = $("#planTitle").val();
		var maxlength = 100;
		var strLength = 0;
		var count = 0;
		
		for(let i=0; i<title.length; i++) {
			if(escape(title.charAt(i)).length == 6) {
				strLength++;
			}
			strLength++;
			if(strLength <= maxlength) {
				count++;
			}
		}
		if(parseInt(maxlength-strLength) <= 0) {
			alert(maxlength + "자 이상 입력 할 수 없습니다.")
			$("#planTitle").val(title.substr(0, count));
			return false;
		}
	})
	
	$("#searchBtn").click();
});