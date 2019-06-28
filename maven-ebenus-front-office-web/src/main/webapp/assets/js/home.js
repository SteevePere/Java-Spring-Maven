
	//LEAFLET

	var userRole = $("#userRole").val();
	var incidentsData = [];
	
	$("#incidents > option").each(function() {
		
		var incidentData = this.value.split(',');
		incidentsData.push(incidentData);
	});
	
    
	var map = L.map('homeMap', { attributionControl: false, zoomControl:false }).setView(
			[34.0268632, -118.2301097], 10);

	
	var layer = L.tileLayer('http://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {});

	
	L.AwesomeMarkers.Icon.prototype.options.prefix = 'fa';
	
	var redMarker = L.AwesomeMarkers.icon({
		icon: 'bolt',
		iconColor: 'white',
		markerColor: 'red'
	});
	
	$.each(incidentsData, function(index, incident) {
		
		var tooltipText = 
			
			"<b> Incident " 
			+ incident[0] + "</b> </br>" 
			+ incident[1] + "</br> <hr>" 
			+ incident[2] + "</br>" 
			+ incident[3] + "," 
			+ incident[4];
		
		if (userRole == "Chief" || userRole == "Detective") {
			
			tooltipText +=
				
				"<div id='tooltipForm'>" +
				"<form action='/maven-ebenus-front-office-web/ReportServlet?showEditReport' method='Post'> " +
					"<input id='incidentId' name='incidentId' type='hidden' value='" + incident[0] + "'>" +
					"<button type='submit' class='editButton' id='editButton' name='" + incident[0] + "' title='Edit Report'>" +
						"<i id='editiconHome' class='fas fa-pencil-alt'></i>" +
					"</button>" +
				"</form>" +
			"</div>";
		}
		
		L.marker([incident[3], incident[4]], {icon: redMarker}).on('click', function() {
			
			map.panTo(new L.LatLng(incident[3], incident[4]))
			
		}).addTo(map).bindPopup(tooltipText);
	});
	

	map.addLayer(layer);
