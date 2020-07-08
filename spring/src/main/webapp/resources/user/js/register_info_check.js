var check = new Map();

$(function(){	
	$("#files").change(function(){
		$("#preview").empty();
		
		var formData = new FormData();
		formData.append("files", $("#files")[0].files[0]);
		
		var img = $("#files").val();
		var imgRegExp = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
		var maxSize = 1073741824;
		var fileSize = document.getElementById("files").files[0].size;
		var file = document.getElementById("files").files[0];
		
		if(!imgRegExp.test(img)) {
			alert("해당 파일은 이미지 파일이 아닙니다.");
			//파일 초기화
			$("input[type='file']").val("");
		} else if(fileSize >= maxSize) {
			alert("업로드 할 수 있는 파일 용량을 초과했습니다.");
			$("input[type='file']").val("");
		}
		
		$.ajax({
			url: "/user/fileCheck",
			data: formData,
			type: "POST",
			processData: false,
			contentType: false,
			success: function(data){
				alert(data)
				$("#profile-file").val(data);
			}
		});
	});
	
   //memberID check
   $("#memberID").blur(function(){
      var id = $("#memberID").val();
      var message;
      if(id === "") {
         message = "<span class='id_msg fail_msg'>필수 정보입니다.</span>";
         check.set("memberID", 0);
      }else {
         message = "";
         check.set("memberID", 1);
      }
      
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
                  message = "<span class='id_msg success_msg'>사용 가능한 아이디입니다.</span>";
               }else {
                  message = "<span class='id_msg fail_msg'>기존에 등록된 아이디입니다.</span>";
               }
            }
            $("#id_check").html(message);
         }
      });
   });
   
   //password check
   $("#memberPW").blur(function(){
	   var pw = $("#memberPW").val();
	   var pwRegExp = /^[A-Za-z\d@$!%*#?&]{6,12}$/;
	   var message;
	   if (pw === "") {
		   message = "<span class='pw_msg fail_msg'>필수 정보입니다.</span>";
		   check.set("memberPW", 0);
	   } else if(!pwRegExp.test(pw)) {
		   alert("비밀번호는 6~12자리의 영어 소문자+대문자, 숫자, 특수문자만 가능합니다.");
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
		   message = "<span class='pw_msg fail_msg'>비밀번호가 일치하지 않습니다.</span>";
		   check.set("memberPW2", 0);
	   } else if (pw2 === "") {
		   message = "<span class='pw_msg fail_msg'>필수 정보입니다.</span>";
		   check.set("memberPW2", 0);
	   } else {
		   message = "<span class='pw_msg success_msg'>비밀번호가 일치합니다.</span>";
		   check.set("memberPW2", 1);
	   }
	   $("#pw2_check").html(message);
   });
   
   //name check
   $("#mName").blur(function(){
	   var name = $("#mName").val();
	   var message;
	   if (name === "") {
		   message = "<span class='name_msg fail_msg'>필수 정보입니다.</span>";
		   check.set("name", 0);
	   } else {
		   message = "";
		   check.set("name", 1);
	   }
	   $("#name_check").html(message);
   });
   
	//nickname check
	$("#nickname").blur(function(){
		var name = $("#nickname").val();
		var message;
		if (nickname === "") {
			message = "<span class='nickname_msg fail_msg'>필수 정보입니다.</span>";
			check.set("nickname", 0);
		} else {
			message = "";
			check.set("nickname", 1);
		}
		$("#nickname_check").html(message);
	});
   
	//gender check
	$("#gender").blur(function() {
       var gender = $("#gender").val();
       var message;
       if (gender === "") {
           message = "<span class='gender_msg fail_msg'>필수 정보입니다.</span>";
           check.set("gender", 0);
       } else {
           message = "";
           check.set("gender", 1);
       }
       $("#gender_check").html(message);
   });

	//email check
	$("#email").blur(function(){
       var email = $("#email").val();
       var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
       var message;
       if (email === '') {
           message = "<span class='email_msg fail_msg'>필수 정보입니다.</span>";
           check.set("email", 0);
       } else if (!emailRegExp.test(email)) {
       	check.set("email", 0);
           message = "<span class='email_msg fail_msg'>올바르지 않은 형식입니다.</span>";
       } else {
           message = "";
           check.set("email", 1);
       }
       $("#email_check").html(message);
   });

	//phone check
	$("#phone").blur(function(){
       var phone = $("#phone").val();
       var phoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
       var message;
       if (phone === '') {
           message = "<span class='phone_msg fail_msg'>필수 정보입니다.</span>";
           check.set("phone", 0);
       } else if (!phoneRegExp.test(phone)) {
           message = "<span class='phone_msg fail_msg'>올바르지 않은 형식입니다.</span>";
           check.set("phone", 0);
       } else {
           message = "";
           check.set("phone", 1);
       }
       $("#phone_check").html(message);
   });
});

function checkInfo() {
	for(var [key, value] of check) {
		if (value === 0) {
			$("#" + key).focus();
			return false;
		} else {
			$("#reg_submit").attr("type", "submit");
		}
	}
}