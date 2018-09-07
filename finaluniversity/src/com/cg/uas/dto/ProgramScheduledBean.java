package com.cg.uas.dto;

import java.util.Date;

public class ProgramScheduledBean {
	
	private String scheduledProgramID;
	private String programName;
	private String location;
	private Date startDate;
	private Date endDate;
	private Integer sessionsPerWeek;
	
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
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getSessionsPerWeek() {
		return sessionsPerWeek;
	}

	public void setSessionsPerWeek(Integer sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}	
}