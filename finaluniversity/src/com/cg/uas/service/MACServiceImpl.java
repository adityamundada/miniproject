package com.cg.uas.service;

import java.util.Date;
import java.util.List;

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
}