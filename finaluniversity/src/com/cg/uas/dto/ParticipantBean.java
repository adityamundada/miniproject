package com.cg.uas.dto;

public class ParticipantBean {

	private String rollNumber;
	private String emailID;
	private Integer applicationID;
	private String scheduledProgramID;
	
	public String getRollNumber() {
		return rollNumber;
	}
	
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	public String getEmailID() {
		return emailID;
	}
	
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public Integer getApplicationID() {
		return applicationID;
	}
	
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	
	public String getScheduledProgramID() {
		return scheduledProgramID;
	}
	
	public void setScheduledProgramID(String scheduledProgramID) {
		this.scheduledProgramID = scheduledProgramID;
	}	
}