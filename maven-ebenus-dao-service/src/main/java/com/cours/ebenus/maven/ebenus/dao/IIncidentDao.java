package com.cours.ebenus.maven.ebenus.dao;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.cours.ebenus.maven.ebenus.dao.entities.Incident;


public interface IIncidentDao {
	
	
    public List<Incident> findAllIncidents();

    
    public List<Incident> findIncidentsBy(int value, String criteria);

    
    public Incident createIncident(Incident incident);

    
    public Incident updateIncident(Incident incident);

    
    public boolean deleteIncident(Incident incident);


    public HashMap <String, Integer> countIncidentsByType();
    
    
    public TreeMap <String, Integer> countIncidentsByDate();
    
    
}
