$(function(){
// 싫어요
	// 장소번호
	var dplaceNo = document.getElementsByName("dplaceNo");
		dplaceNo = parseInt(dplaceNo[0].value)
		
	// 글번호
	var badrNoArr = document.getElementsByName("rNo");
	var list = new Array();	
	
	// 멤버 아이디
	var dmemberID = document.getElementsByName("dmemberID");
		dmemberID = dmemberID[0].value;
	
	for (let i=0; i<badrNoArr.length; i++){
		list[i]= parseInt(badrNoArr[i].value)
	// 추천버튼 클릭시(추천 추가 또는 추천 제거)
	$("#bad_update"+badrNoArr[i].value).click(function(){
		if (confirm("비추는 한번만 가능합니다. 다시 누르면 취소됩니다.")) {
			$.ajax({
				url: "/review/badcnt",
	            type: "POST",
	            data: {
	           		rNo: list[i],
					placeNo: dplaceNo,
	               	memberID: dmemberID
	       		},
	            success: function(){
			    	badCount();
	            }
			});
		} else {
	         // 취소 버튼 클릭 시 동작
	         return false;
	    }		
	});
	 
	// 게시글 추천수
    function badCount() {
		$.ajax({
			url: "/review/badcount",
        	type: "POST",
        	data: {
        		rNo: list[i],
        		placeNo: dplaceNo
         	},
        	success: function (count) {
        		$(".bad_count"+badrNoArr[i].value).html(count);
        	}
		})
    };
    badCount(); // 처음 시작했을 때 실행되도록 해당 함수 호출
	}
});