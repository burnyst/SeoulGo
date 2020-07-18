$(function(){
	var list = new Array();
	var memArr = document.getElementsByName("memberID");
	var btnArr = document.getElementsByName("deleteBtn");
	
	for (let i=0; i < memArr.length; i++) {
		$(deleteBtn[i]).click(function(){
			var r = confirm(memArr[i].value+" 회원을 정말로 탈퇴시키시겠습니까?");
			if(r==true){
				$.ajax({
					url: "/admin/deleteAccount",
					type: "POST",
					async: false,
					data: {
						memberID: memArr[i].value
					},
					success: function(data) {
						if(data.msg === "success") {
							location.reload();
							alert(memArr[i].value+" 회원 탈퇴가 완료되었습니다.");
						}else {
							alert(memArr[i].value+" 회원 탈퇴가 실패했습니다.");
						}
					}
				});
			}
		})
	}
});