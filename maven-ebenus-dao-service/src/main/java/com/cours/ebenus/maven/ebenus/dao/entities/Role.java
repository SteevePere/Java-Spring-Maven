package com.cours.ebenus.maven.ebenus.dao.entities;


public class Role {
	
	
	private Integer roleId;
	private String roleName;
    
	
	public Role() {

	}
	
	
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	
	public Role(String roleName) {
		this.roleName = roleName;
	}


	public  Role(Integer idRole, String roleName) {

		this.roleId = idRole;
		this.roleName = roleName;
	}
	
    
	public Integer getRoleId() {
		return roleId;
	}

	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	
	public String getRoleName() {
		return roleName;
	}

	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		
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
		
		Role other = (Role) obj;
		
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} 
		
		else if (!roleId.equals(other.roleId))
			return false;
		
		return true;
	}

	
	@Override
	public String toString() {
	
		return "Role [idRole=" + roleId + ", nameRole=" + roleName +"]";
	}

	
}