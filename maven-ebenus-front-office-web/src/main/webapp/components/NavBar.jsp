<%!
	public void NavBar() {
%>
		<nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
			
			<img src="https://vignette.wikia.nocookie.net/scream/images/7/76/LAPD-Logo.png/revision/latest?cb=20181014120159" style="height: 40px; margin-left: 5px;" alt="LAPD">
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
			
				<ul class="navbar-nav mr-auto">
				
					<li class="nav-item active">
						<a class="nav-link" href="HomeServlet"><i id="homeicon" class="fas fa-home"></i>Home <span class="sr-only">(current)</span></a>
					</li>
					
					<li class="nav-item active">
						<div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i id="managereportsicon" class="fas fa-folder-open"></i>Reports
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="ReportServlet?showCreateReport"><i id="createIcon-small" class="fas fa-pencil-alt"></i>File New</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="ReportServlet?showAllReports"><i id="listIcon-small" class="fas fa-list"></i>Browse</a>
							</div>
						</div>
					</li>
					
					<c:if test="${user.getRole().getRoleName() == 'Chief'}">
					
						<li class="nav-item active">
							<a class="nav-link" target="_blank" href="AdminServlet?showAdminArea"><i id="manageusersicon" class="fas fa-user-lock" style="color: #dc3545;"></i>Admin Area</a>
						</li>
					
					</c:if>
					
				</ul>
				
				<div class="form-inline my-2 my-lg-0">
				
					<div class="nav-item dropdown">
					
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							${user.getFirstName()} ${user.getLastName()} - ${user.getRole().getRoleName()}
							
							<c:choose>
						        <c:when test="${user.getRole().getRoleName() == 'Chief'}"><i id="chieficon" class="fas fa-chess-king"></i></c:when>
						        <c:when test="${user.getRole().getRoleName() == 'Detective'}"><i id="detectiveicon" class="fas fa-user-secret"></i></c:when>
       							<c:otherwise><i id="agenticon" class="fas fa-user-shield"></i></c:otherwise>
    						</c:choose>
    						
						</a>
						
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="UserServlet?showEditMyAccount"><i id="myAccountIcon" class="fas fa-user"></i>My Account</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="LoginServlet?method=SIGNOUT"><i id="signouticon" class="fas fa-sign-out-alt"></i>Sign Out</a>
						</div>
						
					</div>
				</div>
			</div>
		</nav>
<%!
	}
%>