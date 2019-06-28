package com.cours.ebenus.maven.ebenus.main;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;
import com.cours.ebenus.maven.ebenus.dao.impl.IncidentTypeDao;


public class Main {

	
	public static void main(String[] args) {
		
		IIncidentTypeDao incidentTypeDao = new IncidentTypeDao();

		List<IncidentType> incidentTypes = incidentTypeDao.findAllIncidentTypes();
		
		for (IncidentType incidentType : incidentTypes) {
			System.out.println(incidentType);
		}
		
		return;
	}

}
