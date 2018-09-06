package com.cg.uas.dto;

public class ParticipantBean {

	private String rollNum;
	private String emailID;
	private String applicationID;
	private String scheduledProgramID;
	public String getRollNum() {
		return rollNum;
	}
	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getScheduledProgramID() {
		return scheduledProgramID;
	}
	public void setScheduledProgramID(String scheduledProgramID) {
		this.scheduledProgramID = scheduledProgramID;
	}
	@Override
	public String toString() {
		return "ParticipantBean [rollNum=" + rollNum + ", emailID=" + emailID
				+ ", applicationID=" + applicationID + ", scheduledProgramID="
				+ scheduledProgramID + "]";
	}
	
	
	
	
	
}
