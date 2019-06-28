package com.cours.ebenus.maven.ebenus.dao.test;

import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Incident;
import com.cours.ebenus.maven.ebenus.dao.entities.IncidentType;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ibatis.common.jdbc.ScriptRunner;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import org.junit.Test;
import org.apache.log4j.Logger;


public class JUnit {
    private static final Logger log = Logger.getLogger(JUnit.class);
    private static IServiceFacade serviceFacade = null;
    private static List<Role> roles = null;
    private static List<User> users = null;
    private static List<Incident> incidents = null;
    private static List<IncidentType> incidentType = null;
    private static final int NB_ROLES_LIST = 3;
    private static final int NB_USER_LIST = 7;
    private static final int NB_INCIDENT_LIST = 22;
    private static final int NB_INCIDENTTYPE_LIST = 7;
    private static final int NB_USER_FIND_BY_FIRST_NAME = 1;
    private static final int NB_USER_FIND_BY_LAST_NAME = 1;
    private static final String USER_FIND_BY_FIRST_NAME = "Jhonny";
    private static final String USER_FIND_BY_LAST_NAME = "ouioui";
    private static final String USER_EMAIL_BY_ID = "steevepere@gmail.com";
    private static final String ROLE_BRIGADE = "Brigade";
    private static final String USER_EMAIL_UPDATE = "unit.test@gmail.com";
    private static final String USER_NAME_UPDATE = "Nicole_bis";
    private static final int USER_FIND_BY_ID = 16;


    @BeforeClass
    public static void init() {
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        roles = serviceFacade.getRoleDao().findAllRoles();
        users = serviceFacade.getUserDao().findAllUsers();
        incidents = serviceFacade.getIncidentDao().findAllIncidents();
        incidentType = serviceFacade.getIncidentTypeDao().findAllIncidentTypes();

    }

    @BeforeClass
    public static void initDataBase() throws IOException {

        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;

        try {
            Connection connection = DriverManagerSingleton.getInstance().getConnectionInstance();
            ScriptRunner runner = new ScriptRunner(connection, false, false);

            runner.runScript(new BufferedReader(new FileReader(scriptSqlPath)));
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void verifyRoleData(Role roleInsert) {
        log.debug("In to verifyRole");
        if (roleInsert != null) {
            log.debug("idRole : " + roleInsert.getRoleId());
            Assert.assertNotNull(roleInsert.getRoleId());
            Assert.assertNotNull(roleInsert.getRoleName());
        } else {
            Assert.fail("Role null");
        }
        log.debug("out verify Role Data");
    }

    private void verifyIncidentData(Incident incidentCreat) {
        log.debug("In to verifyIncidentData");
        if (incidentCreat != null) {
            Assert.assertNotNull(incidentCreat.getIncidentDate());
            Assert.assertNotNull(incidentCreat.getIncidentId());
            Assert.assertNotNull(incidentCreat.getCreatedBy());
            Assert.assertNotNull(incidentCreat.getIncidentType().getIncidentTypeName());
        } else {
            log.debug("Your incident is empty");
        }
        log.debug("out to verifyIncidentData");
    }

    //@Test
    // public void deleteIncident_IncidentType(){
    //serviceFacade.getIncidentDao().
    //}

    @Test
    public void updateIncident_Find(){
        List<Incident> incidentgetbyCriter = serviceFacade.getIncidentDao().findIncidentsBy(51,"incidentType");
        String nameGet = incidentgetbyCriter.get(0).getCreatedBy().getFirstName();
        Incident incidentToUpdate = incidentgetbyCriter.get(0);
        List<User> userListToUpdate = serviceFacade.getUserDao().findUsersByFirstName(nameGet);
        User userToUpdate = userListToUpdate.get(0);
        userToUpdate.setFirstName("Bgood");

//        incidentUpdate.setCreatedBy();

        // serviceFacade.getIncidentDao().
        Integer incidentTypeId = incidentgetbyCriter.get(0).getIncidentType().getIncidentTypeId();

        IncidentType incidentTypeUpdate = serviceFacade.getIncidentTypeDao().findIncidentTypeById(incidentTypeId);

        System.out.println(userToUpdate);

        // Incident incidentUpdate = new Incident(5,);
        // serviceFacade.getIncidentDao().updateIncident()
    }

    @Test
    public void creatIncident_IncidentType(){
        Role role = new Role("tester");
        User creator = new User("boby","tonny","@gmail.com","azerty",new Date(System.currentTimeMillis()),role);
        IncidentType incidentTypeCreat = new IncidentType(90,"vole");
        Incident incidentCreat = new Incident(2,incidentTypeCreat,new Date(System.currentTimeMillis()),23.45,-23.45,creator,new Date(System.currentTimeMillis()));
        incidentCreat = serviceFacade.getIncidentDao().createIncident(incidentCreat);
        Assert.assertNotNull(incidentCreat);
        verifyIncidentData(incidentCreat);
    }

    @Test
    public void findAllIncident_IncidentTypes(){
        log.debug("in of findAllIncident_IncidentTypes");
        if(incidents != null) {
            log.debug("NB_INCIDENT_LIST: " + NB_INCIDENT_LIST + ", incidents.size():" + incidents.size());
            Assert.assertEquals(incidents.size(), (long)NB_INCIDENT_LIST);
        } else {
            Assert.fail("incident is Empty");
        }

        if (incidentType != null) {
            log.debug("NB_INCIDENTTYPE_LIST: " + NB_INCIDENTTYPE_LIST + ", incidentType.size():" + incidentType.size());
            Assert.assertEquals(incidentType.size(), (long)NB_INCIDENTTYPE_LIST);
        } else {
            Assert.fail("incidentType is Empty");
        }
        log.debug("out of findAllIncident_IncidentTypes");
    }

    @Test
    public void creationDeleteRole() {
        Role roleInsert = new Role("usersAdgent");
        roleInsert = serviceFacade.getRoleDao().createRole(roleInsert);
        verifyRoleData(roleInsert);
        log.debug("Created roleCRUD : " + roleInsert);
        log.debug("out of test Creation Role");
        Role roleDelete = serviceFacade.getRoleDao().findRoleByName("usersAdgent");
        int userAgentId = roleDelete.getRoleId();
        Role roleDeleteObject = serviceFacade.getRoleDao().findRoleById(userAgentId);
        serviceFacade.getRoleDao().deleteRole(roleDeleteObject);
        roleDeleteObject = serviceFacade.getRoleDao().findRoleById(userAgentId);
        Assert.assertNull(roleDeleteObject);
        log.debug("Sortie de la methode deleteRole");
    }

    @Test
    public void updateRole() {
        log.debug("in updateRole");
        String roleString = roles.get(1).getRoleName();
        Role roleUpdate = serviceFacade.getRoleDao().findRoleByName(roleString);
        log.debug(roleUpdate.getRoleName());
        roleUpdate.setRoleName(ROLE_BRIGADE);
        roleUpdate = serviceFacade.getRoleDao().updateRole(roleUpdate);
        log.debug(roleUpdate);
        Assert.assertEquals(roleUpdate.getRoleName(), ROLE_BRIGADE);
        log.debug("ROLE NAME : " + roleUpdate.getRoleName());
        log.debug("out of updateRole");
    }

    @Test
    public void findAllRole() {
        log.debug("In findAllRole");
        if (roles != null) {
            log.debug("NB_ROLES_LIST: " + NB_ROLES_LIST + ", roles.size():" + roles.size());
            Assert.assertEquals(roles.size(), (long)NB_ROLES_LIST);
        } else {
            Assert.fail("not Role in your bases");
        }
        log.debug("out of findAllRole");
    }

    private void checkUsers(User usersGet) {
        log.debug("in methode checkUsers");
        if(usersGet != null) {
            Assert.assertNotNull(usersGet.getEmail());
            Assert.assertNotNull(usersGet.getUserId());
            Assert.assertNotNull(usersGet.isActive());
            Assert.assertNotNull(usersGet.getCreationDate());
            Assert.assertNotNull(usersGet.getEditDate());
            Assert.assertNotNull(usersGet.getBirthDate());
            Assert.assertNotNull(usersGet.getPassword());
        } else {
            Assert.fail("users null");
        }
        log.debug("out of checkUsers");
    }

    @Test
    public void createUser() {
        log.debug("In createUser");
        Role roles = new Role(6,"users");
        Assert.assertNotNull(roles);
        User users = new User("Nicole","Valentine", new Date(System.currentTimeMillis()),"nicole.valentine@gmail.com","passw0rd",false, roles);
        users = serviceFacade.getUserDao().createUser(users);
        Assert.assertNotNull(users);
        checkUsers(users);
        log.debug("out of createUser");
    }
    @Test
    public void updateUser() {
        log.debug("In updateUser");
        Role roles = new Role(9,"chef");
        User users = new User("Nicola","Valentine", "nicola.valentine@gmail.com","passw0rd", new Date(System.currentTimeMillis()), roles);
        users = serviceFacade.getUserDao().createUser(users);
        checkUsers(users);
        users.setActive(false);
        users.setEmail(USER_EMAIL_UPDATE);
        users.setFirstName(USER_NAME_UPDATE);
        users = serviceFacade.getUserDao().updateUser(users);
        Assert.assertEquals(users.getEmail(),USER_EMAIL_UPDATE);
        Assert.assertEquals(users.getFirstName(),USER_NAME_UPDATE);
        log.debug("out of createUser");
    }

    @Test
    public void findAllUser() {
        log.debug("In findAllUser");
        if (users != null) {
            log.debug("NB_USER_LIST: " + NB_USER_LIST + ", users.size():" + users.size());
            Assert.assertEquals(users.size(), (long)NB_USER_LIST);
        } else {
            Assert.fail("Users empty");
        }
        log.debug("out of findAllUser");
    }

    @Test
    public void findUseerByCriter(){
        log.debug("In findUseerByCriter");
        List<User> userbyFirstName = serviceFacade.getUserDao().findUsersByFirstName(USER_FIND_BY_FIRST_NAME);
        List<User> userbyLastName = serviceFacade.getUserDao().findUsersByLastName(USER_FIND_BY_LAST_NAME);
        User userbyId = serviceFacade.getUserDao().findUserById(USER_FIND_BY_ID);
        log.debug(userbyFirstName);

        if (userbyId == null) {
            Assert.fail("user not found");
        } else {
            log.debug(userbyId.getFirstName());
            Assert.assertEquals(USER_EMAIL_BY_ID, userbyId.getEmail());
        }

        if (userbyFirstName != null) {
            log.debug("USER_FIND_BY_FIRST_NAME: " + USER_FIND_BY_FIRST_NAME + " , user.size(): " + userbyFirstName.size());
            Assert.assertEquals(NB_USER_FIND_BY_FIRST_NAME, userbyFirstName.size());
        } else {
            Assert.fail("null " + USER_FIND_BY_FIRST_NAME + "'was fund");
        }

        if (userbyLastName != null) {
            log.debug("USER_FIND_BY_LAST_NAME: " + USER_FIND_BY_LAST_NAME + " , user.size(): " + userbyLastName.size());
            Assert.assertEquals(NB_USER_FIND_BY_LAST_NAME, userbyLastName.size());
        } else {
            Assert.fail("null" + USER_FIND_BY_LAST_NAME + "' was fund");
        }
        log.debug("out of findUseerByCriter");
    }


    @AfterClass
    public static void terminate() {
        serviceFacade = null;
        roles = null;
        users = null;
        incidentType = null;
        incidents = null;
    }
}
