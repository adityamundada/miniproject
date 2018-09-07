package com.cg.uas.dto;

import java.util.Date;

public class ApplicationBean {
	
	private Integer applicationID;
	private String fullName;
	private Date dateOfBirth;
	private String highestQualification;
	private Integer marksObtained;
	private String goals;
	private String emailID;
	private String scheduledProgramID; 
	private String status;
	private Date dateOfInterview;
	
	public Integer getApplicationID() {
		return applicationID;
	}
	
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getHighestQualification() {
		return highestQualification;
	}
	
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	
	public Integer getMarksObtained() {
		return marksObtained;
	}
	
	public void setMarksObtained(Integer marksObtained) {
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
	
	public Date getDateOfInterview() {
		return dateOfInterview;
	}
	
	public void setDateOfInterview(Date dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}
}