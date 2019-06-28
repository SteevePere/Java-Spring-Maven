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
							<th>Author</th>
							<th>Filed</th>
							<th>Last Edited</th>
							
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
								<td><c:out value="${incident.getCreatedBy().getFirstName()} ${incident.getCreatedBy().getLastName()}" /></td>
								<td><c:out value="${incident.getCreationDate()}" /></td>
								<td><c:out value="${incident.getEditDate()}" /></td>
								
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
										
										<c:if test="${user.getRole().getRoleName() == 'Chief'}">
	
											<button
												id="deleteButton" 
												name="${incident.getIncidentId()}"
												class="deleteButton" 
												data-toggle="modal"
												data-id="${incident.getIncidentId()}"
												data-target="#delete${incident.getIncidentId()}"
												title="Delete Report">
												<i id="deleteicon" class="fas fa-trash-alt"></i>
											</button>
											
										</c:if>
										
									</td>
								
								</c:if>
								
							</tr>
							<!--Delete confirmation Modal -->
							<div class="modal fade" id="delete${incident.getIncidentId()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								
								<div class="modal-dialog" role="document">
							
									<div class="modal-content" style="margin-top: 200px;">
										
										<div class="modal-body">
											<i id="createIcon" style="font-size: 50px; color: #dc3545; margin-bottom: 15px;" class="fas fa-exclamation-circle"></i>
											<h2>Are you sure?</h2>
										</div>
										
										<div class="modal-footer">
											<p><input type="button" data-dismiss="modal" value="Cancel" class="btn btn-outline-info"></p>
											<p><input type="button" name="${incident.getIncidentId()}" onclick="deleteReport(this.name)"  value="Delete" class="btn btn-outline-danger"></p>
										</div>
										
									</div>
								</div>
							</div>
							<!--End of Delete confirmation Modal -->
						</c:forEach>
						
					</tbody>
					
				</table>
				
			</div>
		</div>
	</div>
</body>

<script src= "./assets/js/allReports.js"></script>

</html>