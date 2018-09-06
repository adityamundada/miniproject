package com.cg.uas.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.uas.dto.ApplicationBean;
import com.cg.uas.dto.ProgramScheduledBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IApplicantServ;
import com.cg.uas.service.ApplicantServImp;

public class ApplicantConsole {
	void start(){
		
		String n= "";
		Scanner sc = new Scanner(System.in);
		
	ApplicationBean applicationBean= new ApplicationBean();
	IApplicantServ ser=new ApplicantServImp();
	
		while(!n.equals("3"))
			
		{
			System.out.println("--------------Welcome to Applicant Portal--------------");
			System.out.println("1.View all programs scheduled by the university");
			System.out.println("2.Apply online for a scheduled program ");
			System.out.println("3.View the application status");
			System.out.println("4.Exit");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Enter a choice");
			 n= sc.nextLine();
			switch(n)
			{
			case "1":
				List <ProgramScheduledBean> list= new ArrayList<>();
				try {
					list=ser.getAllScheduleProgram();
				} catch (UniversityException e2) {
					// TODO Auto-generated catch block
					System.err.println("Error in fetching program");
				}
				break;
			case "2":
				 String applicationID=null;
				 String  name=null;
				 String dob=null;
			 String highestQualification=null;
				 String marksObtained=null;
				 String goals=null;
				 String emailID=null;
				 String scheduledProgramID=null; 
			Date doi= null;
				System.out.println("Enter your full name:");
				name=sc.nextLine();
				System.out.println("Enter your date of birth(dd/mm/yyyy):");
				dob=sc.nextLine();
				System.out.println("Enter your highest qualification:");
				highestQualification=sc.nextLine();
				System.out.println("Enter marks obtained:");
				marksObtained=sc.nextLine();
				System.out.println("Enter scheduled program Id");
				scheduledProgramID=sc.nextLine();
				System.out.println("Enter your goals:");
				goals=sc.nextLine();
				System.out.println("Enter your Emailid:");
				emailID=sc.nextLine();
				applicationBean.setName(name);
				applicationBean.setDob(dob);
				applicationBean.setEmailID(emailID);
				applicationBean.setGoals(goals);
				applicationBean.setHighestQualification(highestQualification);
				applicationBean.setMarksObtained(marksObtained);
				applicationBean.setScheduledProgramID(scheduledProgramID);
				applicationBean.setDoi(doi);
				applicationBean.setStatus("Awaited");
				Boolean flag=false;
				try {
					flag=ser.isValidApplication(applicationBean);
				} catch (UniversityException e1) {
					// TODO Auto-generated catch block
					System.err.println("Invalid data");
				}
				if(flag=true){
				 try {
					applicationID=ser.addApplicantDetails(applicationBean);
					 System.out.println("Successfully Registered with application Id is: "+applicationID);
				} catch (UniversityException e) {
					// TODO Auto-generated catch block
					System.err.println("Error in fetching");
				}}
				else{
					System.out.println("Invalid data");
				}
				
				break;
			case "3":
				System.out.println("Enter your application Id:");
				int applicationId=sc.nextInt();
				String status=null;
				try {
					status=ser.viewApplicationStatus(applicationId);
				} catch (UniversityException e) {
					// TODO Auto-generated catch block
					System.out.println("Error in retriving status");
				}
				System.out.println("Applicatus status of id "+applicationId+" is "+status);
				break;
			case "4":System.out.println("Exit University Portal");
			System.exit(0);
				break;
				default: System.out.println("Please enter a correct choice");
					break;
			}
		}
	}

}
