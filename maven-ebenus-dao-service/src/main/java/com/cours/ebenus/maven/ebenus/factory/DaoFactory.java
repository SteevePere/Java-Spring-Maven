package com.cours.ebenus.maven.ebenus.factory;

import com.cours.ebenus.maven.ebenus.dao.IIncidentDao;
import com.cours.ebenus.maven.ebenus.dao.IIncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.impl.IncidentDao;
import com.cours.ebenus.maven.ebenus.dao.impl.IncidentTypeDao;
import com.cours.ebenus.maven.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.maven.ebenus.dao.impl.UserDao;


public class DaoFactory extends AbstractDaoFactory{

	
	@Override
	public IIncidentDao getIncidentDao() {
		
		return new IncidentDao();
	}
	
	
	@Override
	public IIncidentTypeDao getIncidentTypeDao() {
		
		return new IncidentTypeDao();
	}


	@Override
	public IUserDao getUserDao() {
		
		return new UserDao();
	}


	@Override
	public IRoleDao getRoleDao() {
		
		return new RoleDao();
	}

	
}
