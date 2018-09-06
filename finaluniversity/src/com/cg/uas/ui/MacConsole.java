package com.cg.uas.ui;

import java.util.Scanner;

public class MacConsole {
	private static Scanner scanner;
	
	void start() {
		String choice;
		Integer option;
		do {
			scanner = new Scanner(System.in);
			
			System.out.println("--------------Welcome to Capgemini University MAC Portal--------------");
			System.out.println("1. Scheduled programs details");
			System.out.println("2. Update applicant status");			
			System.out.println("3. Logout");
			System.out.println("------------------------------------------------------------------");
			System.out.println("\nEnter your choice");
			
			choice = scanner.nextLine();	
			option = Integer.parseInt(choice);
			
			switch(option) {
				case 1: 
					break;
				case 2:
					break;
				case 3: 
					System.exit(1);
					break;
				default:
					System.out.println("Please enter the correct option");
			}
		} while(option != 3);
	}
}