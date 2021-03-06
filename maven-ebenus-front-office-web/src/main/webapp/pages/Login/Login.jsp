<%@include file="../../components/Header.jsp"%>

<!DOCTYPE HTML>

<html>

<head>
	<% Header();%>
</head>

<body>
	
	<div class="content">
	
		<div class="container" style="text-align: center; padding-top: 10%">
		
			<a target="_blank" href="http://www.lapdonline.org" title="LAPD Website"><img id="LAPD_logo" src="https://vignette.wikia.nocookie.net/scream/images/7/76/LAPD-Logo.png/revision/latest?cb=20181014120159" style="height: 300px;" alt="LAPD"></a>
			
			<div class="main-form">
				
				<form id="loginForm" class="form" role="form">
					
					<div class="form-group" style="margin-bottom: 30px;">
					
						<input id="newUser" type="hidden" name="newUser" value="${newUser}"/>
					
						<div class="form-group">
							<input required style="display: inline-block" type="text" name="email" value="" id="email" class="form-control" placeholder="Email" title="Email">
						</div>
						
						<div class="form-group">
							<input required style="display: inline-block" type="password" name="password" class="form-control" placeholder="Password" id="pass" title="Password">
						</div>
						
					</div>
					<!-- Form -->
					<button id="register" type=button onclick="location.href='RegisterServlet?showRegister';" style="width: 200px;" class="btn btn-outline-primary my-2 my-sm-0"><i id="registerIcon" class="fas fa-user-plus"></i>Register</button>
					<button id="signIn" type=button style="width: 200px;" class="btn btn-danger"><i id="signInIcon" class="fas fa-sign-in-alt"></i>Sign In</button>
					
				</form>
				
				<br>
				
				<div id="justRegisteredAlert" class="alert alert-success alert-dismissible" style='display:none;'>Your account has been created. An e-mail will be sent to ${newUserEmail} when it has been activated.
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="emptyEmailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter your e-mail address
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="emptyPasswordAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter your password
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="wrongCredentialsAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Your credentials are incorrect
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="inactiveAccountAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Your account has been deactivated or is pending activation. Please contact your administrator.
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
			</div> <!-- Main-Form -->
		</div> <!-- Container -->
	</div> <!-- Content -->
	
</body>

<script src= "./assets/js/login.js"></script>

</html>