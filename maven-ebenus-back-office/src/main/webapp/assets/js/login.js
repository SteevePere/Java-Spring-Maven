
	$(document).ready(function() {
		
		var newUser = $("#newUser").val();

		if (newUser == "true") {
		
			$("#justRegisteredAlert").fadeTo(4000, 500).slideUp(500, function(){
				$("#justRegisteredAlert").slideUp(500);
			});
            
			return;
		}
	});
	
	
	$('#signIn').click(function(event) {
		
		event.preventDefault();
		
		var email = $("#email").val();
		var password = $("#pass").val();
	
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
		
		try {
           	
			$.post(
				"LoginServlet?ajax_signIn",
				{
					password: password,	
					email: email
				}
				
			).always(function(reply) {
				
				if (reply.status == 401) {
					
					$("#wrongCredentialsAlert").fadeTo(2000, 500).slideUp(500, function(){
						$("#wrongCredentialsAlert").slideUp(500);
					});
					
					return;
				}
				
				else if (reply.status == 402) {
					
					$("#inactiveAccountAlert").fadeTo(2000, 500).slideUp(500, function(){
						$("#inactiveAccountAlert").slideUp(500);
					});
					
					return;
				}
				
				else if (reply.status == 403) {
					
					$("#unauthorizedAlert").fadeTo(2000, 500).slideUp(500, function(){
						$("#unauthorizedAlert").slideUp(500);
					});
					
					return;
				}
				
				else {
					window.location.href = "HomeServlet";
				}
			});
		}
		 
		catch (err) {
			console.log("error : " + err);
		}
	});
	