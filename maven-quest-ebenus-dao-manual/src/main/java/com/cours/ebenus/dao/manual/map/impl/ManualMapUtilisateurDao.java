/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.security.KeyStore.Entry;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapUtilisateurDao extends AbstractMapDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualMapUtilisateurDao.class);
    private Map<Integer, Utilisateur> utilisateursMapDataSource = null;

    public ManualMapUtilisateurDao() {
       super(Utilisateur.class, DataSource.getInstance().getUtilisateursMapDataSource());
       this.utilisateursMapDataSource = DataSource.getInstance().getUtilisateursMapDataSource();
    }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {

    	List<Utilisateur> all_users = new ArrayList<Utilisateur>(utilisateursMapDataSource.values());

        return all_users;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursMapDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {

    	Utilisateur user_found = utilisateursMapDataSource.get(idUtilisateur);

        return user_found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {

    	List<Utilisateur> found_users = new ArrayList<Utilisateur>();

    	for (Utilisateur user : utilisateursMapDataSource.values()) {

    		if (user.getPrenom() == prenom) {
    			found_users.add(user);
    		}
    	}

        return found_users;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {

    	List<Utilisateur> found_users = new ArrayList<Utilisateur>();

    	for (Utilisateur user : utilisateursMapDataSource.values()) {

    		if (user.getNom() == nom) {
    			found_users.add(user);
    		}
    	}

        return found_users;
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

    	List<Utilisateur> found_users = new ArrayList<Utilisateur>();

    	for (Utilisateur user : utilisateursMapDataSource.values()) {

    		if (user.getIdentifiant() == identifiant) {
    			found_users.add(user);
    		}
    	}

        return found_users;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	
    	List<Utilisateur> found_users = new ArrayList<Utilisateur>();

    	for (Utilisateur user : utilisateursMapDataSource.values()) {

    		if (user.getRole().getIdRole() == idRole) {
    			found_users.add(user);
    		}
    	}
        return found_users;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	
    	List<Utilisateur> found_users = new ArrayList<Utilisateur>();

    	for (Utilisateur user : utilisateursMapDataSource.values()) {

    		if (user.getRole().getIdentifiant() == identifiantRole) {
    			found_users.add(user);
    		}
    	}
        return found_users;
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	
    	for (Utilisateur existing_user : utilisateursMapDataSource.values()) {

    		if (existing_user.getIdentifiant() == user.getIdentifiant()) {
    			throw new CustomException("Une erreur s’est produite, il existe déjà un utilisateur avec l'identifiant " + user.getIdentifiant() + " dans l'application", -1);
    		}
    	}

		Integer current_max_id = Collections.max(utilisateursMapDataSource.keySet());
    	Integer new_id = current_max_id + 1;
    	Date date = new Date();

    	user.setIdUtilisateur(new_id);
    	user.setDateCreation(date);
    	user.setDateModification(date);
    	utilisateursMapDataSource.put(new_id, user);

        return user;
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {

		Iterator<java.util.Map.Entry<Integer, Utilisateur>> it = utilisateursMapDataSource.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, Utilisateur> pair = (Map.Entry<Integer, Utilisateur>)it.next();

            if (user.equals(pair.getValue())) {
            	
            	Date date = new Date();
            	user.setDateModification(date);

            	utilisateursMapDataSource.put(pair.getKey(), user);
            	return user;

            }
        }
        return null;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursMapDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {

		boolean removed = false;
    	Iterator<java.util.Map.Entry<Integer, Utilisateur>> it = utilisateursMapDataSource.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, Utilisateur> pair = (Map.Entry<Integer, Utilisateur>)it.next();

            if (user.equals(pair.getValue())) {

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
