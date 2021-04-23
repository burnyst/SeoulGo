$(document).ready(function () {
	$(".spinner-border").hide();
	var imgCount = 1;
	$(document).on("click", ".add", function () {
		if (imgCount >= 10) {
			alert("이미지를 10개 이상 등록할 수 없습니다.");
			return;
		} else {
			imgCount += 1;
			var delBtn = '<button class="btn btn-danger del" type="button"><i class="fas fa-minus"></i></button>';
			var input = '<input name="files" class="form-control form-control-file border" type="file" accept="image/*" />';
			var div = '<div class="image"><div class="input-group">'+input+'<div class="input-group-append">'+delBtn+'</div></div></div>';
			$(this).parents("td").append(div);
		}
	});
	$(document).on("click", ".del", function () {
		$(this).parents(".image").remove();
		imgCount -= 1;
	});
	$(document).on("change", 'input[type="file"]', function (e) {
		if (e.target.files[0].type.match("image/.*")) {
			var input = $(this);
			var reader = new FileReader();
			reader.onload = function(e) {
				input.parents(".image").children("img").remove();
				input.parents(".image").append($('<img src="'+e.target.result+'" style="width:300px" class="img-thumbnail" alt="preview" />'))
			}
			reader.readAsDataURL(e.target.files[0]);
		} else {
			alert("이미지만 업로드 가능합니다.");
			e.target.value = "";
		}
	});
	$(document).on("click", "#uploadBtn", function(e) {
		$("#uploadBtn").prop("disabled", true);
		$(".spinner-border").show();
		var formData = new FormData($("form")[0]);
		$.ajax({
			url: $("pageUri").val(),
			processData: false,
			contentType: false,
			data: formData,
			type: "POST",
			dataType: "json",
			success: function(result) {
				if (result) {
					alert("입력 완료");
					$("#cancelBtn").click();
				} else {
					alert("입력 실패");
				}
			},
			error: function() {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			complete: function() {
				$("#uploadBtn").prop("disabled", false);
				$(".spinner-border").hide();
			}
		});
	});
	$(document).on("click", "#cancelBtn", function() {
		$(".del").parents(".image").remove();
		$(".add").parents(".image").children("img").remove();
		imgCount = 1;
		$("form")[0].reset();
	});
	$(document).on("click", "#backBtn", function() {
		history.back();
	});
});