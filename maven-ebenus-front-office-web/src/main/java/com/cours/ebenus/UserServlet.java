package com.cours.ebenus;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static IUserDao userDao = null;
	private static HttpSession session = null;
	
	
	@Override
	public void init() throws ServletException {
		
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
		userDao = serviceFacade.getUserDao();
	}

 
    public UserServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String queryString = request.getQueryString();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null && queryString != null) {
			
			refreshLoggedUser(session);
			
			switch (queryString) {
			
				case "showEditMyAccount":
					
					showEditMyAccount(request, response);
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
			
				case "ajax_editMyAccount":
					
					ajax_editMyAccount(request, response);
					break;
			}
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}
	
	
	private void refreshLoggedUser(HttpSession session) {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		User loggedUser = userDao.findUserById(loggedUserId);
		
		session.setAttribute("user", loggedUser);
	}
	
	
	private void showEditMyAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		User userToEdit = userDao.findUserById(loggedUserId);
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String myBirthDate = formatter.format(userToEdit.getBirthDate());
		
		request.setAttribute("userToEdit", userToEdit);
		request.setAttribute("myBirthDate", myBirthDate);
		this.getServletContext().getRequestDispatcher("/pages/Users/EditMyAccount.jsp").forward(request, response);
		
		return;
	}
	
	
	private void ajax_editMyAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userToEdit = (User)session.getAttribute("user");
		Date newBirthDate = null;
		
		String newFirstName = request.getParameter("newFirstName");
		String newLastName = request.getParameter("newLastName");
		String newEmail = request.getParameter("newEmail");
		String newPassword = request.getParameter("newPassword");
		
		try {
			newBirthDate =  new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("newBirthDate"));
			userToEdit.setBirthDate(newBirthDate);
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		userToEdit.setFirstName(newFirstName);
		userToEdit.setLastName(newLastName);
		userToEdit.setEmail(newEmail);
		
		if (newPassword.length() > 0)
			userToEdit.setPassword(DigestUtils.sha256Hex(newPassword));
		
		userDao.updateUser(userToEdit);
		session.setAttribute("user", userToEdit);	
		return;
	}
	
	
}