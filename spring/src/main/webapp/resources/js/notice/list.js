$(function(){
	var rowArr = document.getElementsByClassName("dataRow");
	var nNo = document.getElementsByName("nNo");
	for (let i=0; i < rowArr.length; i++) {
		$(rowArr[i]).click(function(){
			var url = "/notice/cntUpdate";
			url = url + "?nNo=" + nNo[i].value;
			location.href = url;
		});
	}
});