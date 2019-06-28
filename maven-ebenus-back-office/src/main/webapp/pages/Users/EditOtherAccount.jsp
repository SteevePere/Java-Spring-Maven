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
			
				<c:choose>
			        <c:when test="${userToEdit.getRole().getRoleName() == 'Chief'}"><i id="createIcon" class="fas fa-chess-king" style="font-size: 120px;"></i></c:when>
			        <c:when test="${userToEdit.getRole().getRoleName() == 'Detective'}"><i id="createIcon" class="fas fa-user-secret" style="font-size: 120px;"></i></c:when>
					<c:otherwise><i id="createIcon" class="fas fa-user-shield" style="font-size: 120px;"></i></c:otherwise>
				</c:choose>
			
			</div>
			
			<h1><i id="createIcon" class="fas fa-user-alt"></i>${userToEdit.getFirstName()} ${userToEdit.getLastName()}'s Account</h1>
			
			<div class="main-form">
				
				<form id="editMyAccountForm" action="" method="post" class="form" role="form">
					
					<div class="form-group" style="margin-bottom: 30px;">
				    
				    	<div class="row">
				    	
				    		<input id="userId" type="hidden" name="userId" value="${userToEdit.getUserId()}"/>
				    		<input id="oldEmail" type="hidden" name="oldEmail" value="${userToEdit.getEmail()}"/>
				    		<input id="oldRole" type="hidden" name="oldRole" value="${userToEdit.getRole().getRoleName()}"/>
				    	
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
					            <input class="form-control" type="text" placeholder="Birth Date" onfocus="(this.type='date')" onblur="(this.type='text')" type="text"  value="${birthDate}" name="newBirthDate" id="newBirthDate">
						    </div>
						    
			            </div>
			            
			            <div class="row">
			            
			           		<div class="form-group name1 col-md-6">
				            
					            <select class="form-control" id="newRole" name="newRole">
									
									<option value="placeholder" disabled>Role</option>
									
									<c:forEach items="${roles}" var="role">
										<option 
											<c:if test="${role.getRoleName() == userToEdit.getRole().getRoleName()}">
												selected
											</c:if>
											id="${role.getRoleName()}" 
											value="${role.getRoleName()}">
											${role.getRoleName()}
										</option>
									</c:forEach>
									
								</select>
							
							</div>
							
						</div>
			            
					</div>
					<!-- Form -->
					
					<!-- Buttons -->
					<div class="button-div">
						<input type=button onclick="location.href='UserServlet?showAllUsers';" value="Go Back" class="btn btn-outline-danger">
						<input id="saveAccount" type=button value="Save Changes" class="btn btn-outline-info my-2 my-sm-0">
					</div>
					<!-- Buttons -->
					
					<br>
					<br>
					
					<!-- Alerts -->
					<div id="newLastNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter user's last name
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					</div>
					<div id="newFirstNameAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter user's first name
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="newBirthDateAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter user's birth date
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="emptyNewEmailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please enter user's e-mail address
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="emailAlert" class="alert alert-danger alert-dismissible" style='display:none;'>This e-mail address is already in use
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="adminPrivilegesAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Are you sure ? User will lose their administrator privileges !
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

<script src= "./assets/js/editOtherAccount.js"></script>

</html>