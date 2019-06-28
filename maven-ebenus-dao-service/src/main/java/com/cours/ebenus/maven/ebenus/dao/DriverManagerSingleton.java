package com.cours.ebenus.maven.ebenus.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.cours.ebenus.maven.ebenus.utils.Constants;


public class DriverManagerSingleton {

	
    public final static String className = DriverManagerSingleton.class.getName();
    private static final String url = Constants.DATABASE_URL;
    private static final String user = Constants.DATABASE_USER;
    private static final String password = Constants.DATABASE_PASSWORD;
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    private static Connection connection = null;
    
    
    private DriverManagerSingleton() {
    	
    }

    
    private static class Holder {
    	
        static final DriverManagerSingleton instance = new DriverManagerSingleton();
        
    }

    
    public static DriverManagerSingleton getInstance() {
    	
        return Holder.instance;
        
    }
    
    
    public Connection getConnectionInstance() {

    	if (DriverManagerSingleton.connection == null) {
    		
	    	try {
	    		
	    		Class.forName(DriverManagerSingleton.jdbcDriver);
	    		DriverManagerSingleton.connection = DriverManager.getConnection(
					DriverManagerSingleton.url, DriverManagerSingleton.user, DriverManagerSingleton.password
				);
	    	}
	    	
	    	catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	catch (SQLException e) {
	    		
	    	    System.out.println("SQLException: " + e.getMessage());
	    	    System.out.println("SQLState: " + e.getSQLState());
	    	    System.out.println("VendorError: " + e.getErrorCode());
	    	}
    	}
    	
    	return DriverManagerSingleton.connection;
    }
    
    
}
