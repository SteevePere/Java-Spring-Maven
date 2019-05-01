/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao extends AbstractListDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    private List<Utilisateur> utilisateursListDataSource = null;

    public ManualListUtilisateurDao() {
        super(Utilisateur.class, DataSource.getInstance().getUtilisateursListDataSource());
        this.utilisateursListDataSource = DataSource.getInstance().getUtilisateursListDataSource();
     }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateursListDataSource;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursListDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	
        for (Utilisateur user : utilisateursListDataSource) {
    		
     	   if(user.getIdUtilisateur() == idUtilisateur) {
     		   return user;
     	   }
     	   
        }
        
        return null;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	
    	List<Utilisateur> findElements = new ArrayList<>();
    	
    	for (Utilisateur user : utilisateursListDataSource) {
    		
     	   if(String.valueOf(user.getPrenom()).equals(prenom)) {
     		 findElements.add(user);
     	   }
    	}
    	
        return findElements;    
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	
    	List<Utilisateur> findElements = new ArrayList<>();
    	
    	for (Utilisateur user : utilisateursListDataSource) {
    		
     	   if(String.valueOf(user.getNom()).equals(nom)) {
     		 findElements.add(user);
     	   }
    	}
    	
        return findElements;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont l'identifiant est égal au paramètre
     * passé
     *
     * @param identifiant Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	
    	List<Utilisateur> findElements = new ArrayList<>();
    	
    	for (Utilisateur user : utilisateursListDataSource) {
    		
     	   if(String.valueOf(user.getIdentifiant()).equals(identifiant)) {
     		 findElements.add(user);
     	   }
    	}
    	
        return findElements;
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	
    	for (Utilisateur users : utilisateursListDataSource) {

    		if (users.getIdentifiant() == user.getIdentifiant()) {
    			throw new CustomException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant "+ user.getIdentifiant()+" dans l’application", -1);
    		}
    	}
    	
		Integer current_max_id = utilisateursListDataSource.size();
		Integer new_id =  current_max_id + 1;
		
		Date date = new Date();

    	user.setIdUtilisateur(new_id);
    	user.setDateCreation(date);
    	user.setDateModification(date);
		utilisateursListDataSource.add(user);

        return user;
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	
    	int indexOfrole = utilisateursListDataSource.indexOf(user);
    	if (indexOfrole == -1) {
    			throw new CustomException("Element Not Fund", 404);
    	} else {
    		utilisateursListDataSource.set(indexOfrole,user);
    	}
        return user;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursListDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	
    	if(utilisateursListDataSource.contains(user)) {
    		
    		System.out.println("exist");
    		
    		try {
    			utilisateursListDataSource.remove(user);
        		return true;
        	} catch (Exception e) {
    			return false;
    		}
    		
    	} else {
    		return false;
    	}
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	
    	List<Utilisateur> findByIdRole = new ArrayList<>();
    	
    	for (Utilisateur user : utilisateursListDataSource) {
    		
    		if(user.getRole().getIdRole() == idRole) {
    			findByIdRole.add(user);
    		}
    	}
    	
        return findByIdRole;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	
    	List<Utilisateur> findByIdentifRoles = new ArrayList<>();
    	
    	for (Utilisateur user : utilisateursListDataSource) {
    		if(String.valueOf(user.getRole().getIdentifiant()).equals(identifiantRole)) {
    			findByIdentifRoles.add(user);
    		}
    	}
    	
        return findByIdentifRoles;
    }
}
