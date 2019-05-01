package com.cours.ebenus.main;

import com.cours.ebenus.dao.manual.map.impl.ManualMapRoleDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapUtilisateurDao;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Main {


    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {

    	AbstractDaoFactory.FactoryDaoType daoType = AbstractDaoFactory.FactoryDaoType.MANUAL_MAP_DAO_FACTORY;
    	IServiceFacade serviceFacade = new ServiceFacade(daoType);
    	
    	IRoleDao dao = serviceFacade.getRoleDao();
    	
    	System.out.println(dao.findAllRoles());
    }
}
