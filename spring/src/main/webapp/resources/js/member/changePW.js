$(function(){
	// pw check
	$("#pw").blur(function(){
		var pw = $("#pw").val();
		var pwRegExp = /^[A-Za-z\d@$!%*#?&]{6,12}$/;

		if (pw === "") {
			alert("필수 정보입니다.");
		} else if(!pwRegExp.test(pw)) {
			alert("비밀번호는 6~12자리의 영어 소문자+대문자, 숫자, 특수문자만 가능합니다.");
			$("#pw").val("");
		}
	});
	
	// password check
	$("#memberPW").blur(function(){
		var pw = $("#memberPW").val();
		var pwRegExp = /^[A-Za-z\d@$!%*#?&]{6,12}$/;

		if (pw === "") {
			alert("필수 정보입니다.");
		} else if(!pwRegExp.test(pw)) {
			alert("비밀번호는 6~12자리의 영어 소문자+대문자, 숫자, 특수문자만 가능합니다.");
			$("#memberPW").val("");
		}
	});
	  
	$("#memberPW2").blur(function(){
		var pw = $("#memberPW").val();
		var pw2 = $("#memberPW2").val();

		if (pw !== pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#memberPW2").val("");
		} else if (pw2 === "") {
			alert("필수 정보입니다.");
		}
	});
});