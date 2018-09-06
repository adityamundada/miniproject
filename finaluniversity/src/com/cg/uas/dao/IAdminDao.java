package com.cg.uas.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.dto.ProgramScheduledBean;

public interface IAdminDao {

public boolean addProgramOffered(ProgramOfferedBean programOffered) throws UniversityException;
	
	public boolean updateProgramOrffered(String programnName,String column,String newVal) throws UniversityException;
	
	public boolean delProgramOffered(String programName) throws UniversityException;
	
	public List<ProgramOfferedBean> viewProgramOffered() throws UniversityException;
	
	
	
	
	
	
	public String addSchedule(ProgramScheduledBean programScheBean) throws UniversityException;
	public int deleteSchedule(String scheduleId) throws UniversityException;
	public ArrayList<ProgramScheduledBean> viewSchedule(String startDate, String endDate) throws UniversityException;
	
	
	
}
