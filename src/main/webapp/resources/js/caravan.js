$( document ).ready(function() {
	$('#caravanForm fieldset:first').fadeIn('slow');
	$('#caravanForm .btn-next').on('click', function() {
		$(this).parents('fieldset').fadeOut(400, function() {
	    	$(this).next().fadeIn();
			//scroll_to_class('#caravanForm');
	    });
		changeProgressBar(+7,14);
	});	
	$('#caravanForm .btn-previous').on('click', function() {
		$(this).parents('fieldset').fadeOut(400, function() {
			$(this).prev().fadeIn();
			//scroll_to_class('#caravanForm');
		});
		changeProgressBar(-7,14);
	});
	
	function changeProgressBar(changeNumber) {
		var progress_bar_value = parseInt($('.progress-bar').attr('aria-valuenow'));
		progress_bar_value += parseInt(changeNumber);
		$('.progress-bar').css('width', progress_bar_value+'%').attr('aria-valuenow', progress_bar_value);
	}
	
	//codigo equipo t-A
	$('#row_dim').hide(); 
    $('#whoTrans').change(function(){
        if(($('#whoTrans').val() == 'myParking') || ($('#whoTrans').val() == 'serviceTrans')){
            $('#row_dim').show(); 
        } else {
            $('#row_dim').hide(); 
        } 
    });
    //animacion 5.8 hide/show
    $("#r1").click(function() {
		$("#d1").show();

	});
	$("#r2").click(function() {

		$("#d1").hide();
	});
});