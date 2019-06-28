<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Incident"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>
<%!public List<User> users;%>
<%!public List<Incident> incidents;%>

<html>

<head>
	<% Header();%>
</head>

<body>

	<% NavBar();%>
	
	<div class="content">
	
		<div class="container">
		
			<h1><i id="listicon" class="fas fa-list"></i>Browse Reports</h1>
			
			<div class="main-form">
		
				<table class="table table-striped table-bordered" id="allReports" align="center">
				
					<thead>
					
						<tr>
							<th>ID</th>
							<th>Type</th>
							<th>Date</th>
							<th>Location</th>
							
							<c:if test="${user.getRole().getRoleName() == 'Chief' || user.getRole().getRoleName() == 'Detective'}">
							<th>Actions</th>
							</c:if>
							
						</tr>
						
					</thead>
					
					<tbody>
					
						<c:forEach items="${AllReports}" var="incident">
						
							<tr id="${incident.getIncidentId()}">
								
								<td><c:out value="${incident.getIncidentId()}" /></td>
								<td><c:out value="${incident.getIncidentType().getIncidentTypeName()}" /></td>
								<td><c:out value="${incident.getIncidentDate()}" /></td>
								<td><c:out value="${incident.getIncidentLatitude()}, ${incident.getIncidentLongitude()}" /></td>
								
								<c:if test="${user.getRole().getRoleName() == 'Chief' || user.getRole().getRoleName() == 'Detective'}">
								
									<td class="actions">
									
										<form id="editReportForm" action="${pageContext.request.contextPath}/ReportServlet?showEditReport" method="Post">
										
										<input 
											id="incidentId"
											name="incidentId" 
											type="hidden"
											value="${incident.getIncidentId()}">
											
										<button
											type="submit"
											class="editButton"
											id="editButton" 
											name="${incident.getIncidentId()}" 
											title="Edit Report">
											<i id="editicon" class="fas fa-pencil-alt"></i>
										</button>
										
										</form>
										
									</td>
								
								</c:if>
								
							</tr>
							
						</c:forEach>
						
					</tbody>
					
				</table>
				
			</div>
		</div>
	</div>
</body>

<script src= "./assets/js/allReports.js"></script>

</html>