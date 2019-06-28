<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>

<html>

<head>
	<% Header();%>
</head>

<body>

	<% NavBar();%>
	
	<div class="content">
	
		<div class="container">
		
			<div style="text-align: center; margin: 50px 0 80px 0;">
			
				<i id="createIcon" class="fas fa-chess-king" style="font-size: 120px;"></i>
		
			</div>
			
			<h1><i id="createIcon" class="fas fa-user-alt"></i>My Account</h1>
			
			<div class="main-form">
				
				<form id="editMyAccountForm" action="" method="post" class="form" role="form">
					
					<div class="form-group" style="margin-bottom: 30px;">
				    
				    	<div class="row">
				    	
				    		<input id="oldEmail" type="hidden" name="oldEmail" value="${userToEdit.getEmail()}"/>
				    	
					    	<div class="form-group name1 col-md-6">
			            		<input class="form-control" type="text" value="${userToEdit.getFirstName()}" placeholder="First Name" name="newFirstName" id="newFirstName">
		           			</div>
				
							<div class="form-group name1 col-md-6">
			            		<input class="form-control" type="text" value="${userToEdit.getLastName()}" placeholder="Last Name" name="newLastName" id="newLastName">
			            	</div>
			            
			            </div>
			            
			            <div class="row">
			            	
			            	<div class="form-group name1 col-md-6">
				            	<input class="form-control" type="email" value="${userToEdit.getEmail()}" placeholder="Email" name="newEmail" id="newEmail">
				            </div>
			            
			            	<div class="form-group name1 col-md-6">
					            <input class="form-control" type="text" placeholder="Birth Date" onfocus="(this.type='date')" onblur="(this.type='text')" type="text"  value="${myBirthDate}" name="newBirthDate" id="newBirthDate">
						    </div>
						    
			            </div>
			            
			           <div class="row">
				            
				            <div class="form-group name1 col-md-6">
				            	<input class="form-control" type="password" placeholder="New Password" name="newPassword" id="newPassword">
				            </div>
				            
				            <div class="form-group name1 col-md-6">
				            	<input class="form-control" type="password" placeholder="Confim New Password" name="confirmNewPassword" id="confirmNewPassword">
						    </div>
						    
					    </div>
					    
					</div>
					<!-- Form -->
					
					<!-- Buttons -->
					<div class="button-div">
						<input type=button onclick="location.href='HomeServlet';" value="Go Back" class="btn btn-outline-danger">
						<input id="saveMyAccount" type=button value="Save My Changes" class="btn btn-outline-info my-2 my-sm-0">
					</div>
					<!-- Buttons -->
					
					<br>
					<br>
					
					<!-- Alerts -->
					<div id="newLastNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Surely you still have a last name
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					</div>
					<div id="newFirstNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Surely you still have a first name
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="newBirthDateAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Surely you still have a birth date
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="emptyNewEmailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>We still need your e-mail address
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="shortNewPasswordAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Your new password must be at least 6 characters long
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
					<div id="successAlert" class="alert alert-success alert-dismissible" style='display:none;'>Your account has been modified
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<!-- Alerts -->
					
				</form>
			</div> <!-- Main-Form -->
		</div> <!-- Container -->
	</div> <!-- Content -->
	
</body>

<script src= "./assets/js/editMyAccount.js"></script>

</html>