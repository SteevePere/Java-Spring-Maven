package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Incident;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;


public interface IIncidentTypeDao {
	
	
    public List<IncidentType> findAllIncidentTypes();

    
    public IncidentType findIncidentTypeById(int incidentTypeId);

    
    public IncidentType findIncidentTypeByName(String incidentTypeName);

    
    public IncidentType createIncidentType(IncidentType incidentType);

    
    public IncidentType updateIncidentType(IncidentType incidentType);

    
    public boolean deleteIncidentType(IncidentType incidentType);
    
    
}
