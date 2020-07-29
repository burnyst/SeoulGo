$(function(){
	var rowArr = document.getElementsByClassName("dataRow");
	var nNo = document.getElementsByName("nNo");
	for (let i=0; i < rowArr.length; i++) {
		$(rowArr[i]).click(function(){
			var url = $("#basePath").val()+"/notice/cntUpdate";
			url = url + "?nNo=" + nNo[i].value;
			location.href = url;
		});
	}
	
	$("#btnSearch").click(function(){
		var keyword = $("#keyword").val();
		var searchType = $("#searchType").val();
		
		if(keyword != null && keyword != '' && searchType != null && searchType != '') {
			var url = $("#basePath").val()+"/notice/list";
			url = url + "?pageNo=1&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
			location.href = url;
			console.log(url);
		}else {
			alert("검색유형 혹은 검색어가 입력되지 않았습니다.");
		}
	});
});