package com.cours.ebenus.dao.entities;


public class Role {

    private static final long serialVersionUID = 1L;
    private Integer idRole;
    private String identifiant;
    private String description;
    private Integer version = 0;

    public Role() {
    }

    public Role(Integer idRole, String identifiant, String description) {
        this.idRole = idRole;
        this.identifiant = identifiant;
        this.description = description;
    }

    public Role(String identifiant, String description) {
        this(null, identifiant, description);
    }

    public Role(Integer idRole) {
        this(idRole, null, null);
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getIdentifiant() {
        return (this.identifiant);
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @Override
	public int hashCode() {
    	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		
		return result;
	}

	@Override
	public String toString() {
		
		return "Role [idRole=" + getIdRole() + ", identifiant=" + getIdentifiant() + ", description=" + getDescription() + ", version="
				+ getVersion() + "]";
	}

	@Override
	public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

		if (this.getClass() != other.getClass()) {
      		return false;
		}

		if (!this.getIdRole().equals(((Role)other).getIdRole())) {
			return false;
		}

		return true;
    }

}
