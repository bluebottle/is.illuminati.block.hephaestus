jQuery.noConflict();

jQuery(document).ready(function() {
	jQuery('a.imageItem').click(function(event) {
		event.preventDefault();
		
		jQuery('#wellLogHeaders').submit();
	});
});