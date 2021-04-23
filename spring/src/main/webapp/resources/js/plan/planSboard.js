
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
