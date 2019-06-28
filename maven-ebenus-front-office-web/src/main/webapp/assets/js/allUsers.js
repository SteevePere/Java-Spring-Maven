	
	$(document).ready( function () {
		
	    $('#allUsers').DataTable(
			
		{
			"dom": '<"top"fl>rt<"bottom"ip><"clear">',
			language: {
				search: "",
				searchPlaceholder: "Search Users"
			},
			"lengthMenu": [ 5, 10, 25, 50, 75, 100 ],
			"pageLength": 10
		});
	    
	    var alert = document.getElementById("alert");
	    
	    if (alert) {
	    	
	    	$("#successAlert").fadeTo(2000, 500).slideUp(500, function(){
				$("#successAlert").slideUp(500);
			});
	    }
	});
	
	function toggleAccount(userId, newState) {
		
		$("#listIcon").attr('class', 'lds-dual-ring');
		
		try {
			
			$.post(
			  
				"UserServlet?ajax_toggleAccount",
				{ 
					userId: userId,
					newState: newState, 
				}
				
			).always(function (reply) {
				
				window.location.href = "UserServlet?showAllUsers?userUpdated";
			});
		}
		 
		catch (err) {
			
			console.log("error : " + err);
		}
	}