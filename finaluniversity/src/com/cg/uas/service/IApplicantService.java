package com.cg.uas.service;

import java.util.List;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;

public interface IApplicantService {
	
	public Boolean isValidApplication(ApplicationBean applicationBean) throws UniversityException;
	
	public String addApplicantDetails(ApplicationBean applicationBean) throws UniversityException;
	
	public String viewApplicationStatus(int applicationId) throws UniversityException;
	
	public List<ProgramScheduledBean> getAllScheduleProgram() throws UniversityException;
}
