var check = new Map();

$(function() {
	// password check
	$("#memberPW").blur(function(){
		var pw = $("#memberPW").val();
		var pwRegExp = /^[A-Za-z\d@$!%*#?&]{6,12}$/;
		var message;
		if (pw === "") {
			alert("필수 정보입니다.");
			$("#memberPW").focus();
			check.set("memberPW", 0);
		} else if(!pwRegExp.test(pw)) {
			alert("비밀번호는 6~12자리의 영어 소문자+대문자, 숫자, 특수문자만 가능합니다.");
			$("#memberPW").focus();
			check.set("memberPW", 0);
		} else {
			message = "";
			check.set("memberPW", 1);
		}
		$("#pw_check").html(message);
	});
	   
	$("#memberPW2").blur(function(){
		var pw = $("#memberPW").val();
		var pw2 = $("#memberPW2").val();
		var message;
		if (pw !== pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#memberPW2").focus();
			check.set("memberPW2", 0);
		} else if (pw2 === "") {
			alert("필수 정보입니다.");
			$("#memberPW2").focus();
			check.set("memberPW2", 0);
		} else {
			check.set("memberPW2", 1);
		}
		$("#pw2_check").html(message);
	});
	
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
	
	$("#searchPWProc").click(function(){
        var mName = $("#mName").val();
        alert(mName+"님, \n"+"비밀번호가 재설정되었습니다.");
   });
});