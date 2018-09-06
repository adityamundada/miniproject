package com.cg.uas.dto;

import java.util.Date;

public class ProgramScheduledBean {
	
	private String scheduledProgramID;
	private String programName;
	private String location;
	private String startDate;
	private String endDate;
	private String sessionsPerWeek;
	
	
	
	public String getScheduledProgramID() {
		return scheduledProgramID;
	}



	public void setScheduledProgramID(String scheduledProgramID) {
		this.scheduledProgramID = scheduledProgramID;
	}



	public String getProgramName() {
		return programName;
	}



	public void setProgramName(String programName) {
		this.programName = programName;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getSessionsPerWeek() {
		return sessionsPerWeek;
	}



	public void setSessionsPerWeek(String sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}



	@Override
	public String toString() {
		return "ProgramScheduledBean [scheduledProgramID=" + scheduledProgramID
				+ ", programName=" + programName + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", sessionsPerWeek=" + sessionsPerWeek + "]";
	}
	
	
	
	
}
