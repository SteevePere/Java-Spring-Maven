package com.cours.ebenus.maven.ebenus.dao;

import java.sql.SQLException;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.User;


public interface IUserDao {
	
	
	public List<User> findAllUsers();

	
    public User findUserById(int userId);

    
    public List<User> findUsersByFirstName(String firstName);

    
    public List<User> findUsersByLastName(String lastName);

    
    public List<User> findUsersByEmail(String email);

    
    public List<User> findUsersByRoleId(int roleId);

    
    public List<User> findUsersByRoleName(String roleName);
    
    
    public List<User> findUsersByActive(Integer active);

    
    public User createUser(User user);
    
    
    public void exitIfUserExists(String email) throws SQLException;

    
    public User updateUser(User user);

    
    public boolean deleteUser(User user);

    
    public User authenticate(String email, String password);


	
    
    
}
