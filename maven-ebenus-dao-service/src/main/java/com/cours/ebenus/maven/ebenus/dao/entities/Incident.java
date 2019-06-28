package com.cours.ebenus.maven.ebenus.dao.entities;

import java.util.Date;


public class Incident {
	
	
    private Integer incidentId;
    private IncidentType incidentType;
    private Date incidentDate;
    private Double incidentLatitude;
    private Double incidentLongitude;
    private User createdBy;
    private Date creationDate;
    private Date editDate;
    
    
    public Incident(Integer incidentId, IncidentType incidentType, Date incidentDate,  
			Double incidentLatitude, Double incidentLongitude, 
			User createdBy, Date creationDate, Date editDate) {

    	this.incidentId = incidentId;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.incidentLatitude = incidentLatitude;
		this.incidentLongitude = incidentLongitude;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.editDate = editDate;
	}
    
    
    public Incident(IncidentType incidentType, Date incidentDate,  
			Double incidentLatitude, Double incidentLongitude, 
			User createdBy, Date creationDate, Date editDate) {

		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.incidentLatitude = incidentLatitude;
		this.incidentLongitude = incidentLongitude;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.editDate = editDate;
	}


	public Incident(Integer incidentId, IncidentType incidentType, Date incidentDate, Double incidentLatitude, Double incidentLongitude,
			Date editDate) {
		
		this.incidentId = incidentId;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.incidentLatitude = incidentLatitude;
		this.incidentLongitude = incidentLongitude;
		this.editDate = editDate;
	}

	public Incident(int incidentId,IncidentType incidentType,Date incidentDate,double incidentLatitude,double incidentLongitude,User createdBy,Date editDate) {
		this.incidentId = incidentId;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.incidentLatitude = incidentLatitude;
		this.incidentLongitude = incidentLongitude;
		this.createdBy = createdBy;
		this.editDate = editDate;
	}

	public Integer getIncidentId() {
		return incidentId;
	}
	
	
	public void setIncidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}
	
	
	public IncidentType getIncidentType() {
		return incidentType;
	}
	
	
	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}
	
	
	public Date getIncidentDate() {
		return incidentDate;
	}
	
	
	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}
	
	
	public Double getIncidentLatitude() {
		return incidentLatitude;
	}
	
	
	public void setIncidentLatitude(Double incidentLatitude) {
		this.incidentLatitude = incidentLatitude;
	}
	
	
	public Double getIncidentLongitude() {
		return incidentLongitude;
	}
	
	
	public void setIncidentLongitude(Double incidentLongitude) {
		this.incidentLongitude = incidentLongitude;
	}
	
	
	public User getCreatedBy() {
		return createdBy;
	}
	
	
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	public Date getEditDate() {
		return editDate;
	}
	
	
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}


	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((incidentId == null) ? 0 : incidentId.hashCode());
		
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
		
		Incident other = (Incident) obj;
		
		if (incidentId == null) {
			
			if (other.incidentId != null)
				return false;
		} 
		
		else if (!incidentId.equals(other.incidentId))
			return false;
		
		return true;
	}


	@Override
	public String toString() {
		
		return "Incident [incidentId=" + incidentId + ", incidentType=" + incidentType + ", incidentDate="
				+ incidentDate + ", incidentLatitude=" + incidentLatitude + ", incidentLongitude=" + incidentLongitude
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", editDate=" + editDate + "]";
	}
	
	
}
