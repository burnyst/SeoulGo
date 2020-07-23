$(function(){
// 좋아요
	// 장소번호
	var dplaceNo = document.getElementsByName("dplaceNo");
		dplaceNo = parseInt(dplaceNo[0].value)
	
	// 글번호
	var goodrNoArr = document.getElementsByName("rNo");
	var list = new Array();	
	for (let i=0; i<goodrNoArr.length; i++){
		list[i]= parseInt(goodrNoArr[i].value)
	// 추천버튼 클릭시(추천 추가 또는 추천 제거)
	$("#good_update"+goodrNoArr[i].value).click(function(){
		 if (confirm("추천은 한번만 가능합니다. 다시 누르면 취소됩니다.")) {
			$.ajax({
				url: "/review/goodcnt",
	            type: "POST",
	            data: {
	           		rNo: list[i],
					placeNo: dplaceNo,
	               	memberID: "TEST690"
	       		},
	            success: function(){
			    	goodCount();
	            }
			});
		 } else {
	         // 취소 버튼 클릭 시 동작
	         return false;
	     }	
	});
	 
	// 게시글 추천수
    function goodCount() {
		$.ajax({
			url: "/review/goodcount",
        	type: "POST",
        	data: {
        		rNo: list[i],
        		placeNo: dplaceNo,
         	},
        	success: function (count) {
        		$(".good_count"+goodrNoArr[i].value).html(count);
        	}
		})
    };
    goodCount(); // 처음 시작했을 때 실행되도록 해당 함수 호출
	}
});