package com.cours.ebenus;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.google.gson.Gson;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static IUserDao userDao = null;
	private static IRoleDao roleDao = null;
	private static HttpSession session = null;
	private static Gson gson = null;
	
	
	@Override
	public void init() throws ServletException {
		
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
		userDao = serviceFacade.getUserDao();
		roleDao = serviceFacade.getRoleDao();
		gson = new Gson();
	}

 
    public UserServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String queryString = request.getQueryString();
		
		if (LoginServlet.connected == true && session.getAttribute("user") != null && queryString != null) {
			
			refreshLoggedUser(session);
			
			Boolean isAuthorized = validateRights(request, response);
			
			if (!isAuthorized)
				return;
			
			switch (queryString) {
			
				case "showAllUsers":
					
					showAllUsers(request, response);
					break;
					
				case "showAllUsers?userAccountToggled":
					
					request.setAttribute("userAccountToggled", true);
					showAllUsers(request, response);
					break;
					
				case "showAllUsers?userAccountEdited":
					
					request.setAttribute("userAccountEdited", true);
					showAllUsers(request, response);
					break;
					
				case "showEditMyAccount":
					
					showEditMyAccount(request, response);
					break;
					
				case "ajax_getAnalytics":
					
					ajax_getAnalytics(request, response);
					break;
					
				case "exportUsersToCsv":
					
					exportUsersToCsv(response);
					break;
					
				case "exportUsersToJson":
					
					exportUsersToJson(response);
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
			
				case "ajax_toggleAccount":
					
					ajax_toggleAccount(request, response);
					break;
					
				case "ajax_editAccount":
					
					ajax_editAccount(request, response);
					break;
					
				case "showEditOtherAccount":
					
					showEditOtherAccount(request, response);
					break;
			}
		}
		
		else {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
		}
	}
	
	
	private Boolean validateRights(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String chief = "Chief";
		
		if (!((User)session.getAttribute("user")).getRole().getRoleName().equals(chief)) {
			
			this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);

			return false;
		}
		
		return true;
	}
	
	
	private void refreshLoggedUser(HttpSession session) {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		User loggedUser = userDao.findUserById(loggedUserId);
		
		session.setAttribute("user", loggedUser);
	}
	
	
	private void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> allusers = userDao.findAllUsers();

		request.setAttribute("allusers", allusers);
		this.getServletContext().getRequestDispatcher("/pages/Users/AllUsers.jsp").forward(request, response);
		
		return;
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
	
	
	private void showEditOtherAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		User userToEdit = userDao.findUserById(Integer.valueOf(userId));
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String birthDate = formatter.format(userToEdit.getBirthDate());
		
		List <Role> roles = roleDao.findAllRoles();
		
		request.setAttribute("userToEdit", userToEdit);
		request.setAttribute("birthDate", birthDate);
		request.setAttribute("roles", roles);
		this.getServletContext().getRequestDispatcher("/pages/Users/EditOtherAccount.jsp").forward(request, response);
		
		return;
	}
	
	
	private void ajax_toggleAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newState = request.getParameter("newState");
		String userId = request.getParameter("userId");
		
		User userToModify = userDao.findUserById(Integer.valueOf(userId));
		Boolean active = null;
		
		switch (newState) {
		
		case "on":
			
			active = true;
			sendEmail(userToModify, active);
			
			break;

		case "off":
			
			active = false;
			sendEmail(userToModify, active);
			break;
		}
		
		userToModify.setActive(active);
		userDao.updateUser(userToModify);
		
		return;
	}
	
	
	private void ajax_editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer loggedUserId = ((User)session.getAttribute("user")).getUserId();
		String accountToEdit = request.getParameter("accountToEdit");
		String myAccount = "myAccount";
		String otherAccount = "otherAccount";
		String userId = request.getParameter("userId");
		String newPassword = null;
		User userToEdit = null;
		
		if (accountToEdit.equals(myAccount)) {
			
			userToEdit = userDao.findUserById(loggedUserId);
			newPassword = request.getParameter("newPassword");
		}
		
		else if (accountToEdit.equals(otherAccount)) {
			
			userToEdit = userDao.findUserById(Integer.valueOf(userId));
			
			String roleName = request.getParameter("newRole");
			Role role = roleDao.findRoleByName(roleName);
			
			userToEdit.setRole(role);
		}
		
		String newFirstName = request.getParameter("newFirstName");
		String newLastName = request.getParameter("newLastName");
		String newEmail = request.getParameter("newEmail");
		Date newBirthDate = null;
		
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
		
		if (newPassword != null && newPassword.length() > 0)
			userToEdit.setPassword(DigestUtils.sha256Hex(newPassword));
		
		if (accountToEdit.equals(myAccount) || ((accountToEdit.equals(otherAccount)) && userToEdit.getUserId() == loggedUserId))
			session.setAttribute("user", userToEdit);
		
		userDao.updateUser(userToEdit);
		
		return;
	}
	
	
	private void ajax_getAnalytics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap <String, Integer> countUsersByRole = countUsersByRole();

		String jsonCountUsersByRole = gson.toJson(countUsersByRole);
		
        PrintWriter responseWriter = response.getWriter();
        response.setContentType("application/json"); 
        
        responseWriter.print(jsonCountUsersByRole);
        responseWriter.close();

        return;
	}
	
	
	private HashMap <String, Integer> countUsersByRole() {
		
		HashMap <String, Integer> countUsersByRole = new HashMap <String, Integer>();
		List<Role> roles = roleDao.findAllRoles();
		List<User> users = userDao.findAllUsers();
		
		for (Role role : roles) {
			
			countUsersByRole.put(role.getRoleName(), 0);
			
			for (User user : users) {
				
				if (user.getRole().equals(role)) {
					
					countUsersByRole.put(role.getRoleName(), countUsersByRole.get(role.getRoleName()) + 1);
				}
			}
		}
		
		return countUsersByRole;
	}
	
	
	private void exportUsersToCsv(HttpServletResponse response) {
		
		response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"users.csv\"");
	    
	    try {
	    	
	        OutputStream outputStream = response.getOutputStream();
	        String outputResult = "ID, First Name, Last Name, E-Mail, Birth Date, Role, Created On, Last Modified On\n";
	        
	        List<User> users = userDao.findAllUsers();
	        
	        for (User user : users) {
	        	
	        	outputResult += user.getUserId() + "," + user.getFirstName() + "," + user.getLastName()
	        	+ "," + user.getEmail() + "," + user.getBirthDate() + "," + user.getRole().getRoleName()
	        	+ "," + user.getCreationDate() + "," + user.getEditDate();
	        	
	        	outputResult += "\n";
	        }
	        
	        outputStream.write(outputResult.getBytes());
	        outputStream.flush();
	        outputStream.close();
	    }
	    
	    catch (Exception e) {
	    	
	    	e.printStackTrace();
	    }
    
		return;
	}
	
	
	private void exportUsersToJson(HttpServletResponse response) {
		
		String json = "";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=\"users.json\"");

	    try {
	        
	        List<User> users = userDao.findAllUsers();
	        
	        for (User user : users) {
	        	
	        	Gson gson = new Gson();
	            json += gson.toJson(user);
	        }
	        
		    response.getWriter().write(json);
	    }
	    
	    catch (Exception e) {
	    	
	    	e.printStackTrace();
	    }
    
		return;
	}

	
	private void sendEmail(User user, Boolean active) {
		
		final String userName = Constants.USER_MAIL;
		final String password = Constants.Mail_PASSWORD;

		Properties props = new Properties();
			
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props,
        		
    		new javax.mail.Authenticator() {
    			
        		protected PasswordAuthentication getPasswordAuthentication() {
    			
    				return new PasswordAuthentication(userName, password);
        		}
        	}
        );

        try {

        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress("from@gmail.com"));
    		message.setRecipients(
            
					Message.RecipientType.TO,
	                InternetAddress.parse(user.getEmail())
	        );
    		
    		if (active) {
    			
    			message.setSubject("LAPD Report Management Application - Your account is now active");
    	        message.setText("Dear " + user.getFirstName() + " " + user.getLastName() + ","
    	                + "\n\n Your may now login to your account.");
    		}
    		
    		else {
    			
    			message.setSubject("LAPD Report Management Application - Your account has been suspended");
    	        message.setText("Dear " + user.getFirstName() + " " + user.getLastName() + ","
    	                + "\n\n We regret to inform you that your account has been temporarily suspended. "
    	                + "Please contact your supervisor for details.");
    		}
    		
	        Transport.send(message);
	    } 
        
        catch (MessagingException e) {
	        e.printStackTrace();
	    }

		return;
	}
	
	
}