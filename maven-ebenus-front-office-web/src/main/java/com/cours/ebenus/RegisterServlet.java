package com.cours.ebenus;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.EbenusException;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static IUserDao userDao = null;
	private static IRoleDao roleDao = null;

	
	@Override
	public void init() {
		
		AbstractDaoFactory.FactoryDaoType dataType = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
		serviceFacade = new ServiceFacade(dataType);
		userDao = serviceFacade.getUserDao();
		roleDao = serviceFacade.getRoleDao();
	}

	
	public RegisterServlet() {
		
		super();
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String queryString = request.getQueryString();
		
		switch (queryString) {
		
			case "showRegister":
				
				showRegister(request, response);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String queryString = request.getQueryString();
		
		switch (queryString) {
		
			case "register":
				
				register(request, response);
				break;
				
			case "ajax_checkIfEmailExists":
				
				ajax_checkIfEmailExists(request, response);
				break;
		}
	}
	
	
	private void showRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Role> AllRoles = roleDao.findAllRoles();
		
        request.setAttribute("AllRoles", AllRoles);
        this.getServletContext().getRequestDispatcher("/pages/Login/Register.jsp").forward(request, response);
        
        return;
	}
	
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("registerEmail");
        String password = DigestUtils.sha256Hex(request.getParameter("password")); 
        String stringBirthDate = request.getParameter("birthDate");
        Date birthDate = null;
        
		try {
			birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringBirthDate);
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		}
        
        Role role = roleDao.findRoleByName(request.getParameter("role"));
        User newUser = new User(firstName, lastName, email, password, birthDate, role);

        userDao.createUser(newUser);
        
        request.setAttribute("newUser", true);
        request.setAttribute("newUserEmail", email);
        this.getServletContext().getRequestDispatcher("/pages/Login/Login.jsp").forward(request, response);
        
        return;
	}
	
	
	private void ajax_checkIfEmailExists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        String email = request.getParameter("email");
        
        try {
			userDao.exitIfUserExists(email);
		} 
        
        catch (EbenusException | SQLException e) {
        	response.setStatus(403);
        }
        
        return;
	}
	
	
}
