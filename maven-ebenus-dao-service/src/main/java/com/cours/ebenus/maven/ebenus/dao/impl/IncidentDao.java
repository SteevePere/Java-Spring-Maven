package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Incident;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.mysql.jdbc.Statement;


public class IncidentDao implements IIncidentDao {

	
	private Connection connection = null;

    
	public IncidentDao() {
		
        this.connection  = DriverManagerSingleton.getInstance().getConnectionInstance();
    }
	
	
	public Incident getIncidentFromResultset(ResultSet resultSet) throws SQLException {
		
		Integer incidentId = resultSet.getInt("ICD_ID");
		Double incidentLatitude = resultSet.getDouble("ICD_LATITUDE");
		Double incidentLongitude = resultSet.getDouble("ICD_LONGITUDE");
		Date incidentDate = resultSet.getDate("ICD_DATE");
		Date creationDate = resultSet.getDate("ICD_CREATION_DATE");
		Date editDate = resultSet.getDate("ICD_EDIT_DATE");
		
		Integer incidentTypeId = resultSet.getInt("TYP_ID");
		String incidentTypeName = resultSet.getString("TYP_NAME");
		
		
		Integer userId = resultSet.getInt("ICD_CREATED_BY");
		String firstName = resultSet.getString("USR_FIRST_NAME");
		String lastName = resultSet.getString("USR_LAST_NAME");
		String email = resultSet.getString("USR_EMAIL");
		
		User createdBy = new User(userId, firstName, lastName, email);
		IncidentType incidentType = new IncidentType(incidentTypeId, incidentTypeName);
		
		Incident incident = new Incident(incidentId, incidentType, incidentDate, 
				incidentLatitude, incidentLongitude, createdBy, creationDate, editDate);
		
		return incident;
	}
	

	public String getQuery(String criteria) {
		
		String query = "SELECT * FROM incidents "
				+ "INNER JOIN users ON incidents.ICD_CREATED_BY = users.USR_ID "
				+ "INNER JOIN incident_types ON incidents.ICD_TYPE = incident_types.TYP_ID "
				+ "WHERE incidents.";
		
		switch (criteria) {
		
			case "incidentId":
				query += "ICD_ID = ?";
				break;
		
			case "incidentType":
				query += "ICD_TYPE = ?";
				break;
				
			case "createdBy":
				query += "ICD_CREATED_BY = ?";
				break;
		}
		
		return query;
	}
	

	@Override
	public List<Incident> findAllIncidents() {
		
		List<Incident> incidents = new ArrayList<Incident>();
		Incident incident = null;
    	PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement(
				"SELECT * FROM incidents "
				+ "INNER JOIN users ON incidents.ICD_CREATED_BY = users.USR_ID "
				+ "INNER JOIN incident_types ON incidents.ICD_TYPE = incident_types.TYP_ID"
			);

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			incident = getIncidentFromResultset(resultSet);
    			incidents.add(incident);
    		}
    		
    		ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
        return incidents;
	}

	
	@Override
	public List<Incident> findIncidentsBy(int value, String criteria) {
	
		List<Incident> foundIncidents = new ArrayList<Incident>();
		Incident foundIncident = null;
		String query = getQuery(criteria);
		PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setInt(1, value);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			foundIncident = getIncidentFromResultset(resultSet);
    			foundIncidents.add(foundIncident);
    		}
    		
    		ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return foundIncidents;
	}

	
	@Override
	public Incident createIncident(Incident incident) {
		
		Integer incidentId = null;
		Date now = new Date();
		PreparedStatement preparedStatement = null;
		
		incident.setCreationDate(now);
		incident.setEditDate(now);
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"INSERT INTO incidents (ICD_TYPE, ICD_DATE, ICD_LATITUDE, "
					+ "ICD_LONGITUDE, ICD_CREATED_BY, ICD_CREATION_DATE, ICD_EDIT_DATE) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
					);
			
			preparedStatement.setInt(1, incident.getIncidentType().getIncidentTypeId());
			preparedStatement.setObject(2, incident.getIncidentDate());
			preparedStatement.setDouble(3, incident.getIncidentLatitude());
			preparedStatement.setDouble(4, incident.getIncidentLongitude());
			preparedStatement.setInt(5, incident.getCreatedBy().getUserId());
			preparedStatement.setObject(6, now);
			preparedStatement.setObject(7, now);
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
    		
			while (resultSet.next()) {
				incidentId = resultSet.getInt(1);
    		}	
    			
			incident.setIncidentId(incidentId);		    		
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		return incident;
	}

	
	@Override
	public Incident updateIncident(Incident incident) {
		
		PreparedStatement preparedStatement = null;
		Date now = new Date();
		
		try {
			
			preparedStatement = connection.prepareStatement(
				"UPDATE incidents SET ICD_TYPE = ?, ICD_DATE = ?, ICD_LATITUDE = ?,"
				+ " ICD_LONGITUDE = ?, ICD_EDIT_DATE = ? WHERE ICD_ID = ?;"
			);
			
			preparedStatement.setInt(1, incident.getIncidentType().getIncidentTypeId());
			preparedStatement.setObject(2, incident.getIncidentDate());
			preparedStatement.setDouble(3, incident.getIncidentLatitude());
			preparedStatement.setDouble(4, incident.getIncidentLongitude());
			preparedStatement.setObject(5, now);
			preparedStatement.setObject(6, incident.getIncidentId());
			
			preparedStatement.executeUpdate();
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		return incident;
	}

	
	@Override
	public boolean deleteIncident(Incident incident) {
		
		boolean deleted = false; 
		PreparedStatement preparedStatement = null;
    	
    	try {
    		
    		preparedStatement = connection.prepareStatement("DELETE FROM incidents WHERE ICD_ID = ?");
    		preparedStatement.setInt(1, incident.getIncidentId());
    		
    		Integer rowsDeleted = preparedStatement.executeUpdate();
    		
    		if (rowsDeleted == 1)
    			deleted = true;
    	} 
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
		
		return deleted;
	}
	
	
	@Override
	public HashMap <String, Integer> countIncidentsByType() {
		
		HashMap <String, Integer> countByIncidentType = new HashMap<String, Integer>();
		
		String incidentTypeName = null;
		Integer numberOfIncidents = null;
		
		String query = "SELECT TYP_NAME, COUNT(ICD_TYPE) AS 'COUNT' "
					+ "FROM incidents "
					+ "INNER JOIN incident_types ON incidents.ICD_TYPE = incident_types.TYP_ID "
					+ "GROUP BY ICD_TYPE";

		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			numberOfIncidents = resultSet.getInt("COUNT");
				incidentTypeName = resultSet.getString("TYP_NAME");
				
				countByIncidentType.put(incidentTypeName, numberOfIncidents);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return countByIncidentType;
	}
	
	
	@Override
	public TreeMap <String, Integer> countIncidentsByDate() {

		TreeMap<String, Integer> countByIncidentDate = new TreeMap<>();

		Date incidentDate = null;
		Integer numberOfIncidents = null;

		String query = "SELECT ICD_DATE, COUNT(ICD_ID) AS 'COUNT' "
					+ "FROM incidents "
					+ "GROUP BY ICD_DATE "
					+ "ORDER BY ICD_DATE ASC";

		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			
    			numberOfIncidents = resultSet.getInt("COUNT");
				incidentDate = resultSet.getDate("ICD_DATE");
				
				countByIncidentDate.put(incidentDate.toString(), numberOfIncidents);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return countByIncidentDate;
	}

	
}
