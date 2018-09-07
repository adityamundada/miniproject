package com.cg.uas.dto;

public class ProgramOfferedBean {

	private String programName;
	private String description;
	private String applicantEligibility;
	private Integer duration;
	private String degreeCertificateOffered;
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getApplicantEligibility() {
		return applicantEligibility;
	}
	
	public void setApplicantEligibility(String applicantEligibility) {
		this.applicantEligibility = applicantEligibility;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getDegreeCertificateOffered() {
		return degreeCertificateOffered;
	}
	
	public void setDegreeCertificateOffered(String degreeCertificateOffered) {
		this.degreeCertificateOffered = degreeCertificateOffered;
	}
}