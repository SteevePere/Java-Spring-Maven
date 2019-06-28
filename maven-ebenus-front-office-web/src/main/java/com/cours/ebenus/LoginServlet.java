package com.cours.ebenus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	public static boolean connected = false;

	
	@Override
	public void init() {
		
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
	}

	
	public LoginServlet() {
		
		super();
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("method") != null) {
			
			if (request.getParameter("method").equals("SIGNOUT")) {

				try {
					
					HttpSession session = request.getSession(false);
					session.invalidate();
					this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
					
					connected = false;
				}
				
				catch (Exception e) {
					
					this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
					connected = false;
				}
				
				return;
			}
		}

		if (request.getSession().getAttribute("user") != null) {
			
			this.getServletContext().getRequestDispatcher("/pages/Dashboard/Home.jsp").forward(request, response);
			
			return;
		} 
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
			
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String queryString = request.getQueryString();
		
		switch (queryString) {
		
			case "ajax_signIn":
				
				ajax_signIn(request, response);
				break;
		}
	}
	
	
	private void ajax_signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userTryingToConnect = serviceFacade.getUserDao().authenticate(request.getParameter("email"), DigestUtils.sha256Hex(request.getParameter("password")));
		
		if (userTryingToConnect != null) {
			
			if (userTryingToConnect.isActive().equals(false)) {
				
				response.setStatus(403);
				return;
			} 
			
			else {
				
				HttpSession session = request.getSession();
				session.setAttribute("user", userTryingToConnect);
				session.setMaxInactiveInterval(180);
				
				connected = true;
				return;
			}
		} 
		
		else {
			
			response.setStatus(401);
			return;
		}
	}


}
