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
					//dataType: "json",
					async: false,
					data: {
						memberID: memArr[i].value
					},
					success: function(data) {
						if(memberID !== "") {
							if(data.emailCheck === "success") {
								if(!emailRegExp.test(email)){
									alert("입력하신 이메일은 올바르지 않은 형식입니다. 다시 입력해주세요");
									$("#email").val("");
									return false;
								}
							}else {
								alert("이미 사용 중인 이메일입니다. 다시 입력해주세요.");
								$("#email").val("");
								return false;
							}
						}
					}
				});
			}
		})
	}
});