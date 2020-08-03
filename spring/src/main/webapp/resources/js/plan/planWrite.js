$(function(){
	$(document).on("change", "#order", function() {
		$("#searchBtn").click();
	});
	$(document).on("click", "#searchBtn", function(e) {
		$("#searchBtn").prop("searchBtn", true);
		let formData = new FormData($("#searchForm")[0]);
		let requrl = $("#basePath").val()+"/plan/placeList";
		$.ajax({
			url: $("#basePath").val()+"/plan/placeList",
			processData: false,
			contentType: false,
			data: formData,
			type: "GET",
			dataType: "json",
			success: function(result) {
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
		$("#searchForm input[name='type']").val($(this).text());
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
			//return;
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
	
	function makePageList(placePage) {
		console.log(placePage);
	}
	$("#searchBtn").click();
});