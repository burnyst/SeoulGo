$(function() {
	// memberID check
	$("#memberID").blur(function(){
		var id = $("#memberID").val();

		if(id === "") {
			alert("필수 정보입니다.");
			$("#memberID").focus();
			return false;
		}
	});
	
	$("#memberIDChk").click(function(){
		var id = $("#memberID").val();
		
		$.ajax({
			url: "/user/idCheck",
			type: "POST",
			dataType: "json",
			async: false,
			data: {
				memberID: $("#memberID").val()
			},
			success: function(data) {
				if(id !== "") {
					if(data.idCheck === "success") {
						alert("등록되지 않은 아이디입니다. 확인 후 다시 입력해주세요.");
						$("#memberID").focus();
						return false;
					}else {
						alert("아이디가 확인되었습니다.");
					}
				}
			}
		});
	});
	
	// mName check
	$("#mName").blur(function(){
		var name = $("#mName").val();

		if (name === "") {
			alert("필수 정보입니다.");
			return false;
		} else {
			check.set("name", 1);
		}
	});

	// email check
	$("#email").blur(function(){
		var email = $("#email").val();
		var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var message;
		if (email === "") {
			alert("필수 정보입니다.");
			return false;
		} else if (!emailRegExp.test(email)) {
			alert("올바르지 않은 이메일 형식입니다.");
			$("#email").focus();
			return false;
		}
	});
	
	$("#emailBtn").click(function(){
		var memberID = $("#memberID").val();
        var mName = $("#mName").val();
        var email = $("#email").val();

        $.ajax({
            type : "POST",
            url : "/user/emailAuth",
            dataType: "json",
            async: false,
            data : {
            	memberID, mName, email
            },
            success : function(data) {
            	alert(mName+"님, \n"+email+"로 인증번호가 발송되었습니다. \n인증번호를 확인해주세요.");
            	confirmNum = data.confirmNum;
            	$("#emailNum").focus();
            },
            error : function(data, jqXHR) {
            	alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            	return false;
            }
        });
   });
	
	// password check
	$("#memberPW").blur(function(){
		var pw = $("#memberPW").val();
		var pwRegExp = /^[A-Za-z\d@$!%*#?&]{6,12}$/;
		
		if (pw === "") {
			alert("필수 정보입니다.");
			$("#memberPW").focus();
			return false;
		} else if(!pwRegExp.test(pw)) {
			alert("비밀번호는 6~12자리의 영어 소문자+대문자, 숫자, 특수문자만 가능합니다.");
			$("#memberPW").focus();
			return false;
		}
	});
	   
	$("#memberPW2").blur(function(){
		var pw = $("#memberPW").val();
		var pw2 = $("#memberPW2").val();
		
		if (pw !== pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#memberPW2").focus();
			return false;
		} else if (pw2 === "") {
			alert("필수 정보입니다.");
			$("#memberPW2").focus();
			return false;
		}
	});
	
	$("#confirmChk").click(function() {
		var emailNum = $("#emailNum").val();

		if (emailNum != confirmNum) {
			alert("인증번호가 일치하지 않습니다. \n확인 후 다시 입력해주세요");
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