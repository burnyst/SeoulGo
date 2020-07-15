var check = new Map();

$(function() {
	// mName check
	$("#mName").blur(function(){
		var name = $("#mName").val();
		var message;
		if (name === "") {
			alert("필수 정보입니다.");
			check.set("name", 0);
		} else {
			message = "";
			check.set("name", 1);
		}
		$("#name_check").html(message);
	});

	// email check
	$("#email").blur(function(){
		var email = $("#email").val();
		var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var message;
		if (email === "") {
			alert("필수 정보입니다.");
			check.set("email", 0);
		} else if (!emailRegExp.test(email)) {
			alert("올바르지 않은 이메일 형식입니다.");
			$("#email").focus();
			check.set("email", 0);
		} else {
			message = "";
			check.set("email", 1);
		}
		$("#email_check").html(message);
	});
	
	$("#emailBtn").click(function(){
        var mName = $("#mName").val();
        var email = $("#email").val();
        alert(mName+"님, \n"+email+"로 인증번호가 발송되었습니다. \n인증번호를 확인해주세요.");
   });
});