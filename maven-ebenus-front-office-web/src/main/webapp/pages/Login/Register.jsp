<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			
				<p style="color: white; margin-bottom: 25px;">Your account will need to be activated by an administrator before you may sign in.</p>

				<form id="registerForm" action="${pageContext.request.contextPath}/RegisterServlet?register" method="Post">
				    
				    <div class="form-group" style="margin-bottom: 30px;">
				    
				    	<div class="row">
				    	
					    	<div class="form-group name1 col-md-6">
			            		<input class="form-control" type="text" placeholder="First Name" name="firstName" id="firstName">
		           			</div>
				
							<div class="form-group name1 col-md-6">
			            		<input class="form-control" type="text" placeholder="Last Name" name="lastName" id="lastName">
			            	</div>
			            
			            </div>
			            
			            <div class="row">
			            
			            	<div class="form-group name1 col-md-6">
					            <input class="form-control" type="text" placeholder="Birth Date" onfocus="(this.type='date')" onblur="(this.type='text')" type="text" name="birthDate" id="birthDate">
					        	<p id="FailDate">${FailDate}</p>
						    </div>
						    
						    <div class="form-group name1 col-md-6">
					            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="role" id="role">
				            		<option value="" disabled selected>Select A Role</option>
					                <c:forEach items="${AllRoles}" var="rolesItems">
					                    <option value="${rolesItems.getRoleName()}" ><c:out value="${rolesItems.getRoleName()}"/></option>
					                </c:forEach>
					            </select>
				            </div>
			            
			            </div>
			            
			            <div class="row">
			            
			            	<div class="form-group name1 col-md-12">
				            	<input class="form-control" type="email" placeholder="Email" name="registerEmail" id="registerEmail">
				            </div>
				            
			            </div>
			            
			           <div class="row">
				            
				            <div class="form-group name1 col-md-6">
				            	<input class="form-control" type="password" placeholder="Password" name="password" id="password">
				            </div>
				            
				            <div class="form-group name1 col-md-6">
				            	<input class="form-control" type="password" placeholder="Confim Password" name="confirmPassword" id="confirmPassword">
						    </div>
						    
					    </div>
					    
					</div>
					
					<button id="go back" type="button" onclick="location.href='LoginServlet';" style="width: 200px; margin-right: 12px;" class="btn btn-outline-primary my-2 my-sm-0"><i id="signInIcon" class="fas fa-undo-alt"></i>Back to Sign In</button>
				    <button id="register" type="button" style="width: 200px; margin-left: 12px;" class="btn btn-danger"><i id="registerIcon" class="fas fa-user-plus"></i>Register</button>
					
				</form>
				
				<div id="lastNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please tell us your last name
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="firstNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please tell us your first name
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="birthDateAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please tell us your birth date
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="roleAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please choose a Role for your account
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="emptyEmailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please tell us your e-mail address
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="emptyPasswordAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please choose a password
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="shortPasswordAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Your password must be at least 6 characters long
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="emailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>This e-mail address is already in use
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="passwordAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Passwords don't match
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
			</div> <!-- Main-Form -->
		</div> <!-- Container -->
	</div> <!-- Content -->
	
</body>

<script src= "./assets/js/register.js"></script>

</html>
