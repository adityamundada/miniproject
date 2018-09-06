package com.cg.uas.ui;

import java.util.Scanner;

import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.LoginCred;

public class MainClass {

	private static Scanner sc;

	public static void main(String[] args) {
	
		
		
		int n=0;
		while(n!=3){
			sc = new Scanner(System.in);
			System.out.println("--------------Welcome to Capgemini University Portal--------------");
			System.out.println("1.Apply an application");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Enter a choice");
			 n= sc.nextInt();
			switch(n){
			
			case 1: ApplicantConsole ac=new ApplicantConsole();
					ac.start();
					break;
			case 2:
				System.out.println("Enter Login Id:");
				String loginId=sc.nextLine();
				System.out.println("Enter Password:");
				String password=sc.nextLine();
				LoginCred cred =new LoginCred();
				String role=null;
				try {
					role = cred.isValidCred(loginId, password);
				} catch (UniversityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(role.equals("admin")){
					AdministratorConsole ad = new AdministratorConsole();
					ad.start();
					}
				else if(role.equals("mac"))
				{
					MacConsole mc = new MacConsole();
					mc.start();
				}
				else{
					System.out.println("Invalid Login Id and password");
				}
				
				break;
			case 3:
				System.out.println("Exit University Portal");
				System.exit(0);
			
				break;
			
				default:
					System.out.println("Please enter a correct choice");
			}
		}
		 
		
		

	}

}
