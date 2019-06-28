package com.cours.ebenus;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.User;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static HttpSession session = null;
	
	
	@Override
	public void init() throws ServletException {
		
	}

 
    public AdminServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String queryString = request.getQueryString();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null && queryString != null) {
			
			Boolean isAuthorized = validateRights(request, response);
			
			if (!isAuthorized)
				return;
			
			switch (queryString) {
			
				case "showAdminArea":
					
					showAdminArea(request, response);
					break;
			}
			
		} 
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return;
	}
	
	
	private Boolean validateRights(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String chief = "Chief";
		session = request.getSession();
		
		if (!((User)session.getAttribute("user")).getRole().getRoleName().equals(chief)) {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
			
			return false;
		}
		
		return true;
	}
	
	
	private void showAdminArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("/maven-ebenus-back-office/LoginServlet?method=SIGNOUT");
		
		return;
	}
	
	
}