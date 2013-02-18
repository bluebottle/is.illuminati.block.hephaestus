jQuery.noConflict();

jQuery(document).ready(function() {
	initialize();
});

function initialize() {
	var mapOptions = {
		center: new google.maps.LatLng(39.38436498948841, -119.74777660735084),
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.TERRAIN
	};
	
	var infoWindow = new google.maps.InfoWindow;

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
				    	createMarker(new google.maps.LatLng(well.latitude, well.longitude), map, 'logs/?prm_well_id=' + well.id, well.name, infoWindow);
				}
			}
		});
	}
}

function createMarker(location, map, url, name, infoWindow) {
    var marker = new google.maps.Marker({
    	position: location,
    	map: map,
    	animation: google.maps.Animation.DROP
    });
    
    var html = "<div class='infowin'><strong>" + name + "</strong></div>";
    
    google.maps.event.addListener(marker, "click", function() {
        window.location = url;
    });
    
	google.maps.event.addListener(marker, 'mouseover', function() {
		infoWindow.setContent(html);
		infoWindow.open(map, marker);
	});

	google.maps.event.addListener(marker, 'mouseout', function() {
		infoWindow.close();
	});
}