
function scroll_to_class(chosen_class) {
	var nav_height = $('nav').outerHeight();
	var scroll_to = $(chosen_class).offset().top - nav_height;

	if($(window).scrollTop() != scroll_to) {
		$('html, body').stop().animate({scrollTop: scroll_to}, 1000);
	}
}


jQuery(document).ready(function() {

	/*
	    Fullscreen background
	*/
	$('.site-wrapper').backstretch("resources/img/street-car-vehicle-vintage.jpeg");
	
	
	
	
});
