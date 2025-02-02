$(function() {
	// mName check
	$("#mName").change(function(){
		var name = $("#mName").val();
		if (name === "") {
			alert("필수 정보입니다.");
			return false;
		}
	});

	// email check
	$("#email").change(function(){
		var email = $("#email").val();
		var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if (email === "") {
			alert("필수 정보입니다.");
			return false;
		} else if (!emailRegExp.test(email)) {
			alert("올바르지 않은 이메일 형식입니다.");
			return false;
		}
	});
	
	$("#emailBtn").click(function(){
        var mName = $("#mName").val();
        var email = $("#email").val();
        
        $.ajax({
            type : "POST",
            url : "./emailAuth",
            dataType: "json",
            async: false,
            data : {
            	mName, email
            },
            success : function(data) {
            	if(data.result!=null){
            		alert(mName+"님, \n"+email+"로 인증번호가 발송되었습니다. \n인증번호를 확인해주세요.");
                	confirmNum = data.confirmNum;
                	//alert(confirmNum);
                	$("#emailNum").focus();
            	}else {
            		alert("입력한 정보와 일치하는 아이디가 존재하지 않습니다.");
            		return false;
            	}
            },
            error : function(data, jqXHR) {
            	//alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요.\n발생 에러: "+jqXHR.statusText+"\njqXHR.responseText: "+jqXHR.responseText);
            	//alert(data);
            	alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            	return false;
            }
        });
	});
	
	$("#confirmChk").click(function() {
		var emailNum = $("#emailNum").val();
		
		if (emailNum != confirmNum) {
			alert("인증번호가 일치하지 않습니다. \n확인 후 다시 입력해주세요");
			$("#emailNum").val("");
			return false;
		} else {
			var r = confirm("인증번호가 일치합니다");
			$("#searchIDProc").click(function() {
				if (r == true) {
					$("#searchID").submit();
				}
			})
		}
	});
});