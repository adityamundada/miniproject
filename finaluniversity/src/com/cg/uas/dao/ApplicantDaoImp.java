package com.cg.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.util.DBConnection;







public class ApplicantDaoImp implements IApplicantDao {
	Logger logger=Logger.getRootLogger();
	

	public ApplicantDaoImp() {
		// TODO Auto-generated constructor stub
		PropertyConfigurator.configure("src//log4j.properties");
	}

	@Override
	public String isValidCred(String loginId, String password) throws UniversityException {
		// TODO Auto-generated method stub
Connection connection=DBConnection.getInstance().getConnection();
	
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		String role=null;
		try {
			preparedStatement=connection.prepareStatement(IQueryMapper.GET_ROLE);
			preparedStatement.setString(1,loginId);
			preparedStatement.setString(2,password);
			resultset=preparedStatement.executeQuery();
			if(resultset.next())
			{
				role=resultset.getString(1);
						
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(role!=null){
			System.out.println("Valid Credentials");
			return role;
			
		}
		else
		{
			System.out.println("Invalid Credentials");
		return null;}
	}

	@Override
	public String addApplicantDetails(ApplicationBean applicationBean)
			throws UniversityException {
		// TODO Auto-generated method stub
Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		
		String applicationID=null;
		SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  
		java.util.Date myDate=null;
		try {
			myDate = format.parse( applicationBean.getDob() );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int queryResult=0;
		try {
			
			preparedStatement=connection.prepareStatement(IQueryMapper.INSERT_APPLICANT_QUERY);
			preparedStatement.setString(1, applicationBean.getName());
			java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() ); 
			preparedStatement.setDate( 2, sqlDate ); 
			preparedStatement.setString(3, applicationBean.getHighestQualification());
			preparedStatement.setString(4, applicationBean.getMarksObtained());
			preparedStatement.setString(5, applicationBean.getGoals());
			preparedStatement.setString(6, applicationBean.getEmailID());
			preparedStatement.setString(7, applicationBean.getScheduledProgramID());
			preparedStatement.setString(8, applicationBean.getStatus());
			preparedStatement.setDate(9, applicationBean.getDoi());
			queryResult=preparedStatement.executeUpdate();
			
			preparedStatement= connection.prepareStatement(IQueryMapper.APPLICATIONID_QUERY_SEQUENCE);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				
				applicationID=resultSet.getString(1);
						
			}
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new UniversityException("Inserting application details failed ");}
			else
			{
				logger.info("Application details added successfully:");
				return applicationID;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
			//throw new UniversityException("Tehnical problem occured refer log");
		}finally
		{
			try 
			{ if(resultSet!=null)
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				logger.error(sqlException.getMessage());
				throw new UniversityException("Error in closing db connection");
			}
			}
		return null;
		

	
	}

	@Override
	public String viewApplicationStatus(int applicationId) throws UniversityException {
		// TODO Auto-generated method stub
Connection connection=DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		String status=null;
		try {
			preparedStatement=connection.prepareStatement(IQueryMapper.VIEW_APPLICATION_STATUS);
	
		preparedStatement.setInt(1,applicationId);
		resultset=preparedStatement.executeQuery();
		
		if(resultset.next())
		{
			status=resultset.getString(1);
			}
		
		if( status!= null)
		{
			logger.info("Record Found Successfully");
			return status;
		}
		else
		{
			logger.info("Record Not Found Successfully");
			return null;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");

			}
		
	}
		return status;

	
	

	}

	@Override
	public List<ProgramScheduledBean> getAllScheduleProgram()
			throws UniversityException {
		// TODO Auto-generated method stub
		Connection con=DBConnection.getInstance().getConnection();
		int count = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<ProgramScheduledBean> programScheduledBeanList=new ArrayList<ProgramScheduledBean>();
		try
		{
			ps=con.prepareStatement(IQueryMapper.GET_ALL_SCHEDULE_PROGRAM_QUERY);
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				ProgramScheduledBean programScheduledBean=new ProgramScheduledBean();
				programScheduledBean.setScheduledProgramID(resultset.getString(1));
				programScheduledBean.setProgramName(resultset.getString(2));
				programScheduledBean.setLocation(resultset.getString(3));
				programScheduledBean.setStartDate(resultset.getString(4));
				programScheduledBean.setEndDate(resultset.getString(5));
				programScheduledBean.setSessionsPerWeek(resultset.getString(5));
				programScheduledBeanList.add(programScheduledBean);
				
				
				count++;
			}			
			
		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new UniversityException("Tehnical problem occured. Refer log");
		}
		
		finally
		{
			try 
			{
				if(resultset!=null)
					resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new UniversityException("Error in closing db connection");

			}
		}
		
		if( count == 0)
			return null;
		else
			return programScheduledBeanList;
		
		
	}
	}


	}
