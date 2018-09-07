package com.cg.uas.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.DateValidator;

import com.cg.uas.dao.IMACDao;
import com.cg.uas.dao.MACDaoImpl;
import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;


public class MACServiceImpl implements IMACService {
	IMACDao MACdao = new MACDaoImpl();
	
	@Override
	public List<ProgramScheduledBean> viewAllScheduledPrograms() throws UniversityException {
		return MACdao.viewAllScheduledPrograms();
	}
	
	@Override
	public List<ApplicationBean> viewApplicant(String scheduledProgramId) throws UniversityException {
		return MACdao.viewApplicant(scheduledProgramId);
	}
	
	@Override
	public Boolean accept(Integer applicationId) throws UniversityException {
		return MACdao.accept(applicationId);
	}
	
	@Override
	public List<ApplicationBean> acceptedApplicants(String scheduledProgramId) throws UniversityException {
		return MACdao.acceptedApplicants(scheduledProgramId);
	}
	
	@Override
	public Boolean interview(Integer applicationId, Date date) throws UniversityException {
		return MACdao.interview(applicationId, date);
	}
	
	@Override
	public Boolean confirm(Integer applicationId) throws UniversityException {
		return MACdao.confirm(applicationId);
	}
	
	@Override
	public Boolean reject(Integer applicationId) throws UniversityException {
		return MACdao.reject(applicationId);
	}
	

	public boolean checkDateSyntax(String date) throws UniversityException {
		String errorMessage = null;
		Pattern datePattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|30|31])([-])(0[1-9]|10|11|12)([-])(2018|2019)");
		Matcher dateMatcher = datePattern.matcher(date);
		
		if(!(dateMatcher.matches())) {
			errorMessage = "Enter correct date format";
		}
		
		if(!errorMessage.isEmpty()) {
			throw new UniversityException(errorMessage);
		}
		else
			return true;
	}
	
	public boolean checkDateLogic(String date) throws UniversityException {
		DateValidator validator = DateValidator.getInstance(); // Get the date validator
		return validator.validate(date, Locale.ENGLISH) != null; // Validate the date
	}
}