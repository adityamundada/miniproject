package com.cg.uas.ui;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.ProgramOfferedBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.AdminServiceImpl;
import com.cg.uas.service.IAdminService;

public class AdministratorConsole {
	

	static Scanner sc=new Scanner(System.in);
	public static Logger logger=Logger.getLogger(Main.class);
	public static Connection conn=null;
	public static IAdminService adminService=null;
	public static AdminServiceImpl adminServiceImpl=null;
	
 public void static start(){

PropertyConfigurator.configure("src//log4j.properties");

ProgramOfferedBean programOffered=null;


int option =0;
while(option!=5){
	System.out.println("--------------Welcome to Capgemini University Administrator Portal--------------");
	System.out.println("1.Add program offered details");
	System.out.println("2.Update program Offered details");
	System.out.println("3.View program offered details");
	System.out.println("4.Delete program offered details");			
	System.out.println("5.Exit");
	System.out.println("------------------------------------------------------------------");
	System.out.println("Enter a choice");
	option=sc.nextInt();		
	
	switch (option) {
	case 1:
		while (programOffered == null) {
			programOffered = populateProgramOfferedBean();
		}

		try {
			adminService = new AdminServiceImpl();
			boolean check = adminService.addProgramOffered(programOffered);

			if(check==true){
				System.out.println("Program details  has been added successfully ");
			}
			else{
				System.out.println("Program details insertion failed");
			}
			
	

		} catch (UniversityException se) {
			logger.error("exception occured", se);
			;
		} finally {
			//rollNum = null;
			adminService = null;
			programOffered  = null;
		}
		break;
	
		
	case 2:	
		adminService=new AdminServiceImpl();
		int opt=0;
		
		while(opt!=6){
			System.out.println("Do you want to continue:(y/n)");
			String flag=sc.next();
			if(flag.equals("n")){
				break;
				
			}else{

				System.out.println("Enter the program name you want to update:");
				String programName=sc.next();
				
				System.out.println("select a column you want to update");
				System.out.println("1.Program name");
				System.out.println("2.description");
				System.out.println("3.application eligibility");
				System.out.println("4.Duration");			
				System.out.println("5.degree certificate");
				//System.out.println("6.exit");
				System.out.println("------------------------------------------------------------------");
				System.out.println("Select a column number");
				String column=sc.next();
				System.out.println("Enter the new value:");
				String newVal=sc.next();
			
				try{
					boolean check=adminService.updateProgramOrffered(programName,column,newVal);
					if(check==true){
						System.out.println("Details updated successfully!!");
					}
					
					
				}catch(UniversityException e){
					System.err.println("Error: "+e.getMessage());
				}
					
			}
			
			
			
			
		}
		
						
		//System.out.println("update programs section under development.");
		
		
		break;
		
	case 3:
		//System.out.println("viewing programs section under development.Please visit later");
		adminService = new AdminServiceImpl();
		
		try {
			List<ProgramOfferedBean> programOfferedList= new ArrayList<ProgramOfferedBean>();
			programOfferedList= adminService.viewProgramOffered();
			
			if(!programOfferedList.isEmpty()){
				Iterator<ProgramOfferedBean> it=programOfferedList.iterator();
				while (it.hasNext()) {
					ProgramOfferedBean programOfferedBean = (ProgramOfferedBean) it.next();
					System.out.println(programOfferedBean);
					
				}
			}else{
				System.out.println("No program offered details founded");
			}				
			
		} catch (UniversityException e) {
			System.err.println(e.getMessage());
		}
		
		
		
		
		
		break;
	case 4:
		
		
		adminServiceImpl=new AdminServiceImpl();

		System.out.println("Enter program Name :");
		String programName = sc.next();

		/*while (true) {
			if (universityServiceImpl.isValidMobileID(mobileID)) {
				break;
			} else {
				System.err.println("Please enter valid mobile id only, try again");
				rollNum = sc.next();
			}
			
			
		}
*/				try {
			adminService.delProgramOffered(programName);
			System.out.println("Programs offered details deleted successfully.");

			
		} catch (UniversityException e) {
			
			//e.printStackTrace();
			System.err.println("Error: "+e.getMessage());
		}
		
									
		//System.out.println("deleting programs section under development.Please visit later");
		break;
		
	case 5:
		
		System.out.println("Exiting application");
		System.exit(0);
		break;
	default:
		break;
	}
	
	
}



}
	}


private static ProgramOfferedBean populateProgramOfferedBean() {

	

String programName=null;
String applicantEligibility=null;
String description=null;
String duration=null;
String degreeCertificateOffered=null;


ProgramOfferedBean programOffered = null;

System.out.println("Enter Details");


//sc.nextLine();
System.out.println("Enter program name: ");
programName = sc.next();

//sc.nextLine();

System.out.println("Enter program description: ");
description = sc.next();

System.out.println("Enter application eligibility: ");
applicantEligibility = sc.next();

System.out.println("Enter duration: ");
duration = sc.next();

System.out.println("Enter degree certificate offered : ");
degreeCertificateOffered = sc.next();


// set the values to bean
programOffered = new ProgramOfferedBean();
programOffered.setProgramName(programName);

programOffered.setDescription(description);

programOffered.setApplicantEligibility(applicantEligibility);

programOffered.setDuration(duration);

programOffered.setDegreeCertificateOffered(degreeCertificateOffered);


return programOffered;


}


}
