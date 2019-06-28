	
		var error = "";
		var status = true;
		
		function checkIfEmailExists() {
			
			var oldEmail = $("#oldEmail").val();
			var newEmail = $("#newEmail").val();
			
			if (newEmail != oldEmail) {
			
				try {
		           	
					$.post(
						"RegisterServlet?ajax_checkIfEmailExists",
						{ 
							email: newEmail
						}
						
					).always(function(reply) {
						
						if (reply.status == 403) {
							
							$("#emailAlert").fadeTo(1000, 500).slideUp(500, function(){
								$("#emailAlert").slideUp(500);
							});
							
							error = "emailExists";
							status = false;
						}
						
						else {
							
							error = "noerror";
							status = true;
						}
					});
				}
				 
				catch (err) {
					console.log("error : " + err);
				}
			}
		}
		
		
		$("#newEmail").on('keyup blur', function() {
	
			checkIfEmailExists();
			
			return;
		});
		
		
		$("#newRole").on('change', function() {
			
			var oldRole = $("#oldRole").val();
			var newRole = $("#newRole").find(":selected").attr("id");
			
			if (oldRole == "Chief" && newRole != "Chief") {
				
				$("#adminPrivilegesAlert").fadeTo(2500, 500).slideUp(500, function(){
					$("#adminPrivilegesAlert").slideUp(500);
				});
	            
				return;
			}
		});
		
		
		$('#saveAccount').click(function(event) {
	
			checkIfEmailExists();
			
			var userId = $("#userId").val();
			var newFirstName = $("#newFirstName").val();
			var newLastName = $("#newLastName").val();
			var newBirthDate = new Date($('#newBirthDate').val());
	        var day = newBirthDate.getDate();
	        var month = newBirthDate.getMonth() + 1;
	        var year = newBirthDate.getFullYear();
	        var date = [day, month, year].join('/');
	        var newEmail = $("#newEmail").val();
	        var newRole = $("#newRole").find(":selected").attr("id");
	        
        	if (newFirstName == "") {
	        	
				$("#newFirstNameAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#newFirstNameAlert").slideUp(500);
				});
	            
				return;
			}

			if (newLastName == "") {
	        	
				$("#newLastNameAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#newLastNameAlert").slideUp(500);
				});
	            
				return;
			}
			
	        if (date == "NaN/NaN/NaN") {
	
				$("#newBirthDateAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#newBirthDateAlert").slideUp(500);
				});
				   
				return;
	        }
	        
			if (newEmail == "") {
	        	
				$("#emptyNewEmailAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#emptyNewEmailAlert").slideUp(500);
				});
	            
				return;
			}
			
			if (error == "emailExists") {
				
				$("#emailAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#emailAlert").slideUp(500);
				});
				
				return;
			}
			
			if (status == "true") { // This works => WTF ???????
				
				try {

	            	$.post(
					  
						"UserServlet?ajax_editAccount",
						{
							accountToEdit: "otherAccount",
							userId: userId,
							newFirstName: newFirstName,
							newLastName: newLastName, 
							newBirthDate: date, 
							newEmail: newEmail,
							newRole: newRole
						}
						
					).always(function (reply) {

						window.location.href = 'UserServlet?showAllUsers?userAccountEdited';
					});
	           }
				 
				catch (err) {
					
					console.log("error : " + err);
	           }
			}
		});