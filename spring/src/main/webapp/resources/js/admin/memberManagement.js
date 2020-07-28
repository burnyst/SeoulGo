$(function(){
	var memArr = document.getElementsByName("memberID");
	var btnArr = document.getElementsByName("deleteBtn");
	
	for (let i=0; i < memArr.length; i++) {
		$(btnArr[i]).click(function(){
			var r = confirm(memArr[i].value+" 회원을 정말로 탈퇴시키시겠습니까?");
			if(r==true){
				$.ajax({
					url: "./deleteAccount",
					type: "POST",
					async: false,
					dataType: "json",
					data: {
						memberID: memArr[i].value
					},
					success: function(data) {
						if(data.msg === "success") {
							alert(memArr[i].value+" 회원 탈퇴가 완료되었습니다.");
							location.reload();
						}else {
							alert(memArr[i].value+" 회원 탈퇴가 실패했습니다.");
						}
					}
				});
			}
		})
	}
	
	$("#btnSearch").click(function(){
		var keyword = $("#keyword").val();
		var searchType = $("#searchType").val();
		
		if(keyword != null && keyword != '' && searchType != null && searchType != '') {
			var url = "/admin/search";
			url = url + "?pageNo=1&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
			location.href = url;
			console.log(url);
		}else {
			alert("검색유형 혹은 검색어가 입력되지 않았습니다.");
		}
	});
});