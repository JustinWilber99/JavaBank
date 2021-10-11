package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.example.dao.AccountDao;
import com.example.dao.AccountDaoDb;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.Account;
import com.example.models.User;
import com.example.services.AccountServices;
import com.example.services.UserService;

public class BankDriver {

	private static UserDao uDao = new UserDaoDB();
	private static AccountDao aDao = new AccountDaoDb();
	private static UserService uServ = new UserService(uDao);
	private static AccountServices aServ = new AccountServices(aDao);
	
	public BankDriver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UserDoesNotExistException, InvalidCredentialsException {

		//System.out.println(uDao.getAllUsers());
		
		//System.out.println(aDao.getAllAcounts());

	Scanner in = new Scanner(System.in);
		
		//This will be used to control our loop
		boolean done = false;
		
		// Objects being used
		User u = null;
		Account a = null;
		
		
		String userType = null;
		
		while(!done) {
			
			if(u == null) {
				System.out.println("Login or Signup? Press 1 to login, press 2 to signup");
				int choice = Integer.parseInt(in.nextLine());
				if(choice == 1) {
					System.out.print("Please enter your username: ");
					String username = in.nextLine();
					System.out.print("Please enter you password: ");
					String password = in.nextLine();
					
						u = uServ.signIn(username, password);
						
						//setUsername(username);
						//a = aServ.findAccount(u.getUsername());									
						
						System.out.println("-------------------------");
						System.out.println("Welcome " + u.getUsername());
						System.out.println("-------------------------");
						
										
					// add logic here
					// Welcomed user - now need to ask for user input for the next step
					 // depending on what they would like to do. Can branch off here for
					 // user, employee, or bank admin
					 System.out.println("What would you like to do next?");
					 System.out.println("");
					 System.out.println("Enter 1 to withdraw from your account");
					 System.out.println("Enter 2 to deposit into your account");
					 System.out.println("Enter 3 to transfer between accounts");
					 System.out.println("Enter 4 if you're an employee");
					 System.out.println("Enter 5 if you're the bank admin");
					 
					 int choice2 = Integer.parseInt(in.nextLine());
					 
					 if (choice2 == 1) {
						 //do logic for withdraw
						 
						 u = uServ.signIn(username, password);
						 a = aServ.findAccount(u.getUsername());
						 
						 System.out.println("Time to withdraw!");
						 System.out.println("-------------------------");
						 System.out.println("Your amount before withdrawl is: " + a.getBalance());
						 System.out.println("-------------------------");
						 System.out.println("Enter the amount you would like to withdaw: ");
						 
						 
						 
						 double withdrawAmount = Double.parseDouble(in.nextLine());
						 
						 
						 //Then try to withdraw the withdrawAmount from account a 
						 try {
							aServ.withdraw(a, withdrawAmount);  // PROBLEM LINE -- MUST IMPLEMENT THE INHERITED ABSTRACT METHOD ACCOUNTDAO.UPDATEACCOUNT(ACCOUNT)
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 System.out.println("$" + withdrawAmount + " was withdrawn.");
						 //System.out.println("Your amount before withdrawl is: " + a.getBalance());
						 // Line below updates local variable instead of updating DB - Can't figure out why
						 // So I changed it for now to not get stuck..
						 System.out.println("Your amount after withdrawl is: " + (a.getBalance() - withdrawAmount));
						 					 
						 
						 
					 } else if (choice2 == 2) {
						 //do logic for deposit
						 
						 u = uServ.signIn(username, password);
						 a = aServ.findAccount(u.getUsername());
						 
						 System.out.println("Time to deposit!");
						 System.out.println("-------------------------");
						 System.out.println("Your amount before depositing is: " + a.getBalance());
						 System.out.println("-------------------------");
						 System.out.println("Enter the amount you would like to deposit: ");
						 
						 double depositAmount = Double.parseDouble(in.nextLine());
						 
						 
						 //Then try to withdraw the withdrawAmount from account a 
						 try {
							aServ.withdraw(a, depositAmount);  // PROBLEM LINE -- MUST IMPLEMENT THE INHERITED ABSTRACT METHOD ACCOUNTDAO.UPDATEACCOUNT(ACCOUNT)
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
						 System.out.println("-------------------------");
						 System.out.println("Your amount after depositing is: " + (a.getBalance() + depositAmount));
						 
						 
					 } else if (choice2 == 3) {
						 //do logic for transfer
						 System.out.println("Time to transfer!");
						 
						 
					 } else if (choice2 == 4) {
						 //do logic for employee
						 System.out.println("Welcome Employee! What would you like to do?");
						 
						 
					 } else if (choice2 == 5) {
						 //do logic for bankAdmin
						 System.out.println("Welcome Bank Admin! What would you like to do?");
						 
						 
					 }
					 
					 //This should execute once I finish the choice 2 if/elses
					 System.out.println("-------------------------");
					 System.out.println("-------------------------");
					 System.out.println("-------------------------");
					
					
					
				} else {
					System.out.print("Please enter your first name: ");
					String first = in.nextLine();
					System.out.print("Please enter your last name: ");
					String last = in.nextLine();
					System.out.print("Please enter your email: ");
					String email = in.nextLine();
					System.out.print("Please enter your username: ");
					String username = in.nextLine();
					System.out.print("Please enter your password: ");
					String password = in.nextLine();
					System.out.print("Are you customer, Employee, or Admin: ");
					userType = in.nextLine();
					
					try {
						u = uServ.signUp(first, last, email, username, password);
						System.out.println("-------------------------");
						System.out.println("You may now sign in with the username: " + u.getUsername());
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("Sorry the username is already taken");
						System.out.println("Please try signing up with a different one");
					}
				} 
				
			}
			
		}
		
	}

}