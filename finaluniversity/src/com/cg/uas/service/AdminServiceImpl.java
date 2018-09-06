package com.cg.uas.service;


import java.util.ArrayList;
import java.util.List;











import com.cg.uas.dao.AdminDaoImpl;
import com.cg.uas.dao.IAdminDao;
import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.dao.AdminDaoImpl;
import com.cg.uas.dto.ProgramScheduledBean;

public class AdminServiceImpl implements IAdminService{
	
	IAdminDao adminDao=null;
	
	@Override
	public boolean addProgramOffered(ProgramOfferedBean programOffered) throws UniversityException {

		adminDao= new AdminDaoImpl();
		boolean check =adminDao.addProgramOffered(programOffered);
		
		return check;
	}

	@Override
	public boolean updateProgramOrffered(String programnName,String column,String newVal) throws UniversityException {
		adminDao= new AdminDaoImpl();
		boolean flag=adminDao.updateProgramOrffered(programnName,column, newVal);

		return flag;
	}

	@Override
	public boolean delProgramOffered(String programName) throws UniversityException {

		boolean flag=adminDao.delProgramOffered(programName);		
		return flag;
	}

	@Override
	public List<ProgramOfferedBean> viewProgramOffered()
			throws UniversityException {

		adminDao= new AdminDaoImpl();
		List<ProgramOfferedBean> programOffered=null;
		programOffered= adminDao.viewProgramOffered();
			
		return programOffered;
	}

	
	/////////////////////////////////////////////////////////////////////////////
	@Override
	public String addSchedule(ProgramScheduledBean programScheBean) throws UniversityException{
		adminDao = new AdminDaoImpl();
		return adminDao.addSchedule(programScheBean);
	}
	
	@Override
	public int deleteSchedule(String scheduleId) throws UniversityException{
		adminDao = new AdminDaoImpl();
		return adminDao.deleteSchedule(scheduleId);
	}
	
	@Override
	public ArrayList<ProgramScheduledBean> viewSchedule(String startDate, String endDate) throws UniversityException{
		adminDao = new AdminDaoImpl();
		return adminDao.viewSchedule(startDate, endDate);
	}
	
	
	
}
