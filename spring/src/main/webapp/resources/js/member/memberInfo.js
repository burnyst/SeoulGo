$(function(){	
	$("#files").change(function(){
		var formData = new FormData();
		formData.append("files", $("#files")[0].files[0]);
		
		var img = $("#files").val();
		var imgRegExp = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
		var maxSize = 1073741824;
		var fileSize = document.getElementById("files").files[0].size;
		var file = document.getElementById("files").files[0];
		
		if(!imgRegExp.test(img)) {
			alert("해당 파일은 이미지 파일이 아닙니다.");
			$("input[type='file']").val("");
		} else if(fileSize >= maxSize) {
			alert("업로드 할 수 있는 파일 용량을 초과했습니다.");
			$("input[type='file']").val("");
		} else if(this.files && this.files[0]) {
			var reader = new FileReader;
		    reader.onload = function(data) {
			    $(".pro img").attr("src", data.target.result).width(50);    
			    $(".pro img").attr("src", data.target.result).height(50);  
		    }
		    $(".pro img").attr("src", "");
		    reader.readAsDataURL(this.files[0]);
		}
	});
	
	// nickname check
	$("#nickname").change(function(){
		var nickname = $("#nickname").val();
		if (nickname === "") {
			alert("필수 정보입니다.");
			return false;
		}
		
		$.ajax({
			url: "../user/nickCheck",
			type: "POST",
			dataType: "json",
			data: {
				nickname: $("#nickname").val()
			},
			success: function(data) {
	            if(nickname !== "") {
	               if(data.nickCheck === "success") {
	            	   //alert("사용 가능한 닉네임입니다.");
	               }else {
	                  alert("이미 사용 중인 닉네임입니다. 다시 입력해주세요.");
	                  $("#nickname").val("");
	                  return false;
	               }
	            }
			}
		});
	});
	
	// email check
	$("#email").change(function(){
       var email = $("#email").val();
       var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       if (email === '') {
    	   alert("필수 정보입니다.");
           return false;
       }
       
       $.ajax({
    	   url: "../user/emailCheck",
    	   type: "POST",
    	   dataType: "json",
    	   data: {
    		   email: $("#email").val()
    	   },
    	   success: function(data) {
        	   if(email !== "") {
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
	});
	
	// phone check
	$("#phone").change(function(){
       var phone = $("#phone").val();
       var phoneRegExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
       if (phone === "") {
    	   alert("필수 정보입니다.");
           return false;
       } else if (!phoneRegExp.test(phone)) {
           alert("올바르지 않은 형식입니다.");
           $("#phone").val("");
           return false;
       }
	});
});