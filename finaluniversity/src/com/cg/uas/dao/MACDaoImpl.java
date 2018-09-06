package com.cg.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.util.DBConnection;

public class MACDaoImpl implements IMACDao {
	
	Logger logger=Logger.getLogger(AdminDaoImpl.class);
	
	public MACDaoImpl() {
		PropertyConfigurator.configure("src//log4j.properties");	
	}
	
	@Override
	public List<ProgramScheduledBean> viewAllScheduledPrograms() throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer programCount = 0;
		List<ProgramScheduledBean> programScheduledList = new ArrayList<ProgramScheduledBean>();
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.RETRIEVE_SCHEDULED_PROGRAMS);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {	
				ProgramScheduledBean programScheduled = new ProgramScheduledBean();
				programScheduled.setScheduledProgramID(resultSet.getString(1));
				programScheduled.setProgramName(resultSet.getString(2));
				programScheduled.setLocation(resultSet.getString(3));
				programScheduled.setStartDate(resultSet.getString(4));
				programScheduled.setEndDate(resultSet.getString(5));
				programScheduled.setSessionsPerWeek(resultSet.getString(6));
				
				programScheduledList.add(programScheduled);
				programCount++;
			}			
		} 
		catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured. Refer log");
		}		
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
		
		if(programCount == 0)
			return null;
		else
			return programScheduledList;
	}

	@Override
	public List<ApplicationBean> viewApplicant(String scheduledProgramId) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer studentCount = 0;
		List<ApplicationBean> applicationList = new ArrayList<ApplicationBean>();
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.RETRIEVE_APPLICANTS);
			preparedStatement.setString(1, scheduledProgramId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {	
				ApplicationBean applicationBean = new ApplicationBean();
				
				applicationBean.setApplicationID(resultSet.getString(1));
				applicationBean.setName(resultSet.getString(2));
				applicationBean.setDob(resultSet.getString(3));
				applicationBean.setHighestQualification(resultSet.getString(4));
				applicationBean.setMarksObtained(resultSet.getString(5));
				applicationBean.setGoals(resultSet.getString(6));
				applicationBean.setEmailID(resultSet.getString(7));
				applicationBean.setScheduledProgramID(resultSet.getString(8));
				applicationBean.setStatus(resultSet.getString(9));
				applicationBean.setDoi(resultSet.getString(10)));
				
				applicationList.add(applicationBean);
				studentCount++;
			}			
		} 
		catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured. Refer log");
		}		
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
		
		if(studentCount == 0)
			return null;
		else
			return applicationList;
	}

	@Override
	public ApplicationBean accept(Integer applicationId) throws UniversityException {
		Query query = entityManager.createQuery(IQueryMapper.SET_STATUS_ACCEPT);
		query.setParameter(1, applicationId);
		query.executeUpdate();
		return null;
	}

	@Override
	public List<ApplicationBean> confirmedApplicants(String scheduledProgramId) throws UniversityException {
		TypedQuery<ApplicationBean> tQuery = entityManager.createQuery(IQueryMapper.RETRIEVE_APPLICANTS_STATUS_ACCEPTED, ApplicationBean.class);
		tQuery.setParameter(1, scheduledProgramId);
		return tQuery.getResultList();
	}

	@Override
	public ApplicationBean interview(Integer applicationId, Date date) throws UniversityException {
		Query query = entityManager.createQuery(IQueryMapper.SET_INTERVIEW_DATE);
		query.setParameter(1, date);
		query.setParameter(2, applicationId);
		query.executeUpdate();
		return null;
	}

	@Override
	public ApplicationBean confirm(Integer applicationId) throws UniversityException {
		Query query = entityManager.createQuery(IQueryMapper.SET_STATUS_CONFIRMED);
		query.setParameter(1, applicationId);
		query.executeUpdate();
		return null;
	}

	@Override
	public ApplicationBean reject(Integer applicationId) throws UniversityException {
		Query query = entityManager.createQuery(IQueryMapper.SET_STATUS_REJECT);
		query.setParameter(1, applicationId);
		query.executeUpdate();
		return null;
	}
}