package com.cg.uas.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;

public interface IApplicantDao {

	 
		// TODO Auto-generated constructor stub
		public String isValidCred(String loginId,String password) throws UniversityException;
		public String addApplicantDetails(ApplicationBean applicationBean)throws UniversityException;
		public String viewApplicationStatus(int applicationId) throws UniversityException;
		public List<ProgramScheduledBean> getAllScheduleProgram() throws UniversityException;
}
