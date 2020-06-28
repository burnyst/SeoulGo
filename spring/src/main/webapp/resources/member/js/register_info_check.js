var check = new Map();

$(function(){
   //id check
   $("#id").blur(function(){
      var id = $("#id").val();
      var message;
      if(id === "") {
         message = "<span class='id fail_msg'>필수 정보입니다.</span>";
         check.set("id",0);
      }else {
         message = "";
         check.set("id",1);
      }
      
      $.ajax({
         url: "/idCheck",
         type: "POST",
         dataType: "json",
         async: false,
         data: {
        	 id: $("#id").val()
         },
         success: function(data) {
            if(id != "") {
               if(data.idCheck === "success") {
                  message = "<span class='id success_msg'>사용 가능한 아이디입니다.</span>";
               }else {
                  message = "<span class='id fail_msg'>기존에 등록된 아이디입니다.</span>";
               }
            }
            $("#id_check").html(message);
         }
      });
   });
   
   //password check
   $("#pw").blur(function(){
	   var pw = $("#pw").val();
	   var pwRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{12,}$/;
	   var message;
	   if (pw === "") {
		   message = "<span class='pw fail_msg'>필수 정보입니다.</span>";
		   check.set("pw", 0);
	   } else if(!pwRegExp.test(pw)) {
		   message = "<span class='pw fail_msg'>올바르지 않은 형식입니다.</span>";
		   check.set("pw", 0);
	   } else {
		   message = "";
		   check.set("pw", 1);
	   }
	   $("pw_check").html(message);
   });
   
   $("#pw2").blur(function(){
	   var pw = $("#pw").val();
	   var pw2 = $("#pw2").val();
	   var pwRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{12,}$/;
	   var message;
	   if (pw !== pw2) {
		   message = "<span class='pw fail_msg'>비밀번호가 일치하지 않습니다.</span>";
		   check.set("pw2", 0);
	   } else if (pw2 === "") {
		   message = "<span class='pw fail_msg'>필수 정보입니다.</span>";
		   check.set("pw2", 0);
	   } else if(!pwRegExp.test(pw2)) {
		   message = "<span class='pw fail_msg'>올바르지 않은 형식입니다.</span>";
		   check.set("pw", 0);
	   } else {
		   message = "<span class='pw success_msg'>비밀번호가 일치합니다.</span>";
		   check.set("pw", 1);
	   }
	   $("#pw2_check").html(message);
   });
   
   //name check
   $("#name").blur(function(){
	   var name = $("#name").val();
	   var message;
	   if (name === "") {
		   message = "<span class='name fail_msg'>필수 정보입니다.</span>";
		   check.set("name", 0);
	   } else {
		   message = "";
		   check.set("name", 1);
	   }
	   $("#name_check").html(message);
   });
   
   //birth check
   $("#birth").blur(function(){
       var birth = $("#birth").val();
       var birthRegExp = /^(19|20)[0-9]{2}(0[1-9]|1[1-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
       var message;
       if (birth === "") {
           message = "<span class='birth  fail_msg'>필수 정보입니다.</span>";
           check.set("birth", 0);
       } else if (!birthRegExp.test(birth)) {
       	check.set("birth", 0);
           message = "<span class='birth fail_msg'>올바르지 않은 형식입니다.</span>";
       } else {
           message = "";
           check.set("birth", 1);
       }
       $("#birth_check").html(message);
   });
   
 //gender check
   $("#gender").blur(function() {
       var gender = $("#gender").val();
       var message;
       if (gender === "") {
           message = "<span class='birth_msg fail_msg'>필수 정보입니다.</span>";
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
           message = "<span class='email_msg fail_msg'>올바르지 않은 형식입니다.</span>";
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