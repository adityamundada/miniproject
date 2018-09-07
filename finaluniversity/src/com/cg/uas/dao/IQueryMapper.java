package com.cg.uas.dao;

public interface IQueryMapper {

	public static final String INSERT_ADMIN_QUERY = "INSERT INTO programs_offered values(?,?,?,?,?)";
	
	public static final String VIEW_PROGRAM_NAME = null;
	
	public static final String RETRIEVE_ALL_QUERY = "SELECT programname,description,applicant_eligibility,duration,degree_certificate_offered FROM programs_offered";
	
	public static final String SELECT_ON_PROGRAM_NAME="SELECT programname,description FROM programs_offered where programname=?";
	
	public static final String DELETE_QUERY="delete from programs_offered where programname=?";

	public static final String UPDATE_PROGRAM_OFFERED_NAME = "UPDATE programs_offered set programname=? WHERE programname=?";
	
	public static final String UPDATE_PROGRAM_OFFERED_DESCRIPTION = "UPDATE programs_offered set DESCRIPTION=? WHERE programname=?";
	
	public static final String UPDATE_PROGRAM_OFFERED_APPLICANT_ELIGIBILITY = "UPDATE programs_offered set APPLICANT_ELIGIBILITY=? WHERE programname=?";
	
	public static final String UPDATE_PROGRAM_OFFERED_DURATION = "UPDATE programs_offered set programname=? WHERE DURATION=?";
	
	public static final String UPDATE_PROGRAM_OFFERED_DEGREE_CERTIFICATE_OFFERED = "UPDATE programs_offered set DEGREE_CERTIFICATE_OFFERED=? WHERE programname=?";
	
	
	//chhavis code
	public static final String GET_ROLE="SELECT role FROM Users WHERE login_id=? and password=?";
	public static final String INSERT_APPLICANT_QUERY="INSERT INTO application VALUES(applicationId_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	public static final String APPLICATIONID_QUERY_SEQUENCE="SELECT applicationId_seq.CURRVAL FROM DUAL";
	public static final String VIEW_APPLICATION_STATUS="SELECT status FROM APPLICATION WHERE application_Id=?";
	public static final String GET_ALL_SCHEDULE_PROGRAM_QUERY = "SELECT * FROM programs_scheduled";
	
	///MAC(intosh)
	
	public static final String RETRIEVE_SCHEDULED_PROGRAMS = "SELECT * FROM Programs_Scheduled";
	public static final String RETRIEVE_APPLICANTS = "SELECT * FROM Application WHERE Scheduled_program_id = ?1";
	public static final String SET_STATUS_ACCEPT = "UPDATE Application SET status = 'accepted' WHERE Application_id = ?1";
	public static final String RETRIEVE_APPLICANTS_STATUS_ACCEPTED = "SELECT * from Application where Scheduled_program_id = ?1 AND astatus='accepted'";
	public static final String SET_INTERVIEW_DATE = "UPDATE Application SET Date_Of_Interview = ?1 WHERE Application_id = ?2";
	public static final String SET_STATUS_CONFIRMED = "UPDATE  Application SET status = 'confirmed' WHERE Application_id = ?1";
	public static final String SET_STATUS_REJECT = "UPDATE Application SET status = 'rejected' WHERE Application_id = ?1";
	
	
	
	//gangotry
	
	public static final String insertScheduleQuery = "INSERT INTO Programs_Scheduled VALUES(ProgSchedule_seq.NEXTVAL,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?)";
	public static final String currentScheIdQuery = "SELECT ProgSchedule_seq.currval FROM dual";
	public static final String deleteScheQuery = "DELETE FROM Programs_Scheduled WHERE Scheduled_program_id = ?";
	public static final String viewScheduleQuery = "SELECT * FROM programs_scheduled WHERE start_date >= TO_DATE(?,'DD-MM-YYYY') AND end_date >= TO_DATE(?,'DD-MM-YYYY')";

	
	
}
