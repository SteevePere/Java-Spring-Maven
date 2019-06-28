package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;
import com.mysql.jdbc.Statement;


public class IncidentTypeDao implements IIncidentTypeDao {


	private Connection connection = null;

    
	public IncidentTypeDao() {
		
        this.connection  = DriverManagerSingleton.getInstance().getConnectionInstance();
    }
	
	
	public IncidentType getIncidentTypeFromResultset(ResultSet resultSet) throws SQLException {
		
		Integer incidentTypeId = resultSet.getInt("TYP_ID");
		String incidentTypeName = resultSet.getString("TYP_NAME");

		IncidentType incidentType = new IncidentType(incidentTypeId, incidentTypeName);
		
		return incidentType;
	}
	
	
	@Override
	public List<IncidentType> findAllIncidentTypes() {
		
		List<IncidentType> incidentTypes = new ArrayList<IncidentType>();
		IncidentType incidentType = null;
    	PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement(
				"SELECT * FROM incident_types"
			);

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			incidentType = getIncidentTypeFromResultset(resultSet);
    			incidentTypes.add(incidentType);
    		}
    		
    		ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
        return incidentTypes;
	}
	

	@Override
	public IncidentType findIncidentTypeById(int incidentTypeId) {
		
		IncidentType foundIncidentType = null;
		PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement("SELECT * FROM incident_types WHERE TYP_ID = ?");
    		preparedStatement.setInt(1, incidentTypeId);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			foundIncidentType = getIncidentTypeFromResultset(resultSet);
    		}
    		
    		ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return foundIncidentType;
	}

	
	@Override
	public IncidentType findIncidentTypeByName(String incidentTypeName) {
		
		IncidentType foundIncidentType = null;
		PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement("SELECT * FROM incident_types WHERE TYP_NAME = ?");
    		preparedStatement.setString(1, incidentTypeName);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			foundIncidentType = getIncidentTypeFromResultset(resultSet);
    		}
    		
    		ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return foundIncidentType;
	}

	
	@Override
	public IncidentType createIncidentType(IncidentType incidentType) {
		
		Integer incidentTypeId = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"INSERT INTO incident_types (TYP_NAME) VALUES (?)", 
					Statement.RETURN_GENERATED_KEYS
					);
			
			preparedStatement.setString(1, incidentType.getIncidentTypeName());
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
    		
			while (resultSet.next()) {
				incidentTypeId = resultSet.getInt(1);
    		}	
    			
			incidentType.setIncidentTypeId(incidentTypeId);		    		
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		return incidentType;
	}
	

	@Override
	public IncidentType updateIncidentType(IncidentType incidentType) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement("UPDATE incident_types SET TYP_NAME = ? WHERE TYP_ID = ?;");
			preparedStatement.setString(1, incidentType.getIncidentTypeName());

			preparedStatement.executeUpdate();
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		return incidentType;
	}

	
	@Override
	public boolean deleteIncidentType(IncidentType incidentType) {
		
		boolean deleted = false; 
		PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement("DELETE FROM incident_types WHERE TYP_ID = ?");
    		preparedStatement.setInt(1, incidentType.getIncidentTypeId());
    		
    		Integer rowsDeleted = preparedStatement.executeUpdate();
    		
    		if (rowsDeleted == 1)
    			deleted = true;
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
		
		return deleted;
	}
	

}
