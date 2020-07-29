$(function(){
// 리뷰 삭제 버튼
	 var dBtn = document.getElementsByClassName("btn btn-outline-secondary btn-sm");
	 $(dBtn).click(function(){
		 if (confirm("작성하신 리뷰를 삭제합니다. 정말 삭제하시겠습니까?")) {
	         // 확인 버튼 클릭 시 동작
	         alert("삭제되었습니다.");
	     } else {
	         // 취소 버튼 클릭 시 동작
	         return false;
	     }
	 });
});