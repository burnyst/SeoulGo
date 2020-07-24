$(document).ready(function(){
	var nNo = $("#nNo").val();
	var url = "../reply/";
	url = url + nNo;
	var replyUL = $(".comment_list");
	var str = "";
	
	$.ajax({
		url: url,
		type: "POST",
		data: nNo,
		dataType: "json",
		success: function(data){
			var htmls = "";
			if(data.length < 1) {
				str = '<br><div class="commnet_box text-center">등록된 댓글이 없습니다.</div>';
				replyUL.html(str);
			}else {
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
					
					// 대댓글(댓글의 답글) 작성 url
					/*var form = document.replyForm;
					form.action = "../reply/reReply/" + data[i].nNo + "/" +data[i].nrNo + "/" + data[i].nrParent;
					$("#btnReply").click(function(){
						form.submit();
					})*/
					
					// 댓글 삭제 url
					var rplDelete = "../reply/rplDelete/";
					rplDelete = rplDelete + data[i].nNo + "/" +data[i].nrNo;
					var reReply = "../reply/reReply/" + data[i].nNo + "/" +data[i].nrNo + "/" + data[i].nrParent;

					// 댓글 리스트 출력 코드
					str += '<div class="container mt-3" style="padding-left: '+nrOrder+'" data-nrParent="' + data[i].nrParent + '">';
					str += '	<div class="comment_box"><div class="media">';
					str += '		<img id="pro" src="/resources/img/member/default.png" class="mr-3 rounded-circle" width="50" height="50">';
					str += '			<div class="media-body"><h5 class="primary-font">'+data[i].nrWriter+' <small class="pull-right text-muted"><i>';
					str += nrDate+'</i></small><button type="button" class="btn btn-link dropdown-toggle dropdown-toggle-split" ';
					str += 'data-toggle="dropdown"></button><div class="dropdown-menu">';
					str += '	<a class="dropdown-item" name="reReply" data-toggle="modal" data-target=".reReply">'+reReply+'댓글 답글 작성</a>';
				    str += '	<a class="dropdown-item" href="'+rplDelete+'">댓글 삭제</a></h5>';
					str += '		<div class="comment_text_box"><p>'+data[i].nrContent+'</p>';
					str += '			<input type="hidden" name="nNo" id="nNo" value="'+data[i].nNo+'">';
					str += '			<input type="hidden" name="nrNo" id="nrNo" value="'+data[i].nrNo+'">';
					str += '			<input type="hidden" name="nrParent" id="nrParent" value="'+data[i].nrParent+'"></div></div></div></div></div><hr>';
				}
				replyUL.html(str);
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
	var test = document.getElementsByClassName("dropdown-toggle-split");
	var nNoArr = document.getElementsByName("nNo");
	var nrNoArr = document.getElementsByName("nrNo");
	var nrParentArr = document.getElementsByName("nrParent");
	var reReplyArr = document.getElementsByName("reReply");
	var form = document.replyForm;
	alert(test.length);
	
	for(var i=0; i<nNoArr.length; i++){
		$(reReplyArr[i]).click(function(){
			
			form.action = "../reply/reReply/" + data[i].nNo + "/" +data[i].nrNo + "/" + data[i].nrParent;
			$("#replyForm").submit();
		})
	}
	//alert($("#nNo").val() + "/" + $("#nrNo").val() + "/" + $("#nrParent").val());
	/*var form = document.replyForm;
	form.action = "../reply/reReply/" + $("#nNo").val() + "/" + $("#nrNo").val() + "/" + $("#nrParent").val();*/
	//$("#replyForm").submit();
	
	/*$("#btnReply2").click(function(){
		var content = $("#nrContent2").val();
		if(content === "") {
			alert("댓글이 작성되지 않았습니다.");
		} else{

		}
	})*/
})