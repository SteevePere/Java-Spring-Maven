package com.cours.ebenus.maven.ebenus.dao.entities;


public class IncidentType {
	
	
    private Integer incidentTypeId;
    private String incidentTypeName;
    
    
    public IncidentType(String incidentTypeName) {
    	
		this.incidentTypeName = incidentTypeName;
    }
    
    
    public IncidentType(Integer incidentTypeId, String incidentTypeName) {
    	
    	this.incidentTypeId = incidentTypeId;
		this.incidentTypeName = incidentTypeName;
    }
    
    
	public Integer getIncidentTypeId() {
		return incidentTypeId;
	}
	
	
	public void setIncidentTypeId(Integer incidentTypeId) {
		this.incidentTypeId = incidentTypeId;
	}
	
	
	public String getIncidentTypeName() {
		return incidentTypeName;
	}
	
	
	public void setIncidentTypeName(String incidentTypeName) {
		this.incidentTypeName = incidentTypeName;
	}


	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((incidentTypeId == null) ? 0 : incidentTypeId.hashCode());
		
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
		
		IncidentType other = (IncidentType) obj;
		
		if (incidentTypeId == null) {
			
			if (other.incidentTypeId != null)
				return false;
		} 
		
		else if (!incidentTypeId.equals(other.incidentTypeId))
			return false;
		
		return true;
	}


	@Override
	public String toString() {
		
		return "IncidentType [incidentTypeId=" + incidentTypeId + ", incidentTypeName=" + incidentTypeName + "]";
	}
	

}
