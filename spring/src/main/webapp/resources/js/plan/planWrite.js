$(function(){
	let placeCount = 0;
	let basePath = $("#basePath").val();
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
					if (placeCount == 10) {
						alert("일정장소는 최대 10곳까지 가능합니다");
						return;
					}
					placeCount++;
					let placeNo = $(this).parent().children('input[name="searchResultPlaceNo"]').val();
					let placeName = $(this).parent().children(".media-body").children("a").text();
					let tags = '<div class="border border-secondary rounded text-secondary">';
					tags += '&nbsp&nbsp'+placeName;
					tags += '<button type="button" class="btn btn-sm btn-outline-light text-secondary" id="delBtn'+placeCount+'" style="border: 0; outline: 0;">'
					tags += '<span class="text-secondary">×</span></button><input type="hidden" name="placeNo" value="'+placeNo+'"></div>';
					
					$("#placeName").append(tags);
					sessionStorage.setItem("placeName"+(placeCount-1), placeName);
					sessionStorage.setItem("placeNo"+(placeCount-1), placeNo);
					sessionStorage.setItem("planTitle", $("#planTitle").val());
					sessionStorage.setItem("planCate", $("#plancate").val());
					sessionStorage.setItem("plandate", $("#plandate").val());
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
	console.log(count);
	if(count !== 0) {
		console.log("sessionStorage에 값이 존재합니다.")
		$("#planTitle").val(sessionStorage.getItem("planTitle"))
		count--;
		$("#plancate").val(sessionStorage.getItem("planCate"))
		count--;
		$("#plandate").val(sessionStorage.getItem("plandate"))
		count--;
		count = count/2;
		for(let j=0; j<count; j++) {
			str = '<div class="border border-secondary rounded text-secondary'+j+'" style="margin-bottom: 3px; margin-right: 3px;">&nbsp&nbsp'+sessionStorage.getItem("placeName"+j)
						+'<button type="button" class="btn btn-sm btn-outline-light text-secondary" id="delBtn'+j+'" style="border: 0; outline: 0;">'
						+'<span class="text-secondary">×</span></button><input type="hidden" name="placeNo" value="'+sessionStorage.getItem("placeNo"+j)+'"></div>';
			console.log(str);
			$("#placeName").append(str);
		}
		placeCount = count;
	}
	
	console.log("count="+count+", placeCount="+placeCount);
	for(let i=0; i<pNo.length; i++){
		list[i] = parseInt(pNo[i].value);
		nameList[i] = $(".media-body").children("a[id='aName"+list[i]+"']").text();
	}
	
	var btn = $("button[type='button']").length
	var btnArr = btn/2
	for(let i=0; i<btnArr; i++) {
		$("#delBtn"+i).click(function(){
			$(".text-secondary"+i).remove();
			sessionStorage.removeItem("placeName"+i);
			sessionStorage.removeItem("placeNo"+i);
		});
	}
	
	$("#wBtn").click(function(){
		var r = confirm("플랜을 등록하시겠습니까?")
		if(r==true){
			$("#planWrite").submit();
			sessionStorage.clear();
		}
	});
	
	$("#planTitle").blur(function(){
		var title = $("#planTitle").val();
		var strLength = 0;
		
		for(let i=0; i<title.length; i++) {
			if(escape(title.charAt(i)).length == 6) {
				strLength++;
			}
			strLength++;
		}
		
		if(parseInt(maxlength-strLength) <= 0) {
			alert(maxlength + "자 이상 입력 할 수 없습니다.")
			$("#planTitle").focus();
			return false;
		}
	})
	
	var mapContainer = document.getElementById('map');
	mapOption = {
		center: new kakao.maps.LatLng(37.5009565732326, 126.884660819027),
		level : 7
	};
	
	// 지도를 생성
	var map = new kakao.maps.Map(mapContainer, mapOption);
	var geocoder = new kakao.maps.services.Geocoder();
	
	$("#searchBtn").click();
});