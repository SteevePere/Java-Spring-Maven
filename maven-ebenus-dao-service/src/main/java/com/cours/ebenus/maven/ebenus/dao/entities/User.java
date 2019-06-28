package com.cours.ebenus.maven.ebenus.dao.entities;

import java.util.Date;


public class User {
	
	
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private Date creationDate;
    private Date editDate;
    private Boolean active = true;
    private Role role;

    
    public User(Integer userId){
    	this.userId = userId;
	}

    
	public User(Integer userId, String firstName, String lastName, String email, String password, 
			Date birthDate, Date creationDate, Date editDate, Boolean active, Role role) {
    	
		this.userId = userId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
    	this.password = password;
    	this.birthDate = birthDate;
    	this.creationDate = creationDate;
    	this.editDate = editDate;
    	this.active = active;
    	this.role = role;
	}

	
	public User(String firstName, String lastName, String email, String password, Date birthDate, Role role) {
		this(0 , firstName, lastName, email, password, birthDate, null, null, true, role);
    }

	
	public User(Integer userId, String firstName, String lastName, String email, String password, Date birthDate, Role role) {
		this(userId, firstName, lastName, email, password, birthDate, null, null, false, role);
	}


	public User(Integer userId, String firstName, String lastName, String email) {
		
		this.userId = userId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
	}

    public User(String lastName,String firstName,Date date,String email,String passw0rd,boolean active,Role roles) {
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.birthDate = date;
    	this.email = email;
    	this.password = passw0rd;
    	this.active = active;
    	this.role = roles;
    }


    public Integer getUserId() {
		return userId;
	}
	

	public Role getRole() {
		return role;
	}

	
	public void setRole(Role role) {
		this.role = role;
	}

	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	
	public Date getEditDate() {
		return editDate;
	}
	
	
	public Boolean isActive() {
		return active;
	}
	
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		User other = (User) obj;
		
		if (userId == null) {
			if (other.userId != null)
				return false;
		} 
		
		else if (!userId.equals(other.userId))
			return false;
		
		return true;
	}

	
	@Override
	public String toString() {
		
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", birthDate=" + birthDate +
				", creationDate=" + creationDate +
				", editDate=" + editDate +
				", active=" + active +
				", role=" + role +
				'}';
	}
	
	
}
