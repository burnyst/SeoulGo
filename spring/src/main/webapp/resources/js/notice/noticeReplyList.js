$(document).ready(function(){
	var nNo = $("#nNo").val();
	var url = "/comment/";
	url = url + nNo;
	var replyUL = $(".comment_list");
	var str = "";
	
	$.ajax({
		url: url,
		type: "POST",
		data: nNo,
		dataType: "json",
		async: false,
		success: function(data){
			var htmls = "";
			if(data.length < 1) {
				str = '<br><div class="commnet_box text-center">등록된 댓글이 없습니다.</div>';
				replyUL.html(str);
			}else {
				var arr = new Array();
				var nrWriterarr = new Array();
				for(var i=0; i<data.length; i++){
					var date = new Date(data[i].nrDate),
					month = ''+(date.getMonth()+1),
					day = ''+(date.getDate()),
					year = date.getFullYear();
					hour = date.getHours();
					min = date.getMinutes();
					sec = date.getSeconds();
					
					if (month.length < 2) month = '0' + month; 
					if (day.length < 2) day = '0' + day;
					nrDate = year + "." + month + "." + day + " " + hour + ":" + min + ":" + sec;
					
					// 대댓글(댓글의 답글) 입력창 padding 설정
					var nrOrder = data[i].nrOrder;
					nrOrder = (nrOrder * 30) + "px;";
					
					// 댓글 삭제 url
					var rplDelete = $("#basePath").val()+"/comment/delete/";
					rplDelete = rplDelete + data[i].nNo + "/" +data[i].nrNo;
					
					// 댓글, 대댓글(댓글의 답글) 작성자 정보 가져와서 프로필 사진 및 작성자 아이디 처리하기
					var proSaveName = "";
					var nrWriter = "";
					arr[i] = data[i].nrNo;
					nrWriterarr[i] = data[i].nrWriter;
					$.ajax({
						url: $("#basePath").val()+"/comment/getInfo",
						type: "POST",
						data: {
							nrNo: arr[i],
							nrWriter: nrWriterarr[i]
						},
						async: false,
						success: function(data){
							if(data.msg === "success") {
								values = data.list;
								$.each(values, function(index, value){
									if(value.proSaveName === '' || value.proSaveName === null) {
										if(value.mLevel === "ROLE_ADMIN") {
											proSaveName = $("#basePath").val()+"/resources/img/admin/default.png";
											nrWriter = "관리자";
										}else {
											proSaveName = $("#basePath").val()+"/resources/img/member/default.png";
											nrWriter = value.memberID;
										}
									}else if(value.proSaveName !== '' || value.proSaveName !== null) {
										if(value.mLevel === "ROLE_MEMBER") {
											nrWriter = value.memberID;
											var obj = new Image();
											obj.src = $("#basePath").val()+"/resources/img/member/"+value.proSaveName;
											if(!obj.src) {
												proSaveName = $("#basePath").val()+"/resources/img/member/default.png";
											}else {
												proSaveName = $("#basePath").val()+"/resources/img/member/"+value.proSaveName;
											}
										}else {
											proSaveName = $("#basePath").val()+"/resources/img/admin/default.png";
											nrWriter = "관리자";
										}
									}else if(value.mLevel === "ROLE_ADMIN") {
										nrWriter = "관리자";
									}else if(value.mLevel === "ROLE_MEMBER") {
										nrWriter = value.memberID;
									}
								})
							}
						}
					})
					// 댓글 리스트 출력 코드
					str += '<div class="container mt-3" style="padding-left: '+nrOrder+'" data-nrParent="' + data[i].nrParent + '">';
					str += '	<div class="comment_box"><div class="media">';
					str += '		<img id="pro" src="'+proSaveName+'" class="mr-3 rounded-circle" width="50" height="50">';
					str += '			<div class="media-body"><h5 class="primary-font">'+nrWriter+' <small class="pull-right text-muted"><i>';
					str += nrDate+'</i></small><button type="button" class="btn btn-link dropdown-toggle dropdown-toggle-split" ';
					str += 'data-toggle="dropdown"></button><div class="dropdown-menu">';
					str += '	<a class="dropdown-item" name="reReply" data-toggle="modal" data-target=".reply">댓글 답글 작성</a>';
				    str += '	<a class="dropdown-item" href="'+rplDelete+'">댓글 삭제</a></h5>';
					str += '		<div class="comment_text_box"><p>'+data[i].nrContent+'</p>';
					str += '			<input type="hidden" name="nNo" id="nNo" value="'+data[i].nNo+'">';
					str += '			<input type="hidden" name="nrNo" id="nrNo" value="'+data[i].nrNo+'">';
					str += '			<input type="hidden" name="nrParent" id="nrParent" value="'+data[i].nrParent+'"></div></div></div></div></div><hr>';
				}
				// 댓글 리스트 출력 코드
				replyUL.html(str);
				
				// 대댓글(댓글의 답글) 작성 및 댓글, 대댓글(댓글의 답글) 작성자 정보 가져오기
				var form = document.replyForm;
				var nNoArr = document.getElementsByName("nNo");
				var nrNoArr = document.getElementsByName("nrNo");
				var nrParentArr = document.getElementsByName("nrParent");
				
				// 대댓글(댓글의 답글) 작성, 댓글 삭제
				$(".primary-font").click(function(){
					var index = $(".media-body h5").index(this);
					var url = $("#basePath").val()+"/comment/reply/" + data[index].nNo + "/" +data[index].nrNo + "/" + data[index].nrParent;
					var infoUrl = $("#basePath").val()+"/comment/infoCheck";
					var nNo = data[index].nNo;
					var nrNo = data[index].nrNo;
					
					$.ajax({
						url: infoUrl,
						type: "POST",
						data: {
							nNo, nrNo
						},
						success: function(data){
							if(data.msg === "success"){
								
							}else {
								alert("댓글의 답글은 해당 댓글의 작성자와 관리자만 작성 할 수 있습니다.")
								$(".dropdown-menu a").attr("class", "dropdown-item disabled");
							}
						}
					})
					
					$("#btnReply2").click(function(){
						var content = $("#nrContent2").val();
						form.action = url;
							
						if(content === "") {
							alert("댓글이 작성되지 않았습니다.");
							return false;
						} else{
							$("#replyForm").submit();
						}
					})
				})
			}
		}
	})
});

$(function(){
	$("#btnReply").click(function(){
		var content = $("#nrContent").val();
		if(content === "") {
			alert("댓글이 작성되지 않았습니다.");
		} else {
			$("#replyFrm").submit();
		}
	})
})