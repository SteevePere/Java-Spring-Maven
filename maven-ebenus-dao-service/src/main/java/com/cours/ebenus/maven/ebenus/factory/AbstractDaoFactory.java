package com.cours.ebenus.maven.ebenus.factory;

import com.cours.ebenus.maven.ebenus.factory.DaoFactory;
import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;


public abstract class AbstractDaoFactory {
	
	
	public static String className = AbstractDaoFactory.class.getName();
	public abstract IIncidentDao getIncidentDao();
	public abstract IIncidentTypeDao getIncidentTypeDao();
	public abstract IUserDao getUserDao();
	public abstract IRoleDao getRoleDao();
	
	
	public enum FactoryDaoType {

        JDBC_DAO_FACTORY;
    }

	
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
    	
    	AbstractDaoFactory factory = null;
    	
    	switch(daoType) {
    	
    		case JDBC_DAO_FACTORY:

    			factory = new DaoFactory();
    	}
    	
    	return factory;
    }
	

}
