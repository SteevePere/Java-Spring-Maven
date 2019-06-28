package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.exception.EbenusException;
import com.mysql.jdbc.Statement;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;


public class RoleDao extends AbstractDao<Role> implements IRoleDao {
	
	
	private Connection connection = null;

	
	public RoleDao() {
		
		super(Role.class);
		this.connection = DriverManagerSingleton.getInstance().getConnectionInstance();
	}

	
	void exitIfRoleExists(Role role) throws SQLException {
		
		PreparedStatement checkingRoles = null;
		String roleFound = "";
		
		checkingRoles = connection.prepareStatement("SELECT ROL_NAME from roles where ROL_NAME = ?");
		checkingRoles.setString(1, role.getRoleName());
		
		ResultSet foundRoles = checkingRoles.executeQuery();

		while (foundRoles.next())
			roleFound = foundRoles.getString("ROL_NAME");
		
		if (!roleFound.isEmpty())
			throw new EbenusException(-1);
			
		return;
	}
	
	
	@Override
	public List<Role> findAllRoles() {
		
		List<Role> roles= new ArrayList<Role>();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement("SELECT * FROM roles");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Integer roleId = resultSet.getInt("ROL_ID");
				String roleName = resultSet.getString("ROL_NAME");

				Role role = new Role(roleId, roleName);
				roles.add(role);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return roles;
	}

	
	@Override
	public Role findRoleById(int roleId) {
		
		Role role = null;
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement("SELECT * FROM roles WHERE `ROL_ID` = ?");
			preparedStatement.setInt(1, roleId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Integer id = resultSet.getInt("ROL_ID");
				String roleName = resultSet.getString("ROL_NAME");
				role = new Role(id, roleName);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}

	
	@Override
	public Role findRoleByName(String roleName) {

		Role role = null;
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM roles WHERE `ROL_NAME` = ?"
			);
			preparedStatement.setString(1, roleName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Integer roleId = resultSet.getInt("ROL_ID");
				String name = resultSet.getString("ROL_NAME");

				role = new Role(roleId, name);
			}

			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}

		return role;
	}

	
	@Override
	public Role createRole(Role role) {
		
		PreparedStatement preparedStatement = null;
		Integer idRole = null;

		try {
			
			exitIfRoleExists(role);
			
			preparedStatement = connection.prepareStatement(
					"INSERT INTO roles (ROL_NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS
			);
			
			preparedStatement.setString(1, role.getRoleName());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			while (resultSet.next()) {
				idRole = resultSet.getInt(1);
			}
			
			role.setRoleId(idRole);
		} 
			
		catch (Exception e) {
				e.printStackTrace();
		}

		return role;
	}

	
	@Override
	public Role updateRole(Role role) {
		
		PreparedStatement preparedStatement = null;
	
		try {
			
			preparedStatement = connection.prepareStatement("UPDATE roles SET ROL_NAME = ? WHERE ROL_ID = ?");
			preparedStatement.setString(1, role.getRoleName());
			preparedStatement.setInt(2, role.getRoleId());
	
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}

	
	@Override
	public boolean deleteRole(Role role) {
		
		boolean deleted = false;
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM roles WHERE ROL_ID = ?");
			preparedStatement.setInt(1, role.getRoleId());
			
			Integer rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted == 1)
				deleted = true;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}


}
