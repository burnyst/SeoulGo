$(function(){
	//추가버튼 클릭시 첨부파일이 동적으로 추가 (최대 5개 까지)
		var cnt=1; 
		$("#aBtn").click(function(){
			cnt++;
		// 첨부파일은 5개까지 라고 알려주자.
		if(cnt==6){
			alert("첨부파일은 5개까지 가능합니다.");
			cnt=5;
			return;
		}
		/*			
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="files" id="files"/></td>
		</tr>
		*/
		// 추가할 대상
		var tr=	'<tr>';
			tr+='<td></td>';
			tr+='<td colspan="4"><input type="file" name="files" id="files'+cnt+'"/></td>';
			tr+='</tr>;'
				// 원하는 위치에 붙인다.	
				$("#copy").before(tr);
		});
	//삭제버튼 클릭시 첨부파일이 동적으로 삭제 (최소 1개는 남기고)
		$("#dtn").click(function(){
			if(cnt==1){
				return;
			}
			// 제거 코드
			var tr = $("#files"+cnt).parents("tr"); 
			tr.remove();
			cnt--;
		});
});