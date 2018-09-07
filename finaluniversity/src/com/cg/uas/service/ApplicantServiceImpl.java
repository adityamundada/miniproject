package com.cg.uas.service;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.uas.dao.ApplicantDaoImp;
import com.cg.uas.dao.IApplicantDao;
import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;

public class ApplicantServiceImpl implements IApplicantService {
	IApplicantDao dao=new ApplicantDaoImp();
	@Override
	public Boolean isValidApplication(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub
		Pattern namePattern=Pattern.compile("^[A-Za-z\\s]{3,}$");
		Matcher nameMatcher=namePattern.matcher(applicationBean.getName());
		Pattern dobPattern=Pattern.compile("^[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}$");
		Matcher dobMatcher=dobPattern.matcher(applicationBean.getDateOfBirth());
		Pattern goalPattern=Pattern.compile("^[A-Za-z\\s]{1,}$");
		Matcher goalMatcher=goalPattern.matcher(applicationBean.getGoals());
		Pattern marksPattern=Pattern.compile("^[1-9][0-9]?$|^100$");
		Matcher marksMatcher=marksPattern.matcher(applicationBean.getMarksObtained());
		Pattern scheduleIdPattern=Pattern.compile("^[0-9]{1,2}$");
		Matcher scheduleIdMatcher=scheduleIdPattern.matcher(applicationBean.getScheduledProgramID());
		Pattern emailPattern=Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		Matcher emailMatcher=emailPattern.matcher(applicationBean.getEmailID());
		
		
		if(!(nameMatcher.matches()))
		{
			System.out.println("\nApplicant Name Should Be In Alphabets and minimum 1 characters long.");
			return false;
		}
		if(!(dobMatcher.matches()))
		{
			System.out.println("\nDate of birth should be in DD/MM/YYYY format.");
			return false;
		}
		if(!(goalMatcher.matches()))
		{
			System.out.println("\n Goal should be alphabetical and having minimum one letter");
			return false;
		}
		if(!(marksMatcher.matches()))
		{
			System.out.println("\nMarks should be less than 100");
			return false;
		}
		if(!(scheduleIdMatcher.matches()))
		{
			System.out.println("\nSchedule Id should be of one or two digit");
			return false;
		}
		if(!(emailMatcher.matches()))
		{
			System.out.println("\nEnter valid email address");
			return false;
		}
		
		return true;
	}

	@Override
	public String addApplicantDetails(ApplicationBean applicationBean)
			throws UniversityException {
		// TODO Auto-generated method stub
		String applicationID=null;
		applicationID=dao.addApplicantDetails(applicationBean);
		return applicationID;
	}

	@Override
	public String viewApplicationStatus(int applicationId)
			throws UniversityException {
		// TODO Auto-generated method stub
		String status=null;
		status=dao.viewApplicationStatus(applicationId);
		return status;
	}

	

		@Override
		public List<ProgramScheduledBean> getAllScheduleProgram()
				throws UniversityException {
			// TODO Auto-generated method stub
			return dao.getAllScheduleProgram();
		}	}


