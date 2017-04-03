$( document ).ready(function() {
	$('#caravanForm fieldset:first').fadeIn('slow');
	$('#caravanForm .btn-next').on('click', function() {
		$(this).parents('fieldset').fadeOut(400, function() {
	    	$(this).next().fadeIn();
			//scroll_to_class('#caravanForm');
	    });
		changeProgressBar(+17);
	});	
	$('#caravanForm .btn-previous').on('click', function() {
		$(this).parents('fieldset').fadeOut(400, function() {
			$(this).prev().fadeIn();
			//scroll_to_class('#caravanForm');
		});
		changeProgressBar(-17);
	});
	
	function changeProgressBar(changeNumber) {
		var progress_bar_value = parseInt($('.progress-bar').attr('aria-valuenow'));
		progress_bar_value += parseInt(changeNumber);
		$('.progress-bar').css('width', progress_bar_value+'%').attr('aria-valuenow', progress_bar_value);
	}
});