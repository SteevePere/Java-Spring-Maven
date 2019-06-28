	
	//LEAFLET
	var lat = $('#incidentLatitude').val();
	var lng = $('#incidentLongitude').val();
	
	var map = L.map('map', { attributionControl: false, zoomControl:false }).setView(
		[lat, lng], 10);
	var layer = L.tileLayer('http://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
	});
	
	L.AwesomeMarkers.Icon.prototype.options.prefix = 'fa';
	
	var redMarker = L.AwesomeMarkers.icon({
		icon: 'bolt',
		iconColor: 'white',
		markerColor: 'red'
	  });
	
	marker = new L.marker([lat, lng], {draggable:'true', icon: redMarker}).addTo(map);
	var position = marker.getLatLng();
	
	lat = position.lat
	lng = position.lng
	
	marker.on('dragend', function(event){
		
		var marker = event.target;
		var position = marker.getLatLng();
		
		marker.setLatLng(new L.LatLng(position.lat, position.lng), {draggable:'true'});
		map.panTo(new L.LatLng(position.lat, position.lng))
		
		lat = position.lat
		lng = position.lng
		
	});
	
	map.addLayer(layer);
	//LEAFLET
	
	$(document).ready( function() {
		
		$('#submit').click(function(event) {
			
			event.preventDefault();
			
			var incidentId = $('#incidentId').val();
            var incidentType = $('#incidentType').find(":selected").attr("id");
            var newType = $('#newType').val();
            var incidentDate = new Date($('#incidentDate').val());
            var day = incidentDate.getDate();
            var month = incidentDate.getMonth() + 1;
            var year = incidentDate.getFullYear();
            var date = [day, month, year].join('/');

            if (date == "NaN/NaN/NaN") {

				$("#incidentDateAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#incidentDateAlert").slideUp(500);
				});
				   
				return;
            }
            
            if ($('#newType').css('display') == "block") {

            	if (!newType) {
            	  
					$("#newTypeAlert").fadeTo(1000, 500).slideUp(500, function(){
						$("#newTypeAlert").slideUp(500);
		           	});
		            
	                return;
            	} 
              
            	else {
            	  
            	  incidentType = newType;
            	}
            }
            
            if (lat == 0 || lng == 0) {

				$("#geolocAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#geolocAlert").slideUp(500);
				});
                
				return;
            }
            
            try {

            	$.post(
				  
					"ReportServlet?ajax_editReport",
					{ 
						incidentId: incidentId,
						incidentType: incidentType, 
						incidentDate: date, 
						incidentLatitude: lat, 
						incidentLongitude: lng,
					}
					
				).always(function (reply) {

					window.location.href = 'ReportServlet?showAllReports';
				});
           }
			 
			catch (err) {
				
				console.log("error : " + err);
           }
        });
		
		
		$('#incidentType').change(function() {
			
			var type = $('#incidentType').find(":selected").text();
			var newType = document.getElementById("newType");
			
			if (type == "Add New Type") {
			 
			  if (newType.style.display === "none") {
			  	newType.style.display = "block";
			  }
			}
			
			else {
			 
			  if (newType.style.display === "block") {
			  	newType.style.display = "none";
			  }
			}
       });
	});