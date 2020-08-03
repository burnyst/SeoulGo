var cnt = [1,2,3,4,5]; 
var array = new Array();

function setThumbnail(event){
	var reader = new FileReader();

	reader.onload = function(event){
		var img = document.createElement("img");	
		img.setAttribute("src", event.target.result);
		img.setAttribute("class", "ready_made");
		for (let i=0; i<5; i++){
			array[i] = cnt[i];
			document.querySelector("span#image_container"+array[i]).appendChild(img);
		};
	};
	reader.readAsDataURL(event.target.files[0]);
};
$(function(){
	//추가버튼 클릭시 첨부파일이 동적으로 추가 (최대 5개 까지)
		var cnt=0; 
		$("#aBtn").click(function(){
			cnt++;
		// 첨부파일은 5개까지 라고 알려주자.
		if(cnt==6){
			alert("첨부파일은 5개까지 가능합니다.");
			cnt=5;
			return;
		}
		
		// 추가할 대상
		var tr=	'<tr>';
			tr+='<th class="text-center"></th>';
			tr+='<td colspan="4">';
			tr+='<input type="file" name="files" id="files'+cnt+'" accept="image/*" onchange="setThumbnail(event);"/>';
			tr+='<span id="image_container'+cnt+'"></span>';
			tr+='</td>';
			tr+='</tr>';
				// 원하는 위치에 붙인다.	
				$("#copy").before(tr);
		});
	//삭제버튼 클릭시 첨부파일이 동적으로 삭제 (최소 1개는 남기고)
		$("#dtn").click(function(){
			if(cnt==0){
				return;
			}
			// 제거 코드
			var tr = $("#files"+cnt).parents("tr"); 
			tr.remove();
			cnt--;
		});
});