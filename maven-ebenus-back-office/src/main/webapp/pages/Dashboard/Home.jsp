<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Incident"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>
<%!public List<Incident> incidents;%>

<html>

<head>
	<% Header();%>
</head>

<body>

	<% NavBar();%>	
	
		<select class="form-control" id="incidents" name="incidents" style="display: none;">
							
			<c:forEach items="${incidents}" var="incident">
				<option 
					id="${incident.getIncidentId()}" 
					value="${incident.getIncidentId()}, ${incident.getIncidentDate()}, ${incident.getIncidentType().getIncidentTypeName()}, ${incident.getIncidentLatitude()}, ${incident.getIncidentLongitude()}">
				</option>
			</c:forEach>
			
		</select>
		
		<input id="userRole" type="hidden" name="userRole" value="${user.getRole().getRoleName()}"/>
	
		<!-- Map -->
		<div class="map-container">
		    <div id="homeMap"></div>
		</div>
		
		<div class="leaflet-bottom leaflet-right">
			<button 
				class="btnStyle span3 leaflet-control" 
				title="New Report" 
				onclick="location.href='ReportServlet?showCreateReport';" 
				style="background-color: transparent; border-width: 0;">
				<i id="homeNewReport" class="fas fa-pencil-alt"></i>
			</button>
		</div>
		<!-- Map -->
	
</body>

<script src= "./assets/js/home.js"></script>

</html>