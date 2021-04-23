$(function(){
	var nNo = $("#nNo").val();
	$("#btn").click(function(){
		var r = confirm(nNo+"번 게시글을 수정하시겠습니까?")
		if(r==true){
			$("#modify").submit();
		}
	})
})