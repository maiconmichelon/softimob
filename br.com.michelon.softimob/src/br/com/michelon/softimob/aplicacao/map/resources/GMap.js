window.init = function( center, zoom, type ) {
  var parent = document.getElementById( "map_canvas" );
  if( window[ "google" ] ) {
    var options = {
  	  center : new google.maps.LatLng( center[ 0 ], center[ 1 ] ),
  	  zoom : zoom,
  	  mapTypeId : google.maps.MapTypeId[ type ]
    };
    window.gmap = new google.maps.Map( parent, options );
    window.geocoder = new google.maps.Geocoder();
    window.geocoderMarker = new google.maps.Geocoder();
    _registerEventListener();
  } else {
    parent.innerHTML = '<p style="padding: 10px;">Falha ao conectar ao Google Maps. Verifique sua conexão com a internet.</p>';
  }
};

window.gotoAddress = function( address ) {
  if( window[ "google" ] ) {
    geocoder.geocode( { "address" : address }, window._handleAddressResolved );
  }
};

window.addMultipleMarkers = function( address, titulo ) {

	geocoderMarker.geocode( { "address": address}, function( results, status ) {
		var latitude = results[0].geometry.location.lat();
	    var longitude = results[0].geometry.location.lng();
	
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(latitude, longitude),
			map: window.gmap,
			title: titulo
		});
	}); 
};

// INTERNO

window._registerEventListener = function(){
  //The actual "center_changed" event can't be easily used because it can create
  //a lot of events (resulting in requests) while dragging. 
  google.maps.event.addListener( gmap, "dragend", function() {
    _handleBoundsChanged();
  } );
  google.maps.event.addListener( gmap, "zoom_changed", function() {
    _handleBoundsChanged();
  } );
};

window._handleAddressResolved = function( results, status ) {
  // NOTE: This function is called asynchronously (i.e. not from within java)
  if( status == google.maps.GeocoderStatus.OK && results[ 0 ] ) {
    var newBounds = results[ 0 ].geometry.viewport;
    gmap.fitBounds( newBounds );
  }
};

