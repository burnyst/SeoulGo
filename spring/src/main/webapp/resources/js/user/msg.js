$(function(){
	var msg = $(".msg");
	var str = "";
	if(location.search === "?msg=deleteSuccess") {
		str += '<td colspan="2" id="deleteSuccess">';
		str += '<i class="far fa-hand-paper fa-2x"></i> 회원탈퇴 완료되었습니다. 그동안 이용해주셔서 감사합니다.';
		str += '</td>';
	}
	msg.html(str);
	if(location.search === "?msg=pwUpdate") {
		str += '<td colspan="2" id="pwUpdate">비밀번호 변경에 성공했습니다. 다시 로그인해주세요.</td>';
	}
	msg.html(str);
})