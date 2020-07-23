$(function(){
	// star rating
	var starRating = function(){
		var $star = $(".rRate"),
			$result = $star.find("output>b");

		$(document)
		.on("focusin", ".rRate>.input", function(){
			$(this).addClass("focus");
		})
		.on("focusout", ".rRate>.input", function(){
			var $this = $(this);
			setTimeout(function(){
				if($this.find(":focus").length === 0){
					$this.removeClass("focus");
				}
			}, 100);
		
		})
		.on("change", ".rRate :radio", function(){
			$result.text($(this).next().text());
		})
		.on("mouseover", ".rRate label", function(){
			$result.text($(this).text());
		})
		.on("mouseleave", ".rRate>.input", function(){
			var $checked = $star.find(":checked");
			if($checked.length === 0){
				$result.text("");
			} else {
				$result.text($checked.next().text());
			}
		});
	};
	starRating();
});