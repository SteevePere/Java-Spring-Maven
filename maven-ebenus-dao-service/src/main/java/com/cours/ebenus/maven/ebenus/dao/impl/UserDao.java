package com.cours.ebenus.maven.ebenus.dao.impl;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.EbenusException;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDao extends AbstractDao<User> implements IUserDao {
	
	
	private Connection connection;
	

	public UserDao() {

		super(User.class);
		this.connection = DriverManagerSingleton.getInstance().getConnectionInstance();
	}
	

	private User getUserFromResultSet(ResultSet resultSet) throws SQLException {

		User user = null;

		Integer userId = resultSet.getInt("USR_ID");
		String userFirstName = resultSet.getString("USR_FIRST_NAME");
		String userLastName = resultSet.getString("USR_LAST_NAME");
		String userEmail = resultSet.getString("USR_EMAIL");
		String userPassword = resultSet.getString("USR_PASSWORD");
		Date userBirthDate = resultSet.getDate("USR_BIRTH_DATE");
		Date userCreationDate = resultSet.getDate("USR_CREATION_DATE");
		Date userEditDate = resultSet.getDate("USR_EDIT_DATE");
		Boolean isActive = resultSet.getBoolean("USR_ACTIVE");
		Integer roleId = resultSet.getInt("ROL_ID");
		String roleName = resultSet.getString("ROL_NAME");
		
		Role role = new Role(roleId, roleName);
		user = new User(userId, userFirstName, userLastName, userEmail, userPassword, userBirthDate, userCreationDate, userEditDate, isActive, role);

		return user;
	}
	
	
	@Override
	public void exitIfUserExists(String email) throws SQLException, EbenusException {
		
		PreparedStatement checkingUsers = null;
		String userFound = "";
		
		checkingUsers = connection.prepareStatement("SELECT USR_EMAIL from users where USR_EMAIL = ?");
		checkingUsers.setString(1, email);
		
		ResultSet foundUsers = checkingUsers.executeQuery();

		while (foundUsers.next())
			userFound = foundUsers.getString("USR_EMAIL");
		
		if (!userFound.isEmpty())
			throw new EbenusException(-1);
			
		return;
	}
	
	
	@Override
	public List<User> findAllUsers() {
		
		List<User> allUsers = new ArrayList<User>();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles on users.USR_ROLE = roles.ROL_ID"
			);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
			
				User user = getUserFromResultSet(resultSet);
				allUsers.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allUsers;
	}

	
	@Override
	public User findUserById(int userId) {
		
		User user = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE USR_ID = ?"
			);
			
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
				user = getUserFromResultSet(resultSet);

			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}


	@Override
	public List<User> findUsersByFirstName(String firstName) {
		
		List<User> foundUsers = new ArrayList<User>();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE USR_FIRST_NAME = ?"
			);
			
			preparedStatement.setString(1, firstName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				foundUsers.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundUsers;
	}
	

	@Override
	public List<User> findUsersByLastName(String lastName) {
		
		List<User> foundUsers = new ArrayList<>();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE USR_LAST_NAME = ?"
			);
			
			preparedStatement.setString(1, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				foundUsers.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundUsers;
	}

	
	@Override
	public List<User> findUsersByEmail(String email) {
		
		List<User> foundUsers = new ArrayList<>();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE users.USR_EMAIL = ?"
			);
			
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				foundUsers.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundUsers;
	}

	
	@Override
	public List<User> findUsersByRoleId(int roleId) {
		
		List<User> usersFound = new ArrayList<User>();
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE users.USR_ROLE = ?"
			);
			
			preparedStatement.setInt(1, roleId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				usersFound.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersFound;
	}

	
	@Override
	public List<User> findUsersByRoleName(String nameRole) {
		
		List<User> usersFound = new ArrayList<User>();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE roles.ROL_NAME = ?;"
			);
			
			preparedStatement.setString(1, nameRole);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				usersFound.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersFound;
	}
	
	
	@Override
	public List<User> findUsersByActive(Integer active) {
		
		List<User> usersFound = new ArrayList<User>();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID "
					+ "WHERE users.USR_ACTIVE = ?;"
			);
			
			preparedStatement.setInt(1, active);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = getUserFromResultSet(resultSet);
				usersFound.add(user);
			}
			
			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersFound;
	}
	

	@Override
	public User createUser(User user) {
		
		Integer userId = null;
		Date createDate = new Date();
		Date editDate = new Date();
		PreparedStatement preparedStatement = null;
		
		user.setCreationDate(createDate);
		user.setEditDate(editDate);

		try {
			
			exitIfUserExists(user.getEmail());
				
			preparedStatement = connection.prepareStatement(
					"INSERT INTO users (USR_ROLE, USR_FIRST_NAME, USR_LAST_NAME, USR_EMAIL, USR_ACTIVE,"
							+ "USR_PASSWORD, USR_BIRTH_DATE, USR_CREATION_DATE, USR_EDIT_DATE) VALUES " +
							"(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
			);
			
			preparedStatement.setInt(1, user.getRole().getRoleId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, 0); // account is inactive by default
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setObject(7, user.getBirthDate());
			preparedStatement.setObject(8, createDate);
			preparedStatement.setObject(9, editDate);
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			while (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
			
			user.setUserId(userId);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	@Override
	public User updateUser(User user) {
		
		Integer userId = user.getUserId();
		Date editDate = new Date();
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"UPDATE users SET USR_ROLE = ?, USR_EMAIL = ?, USR_FIRST_NAME = ?, USR_LAST_NAME = ?, "
							+ "USR_PASSWORD = ?, USR_BIRTH_DATE = ?, USR_EDIT_DATE = ?, USR_ACTIVE = ? "
							+ " WHERE USR_ID = ?;"
			);
			
			preparedStatement.setInt(1, user.getRole().getRoleId());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setObject(6, user.getBirthDate());
			preparedStatement.setObject(7, editDate);
			preparedStatement.setBoolean(8, user.isActive());
			preparedStatement.setInt(9, userId);
			
			preparedStatement.executeUpdate();

		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	@Override
	public boolean deleteUser(User user) {
		
		boolean deleted = false;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"DELETE FROM users WHERE USR_ID = ?"
			);
			
			preparedStatement.setInt(1, user.getUserId());
			Integer rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted == 1)
				deleted = true;

		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	
	@Override
	public User authenticate(String email, String password) {
		
		User user = null;
		PreparedStatement preparedStatement = null;

		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM users INNER JOIN roles ON users.USR_ROLE = roles.ROL_ID" +
							" WHERE users.USR_EMAIL = ? and users.USR_PASSWORD = ?;"
			);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				user = getUserFromResultSet(resultSet);

			ConnectionHelper.closeSqlResources(preparedStatement, resultSet);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
