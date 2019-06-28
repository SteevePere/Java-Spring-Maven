package com.cours.ebenus;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Incident;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static HttpSession session = null;
	
	
	@Override
	public void init() throws ServletException {
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
	}

 
    public HomeServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null) {
			
			refreshLoggedUser(session);
			showHome(request,response);
		} 
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		refreshLoggedUser(session);
		return;
	}

	
	private void showHome(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		List<Incident> incidents = serviceFacade.getIncidentDao().findAllIncidents();
		request.setAttribute("incidents", incidents);
		
		this.getServletContext().getRequestDispatcher("/pages/Dashboard/Home.jsp").forward(request, response);
		
		return;
	}
	
	
	private void refreshLoggedUser(HttpSession session) {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		User loggedUser = serviceFacade.getUserDao().findUserById(loggedUserId);
		
		session.setAttribute("user", loggedUser);
	}
	
	
}
