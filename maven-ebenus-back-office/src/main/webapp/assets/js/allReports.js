	
	$(document).ready( function () {
		
	    $('#allReports').DataTable(
			{
	        
				"dom": '<"top"fl>rt<"bottom"ip><"clear">',
				language: {
					search: "",
					searchPlaceholder: "Search Reports"
				},
				"lengthMenu": [ 5, 10, 25, 50, 75, 100 ],
				"pageLength": 10
			});
	});
	
	function deleteReport(incidentId) {
		
		var rowToDelete = document.getElementById(incidentId);

		try {
		        	
			$.post(
			  
				"ReportServlet?ajax_deleteReport",
				{ 
					incidentId: incidentId, 
				}
				
			).always(function (reply) {
				
				rowToDelete.remove();
				$("#delete" + incidentId).modal('hide');
			});
		}
		 
		catch (err) {
			
			console.log("error : " + err);
		}
	}