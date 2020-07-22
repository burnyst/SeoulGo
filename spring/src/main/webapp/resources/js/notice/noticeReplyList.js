$(document).ready(function(){
	var nNo = $("#nNo").val();
	var url = "../reply/";
	url = url + nNo;
	var replyUL = $(".replyul");
	var str = "";
	
	$.ajax({
		url: url,
		type: "POST",
		data: nNo,
		dataType: "json",
		success: function(data){
			var htmls = "";
			if(data.length < 1) {
				str = '<br><div class="text-center">등록된 댓글이 없습니다.</div>';
				replyUL.html(str);
			}else {
				for(var i=0; i<data.length; i++){
					var date = new Date(data[i].nrDate),
					month = ''+(date.getMonth()+1),
					day = ''+(date.getDate()),
					year = date.getFullYear();
					
					if (month.length < 2) month = '0' + month; 
					if (day.length < 2) day = '0' + day;
					
					var rplDelete = "../reply/rplDelete/";
					rplDelete = rplDelete + data[i].nNo + "/" +data[i].nrNo;

					str += '<br><li data-nrNo="'+data[i].nrNo+'">';
					str += '<div class="row">';
					str += '<div class="replyer col-lg-2"><label class="col-form-label"><i class="fas fa-user"></i>&nbsp;&nbsp;';
					str += data[i].nrWriter;
					str += '</label></div>';
					str += '<div class="replycontent col-lg-8"><textarea class="form-control" name="replyContent" rows="1" readonly onclick="showModal();">';
					str += data[i].nrContent;
					str += '</textarea><a class="replyDelete" href="'+rplDelete+'">댓글 삭제</a></div>';
					str += '<div class="replydate col-lg-2"><label class="col-form-lable"><small>';
					str += year + '년 ' + month + '월 ' + day + '일';
					str += '</small></label><br>';
				}
				replyUL.html(str);
			}
		}
	})
});