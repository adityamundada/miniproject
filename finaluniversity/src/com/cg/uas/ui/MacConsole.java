package com.cg.uas.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IMACService;
import com.cg.uas.service.MACServiceImpl;

public class MacConsole {
	
	private static Scanner scanner;
	
	public static void start() throws UniversityException {
		String choice;
		Integer option;
		IMACService iMacService = new MACServiceImpl();
		do {
			scanner = new Scanner(System.in);
			System.out.println("--------------Welcome to Capgemini University MAC Portal--------------");
			System.out.println("\n");
			System.out.println("1. Scheduled programs details");
			System.out.println("2. Update applicant status after conducting interview");
			System.out.println("3. Logout");
			System.out.println("----------------------------------------------------------------------");
			System.out.println("\nEnter your choice");

			choice = scanner.nextLine();
			option = Integer.parseInt(choice);

			switch (option) {
			case 1:
				List<ProgramScheduledBean> programScheduledList = new ArrayList<ProgramScheduledBean>();
				List<ApplicationBean> applicantList = new ArrayList<ApplicationBean>();
				try {
					programScheduledList = iMacService.viewAllScheduledPrograms();
					if (programScheduledList.size() > 0) {
						printScheduledPrograms(programScheduledList);
					}
					
					System.out.println("Enter the scheduled program ID for which you want to view the applicants: ");
					String programId = scanner.nextLine();
					applicantList = iMacService.viewApplicant(programId);
					printApplicantDetails(applicantList);
					
					System.out.println("Enter the applicant ID for which you want to update status: ");
					String applicantIdString = scanner.nextLine();
					Integer applicantId = Integer.parseInt(applicantIdString);
					
					System.out.println("--------------------------");
					System.out.println("1. Accept applicant");
					System.out.println("2. Reject applicant");
					System.out.println("--------------------------");
					String choice2 = scanner.nextLine();
					Integer option2 = Integer.parseInt(choice2);
					switch(option2) {
						case 1 : 
							Boolean flag = iMacService.accept(applicantId);
							if(flag) {
								System.out.println("~~~~~ Applicant accepted successfully ~~~~~");
								System.out.println("\nPlease enter interview date");
								try {
									String dateInString = scanner.nextLine();
									
									Boolean dateFlagForSyntax = iMacService.checkDateSyntax(dateInString);
									
									Boolean dateFlagForLogic = iMacService.checkDateLogic(dateInString);
									
									if(dateFlagForSyntax && dateFlagForLogic) {
										DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
										Date date = dateFormatter.parse(dateInString);
										Boolean interviewFlag = iMacService.interview(applicantId, date);
										if(interviewFlag) {
											System.out.println("Interview date successfully entered!");
										}
									} 
									else {
										System.out.println("Could not enter interview date");	
									}
								}
								catch (ParseException | UniversityException e) {
									throw new UniversityException(e.getMessage());
								}
							}	
							else {
									System.out.println("Applicant not found!");
							}
							break;
						case 2:
							Boolean rejectFlag = iMacService.reject(applicantId);
							if(rejectFlag) {
								System.out.println("~~~~~ Applicant rejected successfully ~~~~~");
							} 
							else {
								System.out.println("Applicant not found!");
							}
							break;
					}
				}
				catch (UniversityException e2) {
					System.err.println("Error in fetching program");
				}
				break;
			case 2:
				System.out.println("Enter Application ID for which you want to confirm/reject status: ");
				String applicationIdString = scanner.nextLine();
				Integer applicationId = Integer.parseInt(applicationIdString);
				System.out.println("\n1. Confirm");
				System.out.println("\n2. Reject");
				String choice3 = scanner.nextLine();
				Integer option3 = Integer.parseInt(choice3);
				switch(option3) {
					case 1: 
						iMacService.confirm(applicationId);
						System.out.println("Applicant confirmed successfully!");
						break;
					case 2: 
						iMacService.reject(applicationId);
						System.out.println("Applicant rejected successfully!");
						break;
					default: 
						System.out.println("Enter valid option!");
				}
				break;
			case 3:
				System.exit(1);
				break;
			default:
				System.out.println("Please enter the correct option");
			}
		} while (option != 3);
	}
	
	static void printScheduledPrograms(List<ProgramScheduledBean> programScheduledList) {
		for(ProgramScheduledBean psb : programScheduledList) {
			System.out.println("\nScheduled Program ID: " + psb.getScheduledProgramID()
								+ "\nProgram Name: " + psb.getProgramName()
								+ "\nLocation: " + psb.getLocation()
								+ "\nStart Date: " + psb.getStartDate()
								+ "\nEnd Date: " + psb.getEndDate()
								+ "\nSessions Per Week: " + psb.getSessionsPerWeek());
		}
	}
	
	static void printApplicantDetails(List<ApplicationBean> applicantList) {
		for (ApplicationBean applicationBean : applicantList) {
			System.out.println("\nApplication ID: " + applicationBean.getApplicationID()
								+ "\nFull Name: " + applicationBean.getFullName()
								+ "\nDate of Birth: " + applicationBean.getDateOfBirth()
								+ "\nHighest Qualification: " + applicationBean.getHighestQualification()
								+ "\nMarks: " + applicationBean.getMarksObtained()
								+ "\nGoals: " + applicationBean.getGoals()
								+ "\nEmail ID: " + applicationBean.getEmailID()
								+ "\nScheduled Program ID: " + applicationBean.getScheduledProgramID()
								+ "\nStatus: " + applicationBean.getStatus());

		}
	}
	
}