$(function(){
	var nNo = $("#nNo").val();
	$("#deleteBtn").click(function(){
		var r = confirm(nNo+"번 게시글을 삭제하시겠습니까?")
		if(r==true){
			location.href = "/notice/delete?nNo=" + nNo;
		}
	})
})