package com.cg.uas.service;

import java.util.Date;
import java.util.List;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;

public interface IMACService {
	
	/* Retrieves a list of scheduled programs details */
	public List<ProgramScheduledBean> viewAllScheduledPrograms() throws UniversityException;
	
	/* Retrieves a list of all applicants for a scheduled program ID */
	public List<ApplicationBean> viewApplicant(String scheduledProgramId) throws UniversityException;
	
	/* Updates applicant's status as ACCEPTED, given his/her application ID */
	public Boolean accept(Integer applicationId) throws UniversityException;

	/* Retrieves a list of students with status as ACCEPTED and given scheduled program ID */
	public List<ApplicationBean> acceptedApplicants(String scheduledProgramId) throws UniversityException;
	
	/* Sets applicant's date of interview, given his/her application ID */
	public Boolean interview(Integer applicationId, Date date) throws UniversityException;

	/* Updates applicant's status as CONFIRMED (yay!), given his/her application ID */
	public Boolean confirm(Integer applicationId) throws UniversityException;

	/* Updates applicant's status as REJECTED (oops), given his/her application ID */
	public Boolean reject(Integer applicationId) throws UniversityException;
}