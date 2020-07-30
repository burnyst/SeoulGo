$(function(){
		 $("#ibtn").click(function(){
			 //alert("함수 작동");
			 $(location).attr("href","/plan/planwrite");
		 })
});
$( document ).ready(function() {
	
	$('td').each(function(){
		var texts = $(this).text();
		if(texts.includes('\[')){
			var texts = texts.replace(/\[/g,'');
			$(this).text(texts);
		}
		if(texts.includes('\]')){
			var texts = texts.replace(/\]/g,'');
			$(this).text(texts);
		
		}
		if(texts.includes('\,')){
			var texts = texts.replace(/\,/g,'->');
			$(this).text(texts);
		}
	
	})
});
