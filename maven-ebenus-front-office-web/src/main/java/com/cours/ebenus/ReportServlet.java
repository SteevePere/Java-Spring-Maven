package com.cours.ebenus;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Incident;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static IIncidentDao incidentDao = null;
	private static IIncidentTypeDao incidentTypeDao = null;
	private static HttpSession session = null;
	
	
	@Override
	public void init() throws ServletException {
		
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
		incidentDao = serviceFacade.getIncidentDao();
		incidentTypeDao = serviceFacade.getIncidentTypeDao();
	}

 
    public ReportServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String queryString = request.getQueryString();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null && queryString != null) {
			
			refreshLoggedUser(session);
			
			switch (queryString) {
			
				case "showAllReports":
					
					showAllReports(request, response);
					break;
					
				case "showCreateReport":
					
					showCreateReport(request, response);
					break;
					
				case "showEditReport":
					
					this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
					break;
					
				case "showAnalytics":
					
					showAnalytics(request, response);
					break;
			}
			
		} 
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String queryString = request.getQueryString();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null && queryString != null) {
			
			refreshLoggedUser(session);
			
			switch (queryString) {
			
				case "ajax_createReport":
					
					ajax_createReport(request, response);
					break;
					
				case "ajax_editReport":
					
					ajax_editReport(request, response);
					break;
					
				case "ajax_deleteReport":
					
					ajax_deleteReport(request, response);
					break;
					
				case "showEditReport":
					
					showEditReport(request, response);
					break;
			}
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}
	
	
	private void validateRights(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String chief = "Chief";
		String detective = "Detective";
		
		if (!((User)session.getAttribute("user")).getRole().getRoleName().equals(chief) 
				&& !((User)session.getAttribute("user")).getRole().getRoleName().equals(detective)) {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
			return;
		}
		
		return;
	}
	
	
	private void refreshLoggedUser(HttpSession session) {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		User loggedUser = serviceFacade.getUserDao().findUserById(loggedUserId);
		
		session.setAttribute("user", loggedUser);
	}

	
	private void showAllReports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Incident> incidents = incidentDao.findAllIncidents();
		
		request.setAttribute("AllReports", incidents);
		this.getServletContext().getRequestDispatcher("/pages/Reports/AllReports.jsp").forward(request, response);
		
		return;
	}
	
	
	private void showCreateReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<IncidentType> incidentTypes = incidentTypeDao.findAllIncidentTypes();
		
		request.setAttribute("incidentTypes", incidentTypes);
		this.getServletContext().getRequestDispatcher("/pages/Reports/CreateReport.jsp").forward(request, response);
		
		return;
	}
	
	
	private void showEditReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		validateRights(request, response);
		
		Incident incidentToEdit = null;
		Integer incidentId = Integer.valueOf(request.getParameter("incidentId"));
		List<Incident> incidentsFound = incidentDao.findIncidentsBy(incidentId, "incidentId");
		List<IncidentType> incidentTypes = incidentTypeDao.findAllIncidentTypes();
		
		for (Incident incidentFound : incidentsFound) {

			incidentToEdit = incidentFound;
		}
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String incidentDateToEdit = formatter.format(incidentToEdit.getIncidentDate());
		
		request.setAttribute("incidentDateToEdit", incidentDateToEdit);
		request.setAttribute("incidentTypes", incidentTypes);
		request.setAttribute("incidentToEdit", incidentToEdit);
		
		this.getServletContext().getRequestDispatcher("/pages/Reports/EditReport.jsp").forward(request, response);
		
		return;
	}
	
	
	private void showAnalytics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/pages/Analytics/Analytics.jsp").forward(request, response);
		
        return;
	}
	
	
	private void ajax_createReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.valueOf(request.getParameter("createdBy"));
		User createdBy = new User(userId);
		
		Date incidentDate = null;
		Date creationDate = new Date();
		Date editDate = new Date();
		
		Double incidentLatitude = Double.parseDouble(request.getParameter("incidentLatitude"));
		Double incidentLongitude = Double.parseDouble(request.getParameter("incidentLongitude"));
		
		IncidentType incidentType = incidentTypeDao.findIncidentTypeByName(request.getParameter("incidentType"));
		
		if (incidentType == null) {
			
			incidentType = new IncidentType(request.getParameter("incidentType"));
			incidentTypeDao.createIncidentType(incidentType);
		}
		
		else
			incidentType = incidentTypeDao.findIncidentTypeById(incidentType.getIncidentTypeId());
		
		try {
			incidentDate =  new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("incidentDate"));
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Incident incidentToCreate = new Incident(incidentType, incidentDate, incidentLatitude, 
							incidentLongitude, createdBy, creationDate, editDate);
		incidentDao.createIncident(incidentToCreate);
		
		response.setContentType("text/plain");
		
		return;
	}
	
	
	private void ajax_editReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer incidentId = Integer.valueOf(request.getParameter("incidentId"));
		Date incidentDate = null;
		Date editDate = new Date();
		
		Double incidentLatitude = Double.parseDouble(request.getParameter("incidentLatitude"));
		Double incidentLongitude = Double.parseDouble(request.getParameter("incidentLongitude"));
		IncidentType incidentType = incidentTypeDao.findIncidentTypeByName(request.getParameter("incidentType"));
		
		if (incidentType == null) {
			
			incidentType = new IncidentType(request.getParameter("incidentType"));
			incidentTypeDao.createIncidentType(incidentType);
		}
		
		else
			incidentType = incidentTypeDao.findIncidentTypeById(incidentType.getIncidentTypeId());
		
		try {
			incidentDate =  new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("incidentDate"));
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Incident incidentToUpdate = new Incident(incidentId, incidentType, incidentDate, incidentLatitude, 
							incidentLongitude, editDate);
		incidentDao.updateIncident(incidentToUpdate);
		
		response.setContentType("text/plain");

		return;
	}
	
	
	private void ajax_deleteReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Incident incidentToDelete = null;
		Integer incidentId = Integer.valueOf(request.getParameter("incidentId"));
		List<Incident> incidentsFound= incidentDao.findIncidentsBy(incidentId, "incidentId");

		for (Incident incidentFound : incidentsFound) {

			incidentToDelete = incidentFound;
		}
		
		incidentDao.deleteIncident(incidentToDelete);
		
		response.setContentType("text/plain");

		return;
	}
	
	
}
