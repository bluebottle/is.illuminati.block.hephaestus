jQuery.noConflict();

jQuery(document).ready(function() {
	initialize();
});

function initialize() {
	var mapOptions = {
		/*center: new google.maps.LatLng(64.895589, -19.127197),*/
		zoom: 18,
		mapTypeId: google.maps.MapTypeId.TERRAIN
	};

	var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
	var projectId = jQuery('input[name="prm_project_id"]').val();
	
	if (projectId) {
		jQuery.ajax({
			url: '/rest/wells/' + projectId,
			dataType: 'json',
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
				    var well = data[i];
				    if (well.longitude && well.latitude)
				    	createMarker(new google.maps.LatLng(well.latitude, well.longitude), map, 'logs/?prm_well_id=' + well.id);
				}
			}
		});
	}
}

function createMarker(location, map, url) {
    var marker = new google.maps.Marker({
    	position: location,
    	map: map,
    	animation: google.maps.Animation.DROP
    });
    
    google.maps.event.addListener(marker, "click", function() {
        window.location = url;
    });
}