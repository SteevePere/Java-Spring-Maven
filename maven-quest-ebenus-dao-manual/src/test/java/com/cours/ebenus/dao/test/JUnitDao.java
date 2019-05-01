package com.cours.ebenus.dao.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.ServiceFacade;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    JUnitQuestEbenusManualList.class,
    JUnitQuestEbenusManualMap.class})


public class JUnitDao {
	
	private static final Log log = LogFactory.getLog(JUnitQuestEbenusManualMap.class);

    @BeforeClass
    public static void init() throws Exception {
    	
    	JUnitQuestEbenusManualMap.init();
    	JUnitQuestEbenusManualList.init();
  
    }
}
