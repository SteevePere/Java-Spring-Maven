package com.cours.ebenus.maven.ebenus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionHelper {

	
    public final static String className = ConnectionHelper.class.getName();

    
    public static void closeSqlResources(PreparedStatement preparedStatement, ResultSet result) {
    	
        if (preparedStatement != null) {
            
        	try {
                preparedStatement.close();
            } 
        	
        	catch (SQLException e) {
        		e.printStackTrace();
            }
        }
        
        if (result != null) {
            
        	try {
                result.close();
            } 
        	
        	catch (SQLException e) {
        		e.printStackTrace();
            }
        }
    }
    
    
}
