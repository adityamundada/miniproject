package com.cg.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.ApplicationBean;
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
				programScheduled.setStartDate(resultSet.getDate(4));
				programScheduled.setEndDate(resultSet.getDate(5));
				programScheduled.setSessionsPerWeek(resultSet.getInt(6));
				
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
				
				applicationBean.setApplicationID(resultSet.getInt(1));
				applicationBean.setFullName(resultSet.getString(2));
				applicationBean.setDateOfBirth(resultSet.getDate(3));
				applicationBean.setHighestQualification(resultSet.getString(4));
				applicationBean.setMarksObtained(resultSet.getInt(5));
				applicationBean.setGoals(resultSet.getString(6));
				applicationBean.setEmailID(resultSet.getString(7));
				applicationBean.setScheduledProgramID(resultSet.getString(8));
				applicationBean.setStatus(resultSet.getString(9));
				applicationBean.setDateOfInterview(resultSet.getDate(10));
				
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
	public Boolean accept(Integer applicationId) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer queryResult = 0;
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.SET_STATUS_ACCEPT);
			preparedStatement.setInt(1, applicationId);	
			queryResult = preparedStatement.executeUpdate();
			
			if(queryResult == 0) {
				logger.error("Update failed ");
				throw new UniversityException("Updating applicant's status to 'Accepted' failed");
			}
			else {
				logger.info("Updated applicant's status to 'Accepted' successfully.");
				return true;
			}
		}
		catch(SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured refer log");
		}
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
	}

	@Override
	public List<ApplicationBean> acceptedApplicants(String scheduledProgramId) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer studentCount = 0;
		List<ApplicationBean> applicationList = new ArrayList<ApplicationBean>();
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.RETRIEVE_APPLICANTS_STATUS_ACCEPTED);
			preparedStatement.setString(1, scheduledProgramId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {	
				ApplicationBean applicationBean = new ApplicationBean();
				
				applicationBean.setApplicationID(resultSet.getInt(1));
				applicationBean.setFullName(resultSet.getString(2));
				applicationBean.setDateOfBirth(resultSet.getDate(3));
				applicationBean.setHighestQualification(resultSet.getString(4));
				applicationBean.setMarksObtained(resultSet.getInt(5));
				applicationBean.setGoals(resultSet.getString(6));
				applicationBean.setEmailID(resultSet.getString(7));
				applicationBean.setScheduledProgramID(resultSet.getString(8));
				applicationBean.setStatus(resultSet.getString(9));
				applicationBean.setDateOfInterview(resultSet.getDate(10));
				
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
	public Boolean interview(Integer applicationId, Date date) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer queryResult = 0;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		final String stringDate = dateFormat.format(date);
		final java.sql.Date sqlDate = java.sql.Date.valueOf(stringDate);
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.SET_INTERVIEW_DATE);
			preparedStatement.setDate(1, sqlDate);	
			preparedStatement.setInt(2, applicationId);	
			queryResult = preparedStatement.executeUpdate();
			
			if(queryResult == 0) {
				logger.error("Update failed");
				throw new UniversityException("Updating applicant's interview date failed");
			}
			else {
				logger.info("Updated applicant's interview date successfully.");
				return true;
			}
		}
		catch(SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured refer log");
		}
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
	}

	@Override
	public Boolean confirm(Integer applicationId) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer queryResult = 0;
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.SET_STATUS_CONFIRMED);
			preparedStatement.setInt(1, applicationId);	
			queryResult = preparedStatement.executeUpdate();
			
			if(queryResult == 0) {
				logger.error("Update failed ");
				throw new UniversityException("Updating applicant's status to 'Confirmed' failed");
			}
			else {
				logger.info("Updated applicant's status to 'Confirmed' successfully.");
				return true;
			}
		}
		catch(SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured refer log");
		}
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
		
		
	}

	@Override
	public Boolean reject(Integer applicationId) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer queryResult = 0;
		
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.SET_STATUS_REJECT);
			preparedStatement.setInt(1, applicationId);	
			queryResult = preparedStatement.executeUpdate();
			
			if(queryResult == 0) {
				logger.error("Update failed ");
				throw new UniversityException("Updating applicant's status to 'Rejected' failed");
			}
			else {
				logger.info("Updated applicant's status to 'Rejected' successfully.");
				return true;
			}
		}
		catch(SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured refer log");
		}
		finally {
			try {
				if(resultSet != null)
					resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UniversityException("Error in closing database connection");
			}
		}
	}
}