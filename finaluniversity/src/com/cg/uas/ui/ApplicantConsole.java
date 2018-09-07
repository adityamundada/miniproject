package com.cg.uas.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IApplicantService;
import com.cg.uas.service.ApplicantServiceImpl;

public class ApplicantConsole {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void start() {
		String choice;
		Integer option;
		
		ApplicationBean applicationBean = new ApplicationBean();
		IApplicantService iApplicantService = new ApplicantServiceImpl();
	
		do {
			System.out.println("--------------Welcome to Applicant Portal--------------");
			System.out.println("1. View all programs scheduled by the university");
			System.out.println("2. Apply online for a scheduled program ");
			System.out.println("3. View the application status");
			System.out.println("4. Exit");
			System.out.println("------------------------------------------------------------------");
			
			System.out.println("Enter a choice");
			choice = scanner.nextLine();
			option = Integer.parseInt(choice);
			
			switch(option) {
				case 1:
					List <ProgramScheduledBean> list = new ArrayList<>();
					try {
						list = iApplicantService.getAllScheduleProgram();
					} 
					catch (UniversityException e2) {
						System.err.println("Error in fetching program");
					}
					break;
				case 2:
					applicationBean = populateApplicationBean();
					
					Boolean flag = false;
					
					try {
						flag = iApplicantService.isValidApplication(applicationBean);
					} 
					catch (UniversityException e1) {
						System.err.println("Invalid data");
					}
					if(flag = true) {
						try {
							String applicationID = iApplicantService.addApplicantDetails(applicationBean);
							System.out.println("Successfully Registered with application Id is: " + applicationID);
						}
						catch (UniversityException e) {	
							System.err.println("Error in fetching");
						}
					}
					else{
						System.out.println("Invalid data");
					}
					break;
				case 3:
					System.out.println("Enter your application Id:");
					String applicationIdString = scanner.nextLine();
					int applicationId = Integer.parseInt(applicationIdString);
					
					String status = null;
					
					try {
						status = iApplicantService.viewApplicationStatus(applicationId);
					} 
					catch (UniversityException e) {
						System.out.println("Error in retriving status");
					}
					System.out.println("Applicatus status of id " + applicationId + " is " + status);
					break;
				case 4:
					System.exit(0);
				default: 
					System.out.println("Please enter a correct choice");
					break;
			}
		} while(option != 4);
	}
	
	private static ApplicationBean populateApplicationBean() {
		ApplicationBean applicationBean = new ApplicationBean();
		
		String applicationID = null;
		String name = null;
		String dob = null;
		String highestQualification = null;
		String marksObtained = null;
		String goals = null;
		String emailID = null;
		String scheduledProgramID = null; 
		Date doi = null;
	
		System.out.println("Enter your full name:");
		name = scanner.nextLine();
	
		System.out.println("Enter your date of birth(dd/mm/yyyy):");
		dob = scanner.nextLine();
	
		System.out.println("Enter your highest qualification:");
		highestQualification = scanner.nextLine();
	
		System.out.println("Enter marks obtained:");
		marksObtained = scanner.nextLine();
	
		System.out.println("Enter scheduled program Id");
		scheduledProgramID = scanner.nextLine();
	
		System.out.println("Enter your goals:");
		goals = scanner.nextLine();
	
		System.out.println("Enter your Emailid:");
		emailID = scanner.nextLine();
	
		applicationBean.setName(name);
		applicationBean.setDob(dob);
		applicationBean.setEmailID(emailID);
		applicationBean.setGoals(goals);
		applicationBean.setHighestQualification(highestQualification);
		applicationBean.setMarksObtained(marksObtained);
		applicationBean.setScheduledProgramID(scheduledProgramID);
		applicationBean.setDoi(doi);
		applicationBean.setStatus("Applied");
		
		return applicationBean;
	}
}