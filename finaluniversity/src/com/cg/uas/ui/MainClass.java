package com.cg.uas.ui;

import java.util.Scanner;

import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.LoginCred;

public class MainClass {

	private static Scanner scanner;

	public static void main(String[] args) {
	
		String choice;
		Integer option;
		
		do {
			scanner = new Scanner(System.in);
			System.out.println("--------------Welcome to Capgemini University Portal--------------");
			System.out.println("1. Apply for an application");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("------------------------------------------------------------------");
			
			System.out.println("Enter a choice");
			choice = scanner.nextLine();
			option = Integer.parseInt(choice);
			
			switch(option) {
				case 1: 
					ApplicantConsole.start();
					break;
				case 2:
					System.out.println("Enter login ID: ");
					String loginId = scanner.nextLine();
					System.out.println("Enter password: ");
					String password = scanner.nextLine();
					LoginCred cred = new LoginCred();
					String role = null;
					
					try {
						role = cred.isValidCred(loginId, password);
					} 
					catch (UniversityException e) {
						System.err.println(e.getMessage());
					}
					
					if(role.equals("admin")) {
						AdministratorConsole.start();
					}
					else if(role.equals("mac")) {
						try {
							MacConsole.start();
						} 
						catch (UniversityException e) {	
							System.err.println(e.getMessage());
						}
					}
					else {
						System.out.println("Invalid Login Id and password");
					}
					break;
				case 3:
					System.exit(1);
				default:
					System.out.println("Please enter a correct choice");
			}
		} while(option != 3);
	}
}