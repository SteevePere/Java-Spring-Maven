/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapRoleDao extends AbstractMapDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualMapRoleDao.class);
    private Map<Integer, Role> rolesMapDataSource = null;

    
    public ManualMapRoleDao() {
    	
        super(Role.class, DataSource.getInstance().getRolesMapDataSource());
        this.rolesMapDataSource = DataSource.getInstance().getRolesMapDataSource();
    }
    
    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
    	
    	List<Role> all_roles = new ArrayList<Role>(rolesMapDataSource.values());
    	
        return all_roles;
    }

    /**
     * Méthode qui retourne le rôle d'id passé en paramètre de la database (ici
     * rolesListDataSource)
     *
     * @param idRole L'id du rôle à récupérer
     * @return Le rôle d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Role findRoleById(int idRole) {
    	
    	Role role_found = rolesMapDataSource.get(idRole);
    	
        return role_found;
    }

    /**
     * Méthode qui retourne une liste de tous les rôles de la database (ici
     * rolesListDataSource) dont l'identifiant est égal au paramètre passé
     *
     * @param identifiantRole L'identifiant des rôles à récupérer
     * @return Une liste de tous les rôles dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
    	
    	List<Role> found_roles = new ArrayList<Role>();
    	
    	for (Role role : rolesMapDataSource.values()) {
    	    
    		if (role.getIdentifiant() == identifiantRole) {
    			found_roles.add(role);
    		}	
    	}
    	
        return found_roles;
    }

    /**
     * Méthode qui permet d'ajouter à rôle dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à ajouter
     * @return Le rôle ajouté ou null si échec
     */
    @Override
    public Role createRole(Role role) {
    	
    	Integer current_max_id = Collections.max(rolesMapDataSource.keySet());
    	Integer new_id = current_max_id + 1;
    	
    	role.setIdRole(new_id);
    	rolesMapDataSource.put(new_id, role);
    	
        return role;
    }

    /**
     * Méthode qui permet d'update un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à modifier
     * @return Le rôle modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Role updateRole(Role role) {
    	
    	java.util.Iterator<Entry<Integer, Role>> it = rolesMapDataSource.entrySet().iterator();

        while (it.hasNext()) {
        	
            Map.Entry<Integer, Role> pair = (Map.Entry<Integer, Role>)it.next();
            
            if (role.equals(pair.getValue())) {
            	rolesMapDataSource.put(pair.getKey(), role);
            	return role;
            }
        }
        
        return null;
    }

    /**
     * Méthode qui permet de supprimer un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteRole(Role role) {
    	
    	boolean removed = false;
    	java.util.Iterator<Entry<Integer, Role>> it = rolesMapDataSource.entrySet().iterator();
    	
        while (it.hasNext()) {
        	
            Map.Entry<Integer, Role> pair = (Map.Entry<Integer, Role>)it.next();
            
            if (role.equals(pair.getValue())) {
            	
            	try {
            		it.remove();
            		removed = true;
            	}
            	
            	catch (Exception e) {
            		
            	}
            }
        }
        
	    return removed;
    }

}
