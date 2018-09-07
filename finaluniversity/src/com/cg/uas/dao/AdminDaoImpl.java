package com.cg.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.util.DBConnection;
import com.cg.uas.dao.IQueryMapper;
import com.cg.uas.dto.ProgramScheduledBean;

public class AdminDaoImpl implements IAdminDao {
	
	Logger logger= Logger.getLogger(AdminDaoImpl.class);
	
	public AdminDaoImpl() {	
		PropertyConfigurator.configure("src//log4j.properties");
	}
	
	@Override
	public boolean addProgramOffered(ProgramOfferedBean programOffered) throws UniversityException {
		
		Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		
		int queryResult = 0;
		try {
			if(connection == null) {
				logger.fatal("Accessing null pointers");
			}			
			preparedStatement=connection.prepareStatement(IQueryMapper.INSERT_ADMIN_QUERY);
			preparedStatement.setString(1,programOffered.getProgramName());			
			preparedStatement.setString(2,programOffered.getDescription());
			preparedStatement.setString(3,programOffered.getApplicantEligibility());
			preparedStatement.setInt(4,programOffered.getDuration());
			preparedStatement.setString(5,programOffered.getDegreeCertificateOffered());
			
			queryResult = preparedStatement.executeUpdate();
		
			if(queryResult == 0) {
				logger.error("Insertion failed ");
				throw new UniversityException("Inserting program offered details failed ");
			}
			else {
				logger.info("Program offered details added successfully:");
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
				throw new UniversityException("Error in closing db connection");
			}
		}
	}

	@Override
	public boolean updateProgramOrffered(String programName, String column, String newVal) throws UniversityException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet resultSet = null;
		int queryResult = 0;
		try {
			ps1 = connection.prepareStatement(IQueryMapper.SELECT_ON_PROGRAM_NAME);
			ps1.setString(1, programName);
						
			resultSet = ps1.executeQuery();
			if(resultSet==null || !resultSet.next()){
				throw new UniversityException("no such program is found with given name");
			}
			else {
				switch(column) {
					case "1":
						ps2 = connection.prepareStatement(IQueryMapper.UPDATE_PROGRAM_OFFERED_NAME);
						break;
					case "2":
						ps2 = connection.prepareStatement(IQueryMapper.UPDATE_PROGRAM_OFFERED_DESCRIPTION);
						break;
					case "3":
						ps2 = connection.prepareStatement(IQueryMapper.UPDATE_PROGRAM_OFFERED_APPLICANT_ELIGIBILITY);
						break;
					case "4":
						ps2 = connection.prepareStatement(IQueryMapper.UPDATE_PROGRAM_OFFERED_DURATION);
						break;
					case "5":
						ps2 = connection.prepareStatement(IQueryMapper.UPDATE_PROGRAM_OFFERED_DEGREE_CERTIFICATE_OFFERED);
						break;
					case "6":
						break;
					default:
						throw new UniversityException("Please enter a valid column number");
				}
				ps2.setString(1, newVal);
				ps2.setString(2, programName);
				
				queryResult = ps2.executeUpdate();
				
				if(queryResult == 0) {
					logger.error("Insertion failed ");
					throw new UniversityException("Updating program offered details failed ");
				}
				else {
					logger.info("Program offered details updted successfully:");
					return true;
				}
			}	
		}
		catch (UniversityException | SQLException e) {
			logger.error(e.getMessage());
			throw new UniversityException("Updating Program offered details failed ");
		}
	}

	@Override
	public boolean delProgramOffered(String programName) throws UniversityException {
		Connection connection=DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultset = null;
		int queryResult = 0;
		
		try {
			preparedStatement=connection.prepareStatement(IQueryMapper.SELECT_ON_PROGRAM_NAME);
			preparedStatement.setString(1, programName);

			resultset = preparedStatement.executeQuery();
			if(!resultset.next()) {
				throw new UniversityException("No data of program is found!");
			}
			else {
				preparedStatement2 = connection.prepareStatement(IQueryMapper.DELETE_QUERY);
				preparedStatement2.setString(1, programName);
				queryResult = preparedStatement2.executeUpdate();
			}
			if(queryResult == 0) {
				logger.error("deletion failed ");
				throw new UniversityException("Deleting mobile details failed ");
			}
			else {
				logger.info("mobile details deleted successfully:");
				return true;
			}
		} 
		catch (SQLException e) {
			logger.error(e.getMessage());
			throw new UniversityException("Tehnical problem occured. Refer log");
		}
		finally {
			try {
				if(resultset != null)
					resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) {
					logger.error(e.getMessage());
					throw new UniversityException("Error in closing db connection");
				}
			}
	}

	@Override
	public List<ProgramOfferedBean> viewProgramOffered() throws UniversityException {
		Connection con = DBConnection.getInstance().getConnection();
		int studentCount = 0;
		PreparedStatement ps = null;
		ResultSet resultset = null;
		
		List<ProgramOfferedBean> programOfferedList = new ArrayList<ProgramOfferedBean>();
		try {
			ps = con.prepareStatement(IQueryMapper.RETRIEVE_ALL_QUERY);
			resultset = ps.executeQuery();
			
			while(resultset.next()) {
				ProgramOfferedBean programOffered = new ProgramOfferedBean();
				programOffered.setProgramName(resultset.getString(1));
				programOffered.setDescription(resultset.getString(2));
				programOffered.setApplicantEligibility(resultset.getString(3));
				programOffered.setDuration(resultset.getInt(4));
				programOffered.setDegreeCertificateOffered(resultset.getString(5));
			
				programOfferedList.add(programOffered);
				
				studentCount++;
			}			
		} 
		catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured. Refer log");
		}
		finally {
			try {
				if(resultset != null)
					resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");
			}
		}
		if(studentCount == 0)
			return null;
		else
			return programOfferedList;
	}
	
	//insert program schedule
	@Override
	public String addSchedule(ProgramScheduledBean programScheBean) throws UniversityException{
		Connection con = null;
		ResultSet res = null;
		int count = 0;
		String id = null;
		PreparedStatement psmt = null;
		
		try {
			con = DBConnection.getInstance().getConnection();
			
			psmt = con.prepareStatement(IQueryMapper.insertScheduleQuery);
			psmt.setString(1, programScheBean.getProgramName());
			psmt.setString(2, programScheBean.getLocation());
			psmt.setDate(3, programScheBean.getStartDate());
			psmt.setDate(4, programScheBean.getEndDate());
			psmt.setInt(5, programScheBean.getSessionsPerWeek());
			
			count = psmt.executeUpdate();
			
			if(count > 0) {
				logger.info("Schedule inserted Successfully");
				psmt = con.prepareStatement(IQueryMapper.currentScheIdQuery);
				res = psmt.executeQuery();
				if(res.next())
					id = res.getString(1);
			}
			else {
				logger.info("error in inserting schedule");
				throw new UniversityException("Insertion failed.");
			}
		}
		catch(UniversityException | SQLException ue){
			logger.error(ue.getMessage());
			throw new UniversityException("some technical problem occured");
		}
		finally {
			try {
				if(res != null) {
					res.close();
					psmt.close();
					con.close();
				}
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");
			}
		}
		return id;
	}
	
	//delete program schedule
	@Override
	public int deleteSchedule(String scheduleId) throws UniversityException{
		Connection con = null;
		int count = 0;
		PreparedStatement psmt = null;
		
		try {
			con = DBConnection.getInstance().getConnection();
			
			psmt = con.prepareStatement(IQueryMapper.deleteScheQuery);
			psmt.setString(1, scheduleId);
			
			count = psmt.executeUpdate();
			
			if(count > 0) {
				logger.info("Deletion Successful");
			}
			else {
				logger.error("Deletion fails");
				throw new UniversityException("Error in deletion");
			}
		} 
		catch(UniversityException | SQLException ue) {
			logger.error(ue.getMessage());
			throw new UniversityException("some technical problem occured");
		}
		finally {
			try {
				psmt.close();
				con.close();
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");
			}
		}
		return count;
	}
	
	//view program schedule according to given time period
	@Override
	public ArrayList<ProgramScheduledBean> viewSchedule(String startDate, String endDate) throws UniversityException {
		Connection con = null;
		ArrayList<ProgramScheduledBean> programs = new ArrayList<ProgramScheduledBean>();	
		ResultSet res = null;
		int count = 0;
		PreparedStatement psmt = null;
		
		try {
			con = DBConnection.getInstance().getConnection();
			psmt = con.prepareStatement(IQueryMapper.viewScheduleQuery);
			psmt.setString(1, startDate);
			psmt.setString(2, endDate);
			res = psmt.executeQuery();
			
			while(res.next()){
				ProgramScheduledBean bean = new ProgramScheduledBean();
				bean.setScheduledProgramID(res.getString(1));
				bean.setProgramName(res.getString(2));
				bean.setLocation(res.getString(3));
				bean.setStartDate(res.getDate(4));
				bean.setEndDate(res.getDate(5));
				bean.setSessionsPerWeek(res.getInt(6));
				
				programs.add(bean);
				count++;
			}
		}
		catch(SQLException ue) {
			logger.error(ue.getMessage());
			throw new UniversityException("some technical problem occured");
		}
		finally {
			try {
				res.close();
				psmt.close();
				con.close();
			} 
			catch (SQLException e) {
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");

			}
		}
		if(count == 0)
			return null;
		else
			return programs;
	}
}
