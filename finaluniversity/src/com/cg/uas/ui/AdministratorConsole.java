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

	static Scanner scanner = new Scanner(System.in);
	public static Logger logger = Logger.getLogger(Main.class);
	public static Connection conn = null;
	public static IAdminService adminService = null;
	public static AdminServiceImpl adminServiceImpl = null;
	
	public static void start() {
		PropertyConfigurator.configure("src//log4j.properties");
		ProgramOfferedBean programOffered = null;
		String choice;
		Integer option;
		
		do {
			System.out.println("--------------Welcome to Capgemini University Administrator Portal--------------");
			System.out.println("1. Add program offered details");
			System.out.println("2. Update program Offered details");
			System.out.println("3. View program offered details");
			System.out.println("4. Delete program offered details");			
			System.out.println("5. Exit");
			System.out.println("------------------------------------------------------------------");
			
			System.out.println("Enter a choice");
			choice = scanner.nextLine();
			option = Integer.parseInt(choice);		
	
			switch (option) {
				case 1:
					while (programOffered == null) {
						programOffered = populateProgramOfferedBean();
					}
					try {
						adminService = new AdminServiceImpl();
						boolean check = adminService.addProgramOffered(programOffered);
						if(check == true) {
							System.out.println("Program details has been added successfully ");
						}
						else {
							System.out.println("Program details insertion failed");
						}
					} 
					catch (UniversityException se) {
						logger.error("exception occured", se);
					} 
					finally {
						adminService = null;
						programOffered  = null;
					}
					break;	
				case 2:	
					adminService = new AdminServiceImpl();
					int opt = 0;
					while(opt != 6) {
						System.out.println("Do you want to continue:(y/n)");
						String flag = scanner.nextLine();
						if(flag.equals("n")) {
							break;
						}
						else {
							System.out.println("Enter the program name you want to update:");
							String programName = scanner.nextLine();
							System.out.println("select a column you want to update");
							System.out.println("1.Program name");
							System.out.println("2.description");
							System.out.println("3.application eligibility");
							System.out.println("4.Duration");			
							System.out.println("5.degree certificate");
							System.out.println("------------------------------------------------------------------");
							System.out.println("Select a column number");
							String column = scanner.nextLine();
							System.out.println("Enter the new value:");
							String newVal = scanner.nextLine();	
							try {
								boolean check = adminService.updateProgramOrffered(programName, column, newVal);
								if(check == true) {
									System.out.println("Details updated successfully!!");
								}
							}
							catch (UniversityException e) {
								System.err.println("Error: " + e.getMessage());
							}
						}	
					}
					break;
				case 3:
					adminService = new AdminServiceImpl();		
					try {
						List<ProgramOfferedBean> programOfferedList= new ArrayList<ProgramOfferedBean>();
						programOfferedList = adminService.viewProgramOffered();	
						if(!programOfferedList.isEmpty()) {
							Iterator<ProgramOfferedBean> iterator = programOfferedList.iterator();
							while(iterator.hasNext()) {
								ProgramOfferedBean programOfferedBean = (ProgramOfferedBean) iterator.next();
								System.out.println(programOfferedBean);
							}
						}
						else {
							System.out.println("No program offered details founded");
						}				
					} 
					catch (UniversityException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 4:
					adminServiceImpl = new AdminServiceImpl();
					System.out.println("Enter program Name :");
					String programName = scanner.nextLine();
					try {
						adminService.delProgramOffered(programName);
						System.out.println("Programs offered details deleted successfully.");
					} 
					catch (UniversityException e) {
						System.err.println("Error: " + e.getMessage());
					}				
					break;
				case 5:
					System.exit(1);
				default:
					System.out.println("Enter the correct option");
					break;
			}
		} while(option != 5);
	}

	private static ProgramOfferedBean populateProgramOfferedBean() {
		ProgramOfferedBean programOffered = new ProgramOfferedBean();
		
		String programName = null;
		String applicantEligibility = null;
		String description = null;
		String duration = null;
		String degreeCertificateOffered = null;

		System.out.println("ENTER DETAILS");

		System.out.println("Enter program name: ");
		programName = scanner.nextLine();

		System.out.println("Enter program description: ");
		description = scanner.nextLine();

		System.out.println("Enter application eligibility: ");
		applicantEligibility = scanner.nextLine();

		System.out.println("Enter duration: ");
		duration = scanner.nextLine();

		System.out.println("Enter degree certificate offered : ");
		degreeCertificateOffered = scanner.nextLine();

		// set the values to bean
		programOffered.setProgramName(programName);
		programOffered.setDescription(description);
		programOffered.setApplicantEligibility(applicantEligibility);
		programOffered.setDuration(duration);
		programOffered.setDegreeCertificateOffered(degreeCertificateOffered);

		return programOffered;
	}
}