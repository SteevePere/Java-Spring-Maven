/**
 * 
 */
package com.cours.ebenus.maven.ebenus.service;

import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


public class ServiceFacade implements IServiceFacade {
	
	private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

	private IIncidentDao incidentDao = null;
	private IIncidentTypeDao incidentTypeDao = null;
	private IUserDao userDao = null;
    private IRoleDao roleDao = null;
	
	
	public ServiceFacade() {

        incidentDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getIncidentDao();
        incidentTypeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getIncidentTypeDao();
        userDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
	}
	
	
	public ServiceFacade(FactoryDaoType daoType) {

        incidentDao = AbstractDaoFactory.getFactory(daoType).getIncidentDao();
        incidentTypeDao = AbstractDaoFactory.getFactory(daoType).getIncidentTypeDao();
        userDao = AbstractDaoFactory.getFactory(daoType).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
    }
	

	@Override
	public IIncidentDao getIncidentDao() {
		return incidentDao;
	}


	@Override
	public IIncidentTypeDao getIncidentTypeDao() {
		return incidentTypeDao;
	}


	@Override
	public IUserDao getUserDao() {
		return userDao;
	}


	@Override
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	
}
