<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>
<%!public List<User> users;%>

<html>

<head>
	<% Header();%>
</head>

<body>

	<% NavBar();%>	
	
	<div class="content">
	
		<div class="container">
		
			<div style="display:inline-block;">
				<h1><i id="listIcon" class="fas fa-list"></i>Manage Users</h1>
			</div>
			
			<c:if test="${user.getRole().getRoleName()=='Chief'}">
			
				<div style="display:inline-block;">
				
					<a class="downloadToJsonLink" href="UserServlet?exportUsersToJson">
						<i id="jsonExportIcon" title="Download as Json" class="fab fa-js-square"></i>
					</a>
					
					<a class="downloadToCsvLink" href="UserServlet?exportUsersToCsv">
						<i id="csvExportIcon" title="Download as CSV" class="fas fa-file-csv"></i>
					</a>
					
				</div>
				
			</c:if>
			
			<div class="main-form">
		
				<table class="table table-striped table-bordered" id="allUsers" align="center">
			
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Creation Date</th>
							<th>Role</th>
							<th>Account Status</th>
							
							<c:if test="${user.getRole().getRoleName()=='Chief' || user.getRole().getRoleName()=='Detective'}">
								<th>Actions</th>
							</c:if>
							
						</tr>
					</thead>
					
					<tbody>
					
						<c:forEach items="${allusers}" var="userToShow">
						
							<tr 
								id="${userToShow.getUserId()}"
								<c:choose>
				        			<c:when test="${userToShow.isActive() == 'True'}">
				        			class="activeRow"
				        			</c:when>
									<c:otherwise>
									class="inactiveRow"
									</c:otherwise>
								</c:choose>
							>
							
								<td><c:out value="${userToShow.getFirstName()}" /></td>
								<td><c:out value="${userToShow.getLastName()}" /></td>
								<td><c:out value="${userToShow.getEmail()}" /></td>
								<td><c:out value="${userToShow.getEditDate()}" /></td>
								<td>

								<c:choose>
							        <c:when test="${userToShow.getRole().getRoleName() == 'Chief'}"><i id="chieficon" class="fas fa-chess-king"></i></c:when>
							        <c:when test="${userToShow.getRole().getRoleName() == 'Detective'}"><i id="detectiveicon" class="fas fa-user-secret"></i></c:when>
	       							<c:otherwise><i id="agenticon" class="fas fa-user-shield"></i></c:otherwise>
	    						</c:choose>
								
								<c:out value="${userToShow.getRole().getRoleName()}" /></td>
									
								<c:choose>
				        			<c:when test="${userToShow.isActive() == 'True'}">
				        				<td><c:out value="Active" /></td>
				        			</c:when>
									<c:otherwise>
										<td><c:out value="Inactive" /></td>
									</c:otherwise>
								</c:choose>
								
								<c:if test="${user.getRole().getRoleName()=='Chief' || user.getRole().getRoleName()=='Detective'}">
									
									<td class="actions">
									
										<form id="editUserForm" action="${pageContext.request.contextPath}/UserServlet?showEditOtherAccount" method="Post">
										
										<input 
											id="userId"
											name="userId" 
											type="hidden"
											value="${userToShow.getUserId()}">
											
										<button
											type="submit"
											class="editButton"
											id="editButton" 
											name="${userToShow.getUserId()}" 
											title="Edit ${userToShow.getFirstName()} ${userToShow.getLastName()}'s Account">
											<i id="editicon" class="fas fa-pencil-alt"></i>
										</button>
										
										</form>
										
										<c:if test="${user.getRole().getRoleName()=='Chief'}">
										
											<c:choose>
											
							        			<c:when test="${userToShow.isActive() == 'True' && userToShow.getRole().getRoleName() != 'Chief'}">
							        				<button
														id="deleteButton" 
														name="${userToShow.getUserId()}"
														class="deleteButton" 
														onclick="toggleAccount(this.name, 'off')" 
														title="Suspend ${userToShow.getFirstName()} ${userToShow.getLastName()}'s Account">
														<i id="switchOff" class="far fa-check-circle"></i>
													</button>
							        			</c:when>
							        			
							        			<c:when test="${userToShow.getRole().getRoleName() == 'Chief' && userToShow.isActive() == 'True'}">
							        				<button
														id="deleteButton"
														name="${userToShow.getUserId()}"
														disabled
														class="deleteButtonDisabled" 
														title="Chief Accounts Cannot Be Disabled">
														<i id="switchOffDisbaled" class="far fa-check-circle"></i>
													</button>
							        			</c:when>
							        			
	       										<c:otherwise>
		       										<button
														id="deleteButton" 
														name="${userToShow.getUserId()}"
														class="deleteButton" 
														onclick="toggleAccount(this.name, 'on')" 
														title="Activate ${userToShow.getFirstName()} ${userToShow.getLastName()}'s Account">
														<i id="switchOn" class="far fa-check-circle"></i>
													</button>
	       										</c:otherwise>
	       										
	   										</c:choose>
	   										
   										</c:if>
	   										
									</td>
									
								</c:if>
								
							</tr>
							
						</c:forEach>
						
					</tbody>
			
				</table>
				
				<c:if test="${userAccountToggled}">
					<div id="userAccountToggled"></div>
				</c:if>
				
				<div id="userAccountToggledAlert" class="alert alert-success alert-dismissible" style='display:none; margin-top: 20px;'>User account updated. User has been notified by e-mail.
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
				<c:if test="${userAccountEdited}">
					<div id="userAccountEdited"></div>
				</c:if>
				
				<div id="userAccountEditedAlert" class="alert alert-success alert-dismissible" style='display:none; margin-top: 20px;'>User account has been modified
					<button data-dismiss="alert" type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
			</div>
			
		</div>
		
	</div>
	
</body>

<script src= "./assets/js/allUsers.js"></script>

</html>