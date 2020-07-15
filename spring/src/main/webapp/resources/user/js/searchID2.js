$(function() {
	$("#confirmChk").click(function() {
		var emailNum = $("#emailNum").val();
		var confirmNum = $("#confirmNum").val();
		if (emailNum != confirmNum) {
			alert("인증번호가 일치하지 않습니다. \n확인 후 다시 입력해주세요");
			$("#emailNum").val("");
			$("#emailNum").focus();
		} else {
			var r = alert("인증번호가 일치합니다");
			$("#searchIDProc").click(function() {
				if (r == true) {
					$("#searchIDForm").submit();
				}
			})
		}
	})
})