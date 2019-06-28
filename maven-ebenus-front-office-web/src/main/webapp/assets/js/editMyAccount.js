	
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
		
		
		function checkPasswords() {
		
			var newPassword = $("#newPassword").val();
			var confirmedPassword = $("#confirmNewPassword").val();
			
			if (newPassword != confirmedPassword) {
				
				$("#passwordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#passwordAlert").slideUp(500);
				});
				
				error = "passwordsDontMatch";
			}
			
			else {
				
				error = "noerror";
			}
		}
	
		
		$("#newEmail").on('keyup blur', function() {
	
			checkIfEmailExists();
			
			return;
		});
		
		
		$("#confirmNewPassword").blur(function() {
	
			checkPasswords();
			
			return;
		});
		
		
		$('#saveMyAccount').click(function(event) {
	
			checkIfEmailExists();
			checkPasswords();
			
			var newFirstName = $("#newFirstName").val();
			var newLastName = $("#newLastName").val();
			var newBirthDate = new Date($('#newBirthDate').val());
	        var day = newBirthDate.getDate();
	        var month = newBirthDate.getMonth() + 1;
	        var year = newBirthDate.getFullYear();
	        var date = [day, month, year].join('/');
	        var newEmail = $("#newEmail").val();
	        var newPassword = $("#newPassword").val();
	        
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
			
			if (newPassword != "" && newPassword.length < 6) {
	        	
				$("#shortNewPasswordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#shortNewPasswordAlert").slideUp(500);
				});
	            
				return;
			}
			
			if (error == "emailExists") {
				
				$("#emailAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#emailAlert").slideUp(500);
				});
				
				return;
			}
			
			if (error == "passwordsDontMatch") {
				
				$("#passwordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#passwordAlert").slideUp(500);
				});
				
				return;
			}
			
			if (status == "true") { // This works => WTF ???????
				
				try {

	            	$.post(
					  
						"UserServlet?ajax_editMyAccount",
						{ 
							newFirstName: newFirstName,
							newLastName: newLastName, 
							newBirthDate: date, 
							newEmail: newEmail, 
							newPassword: newPassword,
						}
						
					).always(function (reply) {

						$("#successAlert").fadeTo(3000, 500).slideUp(500, function(){
							$("#successAlert").slideUp(500);
						});
					});
	           }
				 
				catch (err) {
					
					console.log("error : " + err);
	           }
			}
		});