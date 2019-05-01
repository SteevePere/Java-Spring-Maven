/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListRoleDao extends AbstractListDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualListRoleDao.class);
    private List<Role> rolesListDataSource = null;

    
    public ManualListRoleDao() {
        super(Role.class, DataSource.getInstance().getRolesListDataSource());
        this.rolesListDataSource = DataSource.getInstance().getRolesListDataSource();
     }
    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {

        return rolesListDataSource;
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
    	
       for (Role role : rolesListDataSource) {
		
    	   if(role.getIdRole() == idRole) {
    		   return role;
    	   }
       }
    	
        return null;
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
    	
    	List<Role> findElements = new ArrayList<>();
    	
    	for (Role role : rolesListDataSource) {
    		
     	   if(String.valueOf(role.getIdentifiant()).equals(identifiantRole)) {
     		 findElements.add(role);
     	   }
    	}
    	
        return findElements;
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
    	
    	for (Role roles : rolesListDataSource) {

    		if (roles.getIdentifiant() == role.getIdentifiant()) {
    			throw new CustomException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant " + role.getIdentifiant()+ " dans l’application", 401);
    		}
    	}
    	
		Integer current_max_id = rolesListDataSource.size();
		Integer new_id =  current_max_id + 1;
		
		role.setIdRole(new_id);
		
		rolesListDataSource.add(role);
		
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
    	
    	int indexOfrole = rolesListDataSource.indexOf(role);

    	if (indexOfrole == -1) {
    			throw new CustomException("Element Not Fund", 404);
    		
    	} else {
    		rolesListDataSource.set(indexOfrole,role);
    	}
        return role;
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
    	
    	if (rolesListDataSource.contains(role)) {
    		try {
    			rolesListDataSource.remove(role);
        		return true;
        	} catch (Exception e) {
    			return false;
    		}
    		
    	} else {
    		return false;
    	}
    	
        
    }
}