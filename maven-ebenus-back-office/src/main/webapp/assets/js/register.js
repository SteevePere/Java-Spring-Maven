	
	var error = "";
		var status = false;
		
		function checkIfEmailExists() {
			
			var email = $("#registerEmail").val();
			
			try {
	           	
				$.post(
					"RegisterServlet?ajax_checkIfEmailExists",
					{ 
						email: email
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
		
		
		function checkPasswords() {
		
			var password = $("#password").val();
			var confirmedPassword = $("#confirmPassword").val();
			
			if (password != confirmedPassword) {
				
				$("#passwordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#passwordAlert").slideUp(500);
				});
				
				error = "passwordsDontMatch";
			}
			
			else {
				
				error = "noerror";
			}
		}
	
		
		$("#registerEmail").on('keyup blur', function() {
	
			checkIfEmailExists();
			
			return;
		});
		
		
		$("#confirmPassword").blur(function() {
	
			checkPasswords();
			
			return;
		});
		
		
		$('#register').click(function(event) {
	
			event.preventDefault();
			
			checkIfEmailExists();
			checkPasswords();
			
			var firstName = $("#firstName").val();
			var lastName = $("#lastName").val();
			var role = $('#role').find(":selected").text();
			var birthDate = new Date($('#birthDate').val());
	        var day = birthDate.getDate();
	        var month = birthDate.getMonth() + 1;
	        var year = birthDate.getFullYear();
	        var date = [day, month, year].join('/');
	        var email = $("#registerEmail").val();
	        var password = $("#password").val();
	        
        	if (firstName == "") {
	        	
				$("#firstNameAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#firstNameAlert").slideUp(500);
				});
	            
				return;
			}

			if (lastName == "") {
	        	
				$("#lastNameAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#lastNameAlert").slideUp(500);
				});
	            
				return;
			}
			
	        if (date == "NaN/NaN/NaN") {
	
				$("#birthDateAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#birthDateAlert").slideUp(500);
				});
				   
				return;
	        }
	        
	        if (role == "Select A Role") {
	        	
				$("#roleAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#roleAlert").slideUp(500);
				});
	            
				return;
			}
	        
			if (email == "") {
	        	
				$("#emptyEmailAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#emptyEmailAlert").slideUp(500);
				});
	            
				return;
			}
			
			if (password == "") {
	        	
				$("#emptyPasswordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#emptyPasswordAlert").slideUp(500);
				});
	            
				return;
			}
			
			if (password.length < 6) {
	        	
				$("#shortPasswordAlert").fadeTo(1000, 500).slideUp(500, function(){
					$("#shortPasswordAlert").slideUp(500);
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
				
				$("#registerForm").submit();
			}
		});