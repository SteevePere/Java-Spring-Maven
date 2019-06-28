package com.cours.ebenus.maven.ebenus.service;

import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;


public interface IServiceFacade {

	
    public IIncidentDao getIncidentDao();
    
    
    public IIncidentTypeDao getIncidentTypeDao();
    
    
    public IUserDao getUserDao();
    
    
    public IRoleDao getRoleDao();
    
    
}
