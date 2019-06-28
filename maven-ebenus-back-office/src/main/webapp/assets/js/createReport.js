	
	//LEAFLET
	var map = L.map('map', { attributionControl: false, zoomControl:false }).setView(
	    [34.0268632, -118.2301097], 10);
	var layer = L.tileLayer('http://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
	});
	
	L.AwesomeMarkers.Icon.prototype.options.prefix = 'fa';
	
	var redMarker = L.AwesomeMarkers.icon({
		icon: 'bolt',
		iconColor: 'white',
		markerColor: 'red'
	  });
	
	var i = 0
	var lat = 0
	var lng = 0
	
	map.on('click', onMapClick);
	
	function onMapClick(e) {
	
		if (i == 0) {
			
			marker = new L.marker(e.latlng, {draggable:'true', icon: redMarker});
			var position = marker.getLatLng();
			
			marker.on('dragend', function(event){
				
				var marker = event.target;
				var position = marker.getLatLng();
				
				marker.setLatLng(new L.LatLng(position.lat, position.lng), {draggable:'true'});
				map.panTo(new L.LatLng(position.lat, position.lng))
				
				lat = position.lat
				lng = position.lng
			});
			
			map.addLayer(marker);
			
			i = 1
			lat = position.lat
			lng = position.lng
		}
	};
	  
	map.addLayer(layer);
	
	$(document).ready( function() {
		
		$('#submit').click(function(event) {
			
			event.preventDefault();
			
			var createdBy = $('#createdBy').val()
            var incidentType = $('#incidentType').find(":selected").text();
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
            
            if (incidentType == "Incident Type") {
            	
				$("#incidentTypeAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#incidentTypeAlert").slideUp(500);
				});
                
				return;
			} 
            
            else if (incidentType == "Add New Type") {
            	
              if (!newType) {
            	  
				$("#newTypeAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#newTypeAlert").slideUp(500);
               	});
                
                return;
              } 
              
              else {
            	  
            	  incidentType = newType;
            	  $("#incidentType").append(new Option(newType, newType));
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
				  
					"ReportServlet?ajax_createReport",
					{ 
						incidentType: incidentType, 
						incidentDate: date, 
						incidentLatitude: lat, 
						incidentLongitude: lng,
						createdBy: createdBy
					}
					
				).always(function (reply) {

					$("#successAlert").fadeTo(500, 500).slideUp(500, function(){
						
						$("#successAlert").slideUp(500);
						$('#createReport').trigger("reset");
						
						if (document.getElementById("newType").style.display === "block") {
							document.getElementById("newType").style.display = "none";
						  }
						
						map.removeLayer(marker);
						
						lat = 0;
						lng = 0;
						i = 0;
					});
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