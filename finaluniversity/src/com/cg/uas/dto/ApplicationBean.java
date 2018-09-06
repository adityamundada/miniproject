package com.cg.uas.dto;

import java.sql.Date;

public class ApplicationBean {
	private String applicationID;
	private String name;
	private String dob;
	private String highestQualification;
	private String marksObtained;
	private String goals;
	private String emailID;
	private String scheduledProgramID; 
	private String status;
	private Date doi;
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getScheduledProgramID() {
		return scheduledProgramID;
	}
	public void setScheduledProgramID(String scheduledProgramID) {
		this.scheduledProgramID = scheduledProgramID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDoi() {
		return doi;
	}
	public void setDoi(Date doi) {
		this.doi = doi;
	}
	
	@Override
	public String toString() {
		return "ApplicationBean [applicationID=" + applicationID + ", name="
				+ name + ", dob=" + dob + ", highestQualification="
				+ highestQualification + ", marksObtained=" + marksObtained
				+ ", goals=" + goals + ", emailID=" + emailID
				+ ", scheduledProgramID=" + scheduledProgramID + ", status="
				+ status + ", doi=" + doi + "]";

}
}
