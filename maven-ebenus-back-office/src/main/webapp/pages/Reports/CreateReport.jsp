<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.IncidentType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>
<%!public List<User> users;%>
<%!public List<IncidentType> incidentTypes;%>

<html>

<head>
	<% Header();%>
</head>

<body>

	<% NavBar();%>
	
	<div class="content">
	
		<div class="container">
		
			<h1><i id="createIcon" class="fas fa-pencil-alt"></i>New Report</h1>
			
			<div class="main-form">
				
				<form id="createReport" action="" method="post" class="form" role="form">
					
					<div class="form-group">
					
						<!-- Form -->
						<input id="createdBy" type="hidden" name="createdBy" value="${user.getUserId()}"/>
						
						<div class="form-group">
							<input id="incidentDate" type="text" class="form-control" style="width: 25%;" placeholder="Incident Date" onfocus="(this.type='date')" onblur="(this.type='text')" name="incidentDate"/>
						</div>
						
						<select class="form-control" id="incidentType" name="incidentType" style="width: 49.5%; float: left;">
							
							<option value="placeholder" disabled selected>Incident Type</option>
							<option value="other">Add New Type</option>
							
							<c:forEach items="${incidentTypes}" var="incidentType">
								<option value="${incidentType.getIncidentTypeName()}">${incidentType.getIncidentTypeName()}</option>
							</c:forEach>
							
						</select>
						
						 <input 
						 	maxlength="25" 
						 	class="form-control" 
						 	placeholder="New Type" 
						 	id="newType" name="newType" 
						 	style="display:none; 
						 	width: 49.5%; 
						 	float: right;"
					 	/>
					 	
					</div>
					<!-- Form -->
					
					<br>
					<br>
					
					<!-- Map -->
					<div class="map-container">
					    <div id="map" style="height: 400px; border-radius: 0px;"></div>
					</div>
					<!-- Map -->
					
					<!-- Alerts -->
					<div id="incidentTypeAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please choose an incident type
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="newTypeAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please fill out new incident type name
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="incidentDateAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please choose incident date
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="geolocAlert" class="alert alert-danger alert-dismissible" style='display:none;'>Please locate incident
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="successAlert" class="alert alert-success alert-dismissible" style='display:none;'>Report has been filed
						<button data-dismiss="alert" type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<!-- Alerts -->
					
					<!-- Buttons -->
					<div class="button-div">
						<input type=button onclick="location.href='HomeServlet';" value="Go Back" class="btn btn-outline-danger">
						<input id="submit" type=submit value="File Report" class="btn btn-outline-info my-2 my-sm-0">
					</div>
					<!-- Buttons -->
					
				</form>
			</div> <!-- Main-Form -->
		</div> <!-- Container -->
	</div> <!-- Content -->
	
</body>

<script src= "./assets/js/createReport.js"></script>

</html>