package com.revature;

import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


		Scanner in = new Scanner(System.in);
		
		//This will be used to control our loop
		boolean done = false;
		boolean done2 = false;
		String userType = null;
		
		// Objects being used
		User u = null;
		Account a = null;	
		
		//do {
			
			if(u == null) {
				System.out.println("-------------------------");
				System.out.println("Welcome to JavaBank!");
				System.out.println("-------------------------");
				System.out.println("Login or Signup? Press 1 to login, press 2 to signup");
				int choice = Integer.parseInt(in.nextLine());
				
				if (choice < 1 && choice > 2) {
					System.out.println("Invalid input... 1 or 2 only.");
					System.out.println("-------------------------");
				
				}else if(choice == 1) {
						System.out.print("Please enter your username: ");
						String username = in.nextLine();
						System.out.print("Please enter your password: ");
						String password = in.nextLine();
					
						u = uServ.signIn(username, password);								
						
						System.out.println("-------------------------");
						System.out.println("Welcome " + u.getUsername());
						System.out.println("-------------------------");
						
						
						System.out.println("What would you like to do next?");
						System.out.println("");
						System.out.println("1: Withdraw from your account");
						System.out.println("2: Deposit into your account");
						System.out.println("3: Transfer between accounts");
						System.out.println("4: Employees");
						System.out.println("5: Bank Admins");
						System.out.println("");
						System.out.println("Enter your choice: ");
					 
						int choice2 = Integer.parseInt(in.nextLine());
					 
						if (choice2 == 1) {
						   //do logic for withdraw
						   a = aDao.getAccountByUsername(username);		 
						 
						   System.out.println("Time to withdraw!");
						   System.out.println("-------------------------");
						   System.out.println("Your amount before withdrawl is: $" + a.getBalance() + ".00");
						   System.out.println("-------------------------");
						   System.out.println("Enter the amount you would like to withdaw: "); 

						   int withdrawAmount = Integer.parseInt(in.nextLine());

						   //Then try to withdraw the withdrawAmount from account a 
						   try {
							  aServ.withdraw(a, withdrawAmount);  // PROBLEM LINE -- MUST IMPLEMENT THE INHERITED ABSTRACT METHOD ACCOUNTDAO.UPDATEACCOUNT(ACCOUNT)
						  } catch (SQLException e) {
							  e.printStackTrace();
						  }
						   System.out.println("$" + withdrawAmount + " was withdrawn.");						 
						   //Update the database itself
						   aDao.updateAccountWithdraw(a);
						 
						   System.out.println("Your amount after withdrawl is: $" + (a.getBalance() - withdrawAmount) + ".00");
		 
					 } else if (choice2 == 2) {
						 //do logic for deposit

						 a = aDao.getAccountByUsername(username);
						 
						 System.out.println("Time to deposit!");
						 System.out.println("-------------------------");
						 System.out.println("Your amount before depositing is: $" + a.getBalance() + ".00");
						 System.out.println("-------------------------");
						 System.out.println("Enter the amount you would like to deposit: ");
						 
						 int depositAmount = Integer.parseInt(in.nextLine());
						 
						 //Then try to deposit the depositAmount from account a 
						 try {
							aServ.deposit(a, depositAmount);
						} catch (SQLException e) {
							e.printStackTrace();
						}					 
						 //Update the database itself
						 aDao.updateAccount(a);
						 
						 System.out.println("-------------------------");
						 System.out.println("Your amount after depositing is: $" + (a.getBalance() + depositAmount) + ".00");
						 
						 
					 } else if (choice2 == 3) {
						 //do logic for transfer
						 a = aDao.getAccountByUsername(username);
						 
						 System.out.println("Time to transfer!");
						 System.out.println("-------------------------");
						 System.out.println("Enter the username of the account to transfer to");
						 String usernameTo = in.nextLine();

						 System.out.println("Enter the amount you would like to transfer: ");
						 
						 int transferAmount = Integer.parseInt(in.nextLine());
						 aServ.transfer(a, usernameTo, transferAmount);
						 
						 //Update the database itself
						 aDao.updateAccountWithdraw(a);
						 
						 System.out.println("-------------------------");
						 System.out.println("Your amount after transfering is: $" + (a.getBalance() - transferAmount) + ".00");
						 System.out.println("pbeesly's account now has $" + transferAmount);
					 } else if (choice2 == 4) {
						 
						 {
						 
						 //do logic for employee
						 System.out.println("Welcome Employee! What would you like to do?");
						 
						 System.out.println("1: Withdraw  2: Deposit  3: Transfer  4: View Customer's Information");
						 System.out.println("5: Approve/Deny Applications ");
						 System.out.println("Enter your choice: ");
						 
						 int choice4 = Integer.parseInt(in.nextLine());
						 switch (choice4) {
						 case 1:
						 {		// Copy and pasted from above
							 a = aDao.getAccountByUsername(username);
														 
							 System.out.println("Time to withdraw!");
							 System.out.println("-------------------------");
							 System.out.println("Your amount before withdrawl is: $" + a.getBalance() + ".00");
							 System.out.println("-------------------------");
							 System.out.println("Enter the amount you would like to withdaw: ");
							 
							 int withdrawAmount = Integer.parseInt(in.nextLine());

							 //Then try to withdraw the withdrawAmount from account a 
							 try {
								aServ.withdraw(a, withdrawAmount); 
							} catch (SQLException e) {
								e.printStackTrace();
							}
							 
							 //Update the database itself
							 aDao.updateAccountWithdraw(a);
							 
							 System.out.println("$" + withdrawAmount + " was withdrawn.");
							 System.out.println("Your amount after withdrawl is: $" + (a.getBalance() - withdrawAmount) + ".00");
							 
							break; 
						 }
						 
						 case 2:
						 {		
							 a = aDao.getAccountByUsername(username);
							 
							 System.out.println("Time to deposit!");
							 System.out.println("-------------------------");
							 System.out.println("Your amount before depositing is: $" + a.getBalance() + ".00");
							 System.out.println("-------------------------");
							 System.out.println("Enter the amount you would like to deposit: ");
							 
							 int depositAmount = Integer.parseInt(in.nextLine());							 
							 
							 //Then try to deposit the depositAmount from account a 
							 try {
								 aServ.deposit(a, depositAmount);
							} catch (SQLException e) {
								e.printStackTrace();
							}							 
						     //Update the database itself
							 aDao.updateAccount(a);
							 System.out.println("-------------------------");
							 System.out.println("Your amount after depositing is: $" + (a.getBalance() + depositAmount) + ".00");
							 
							 break;
							
						 }
						 
						 case 3:
						 {		
							 a = aDao.getAccountByUsername(username);
							 
							 System.out.println("Time to transfer!");
							 System.out.println("-------------------------");
							 System.out.println("Enter the username of the account to transfer to");
							 String usernameTo = in.nextLine();

							 System.out.println("Enter the amount you would like to transfer: ");
							 
							 int transferAmount = Integer.parseInt(in.nextLine());
							 aServ.transfer(a, usernameTo, transferAmount);
							 
							 //Update the database itself
							 aDao.updateAccountWithdraw(a);
							 System.out.println("-------------------------");
							 System.out.println("Your amount after transfering is: $" + (a.getBalance() - transferAmount) + ".00");

							 break;
						 }
						 
						 case 4:
						 {	
							 System.out.println("Revealing all customers' information");
							 System.out.println("-------------------------");
							 
							 List<Account> accountList = new ArrayList<Account>();
							 
							 accountList =  aDao.getAllAcounts();
							 
							 System.out.println("There are " + accountList.size() + " Customers");
							 System.out.println("-------------------------");
							 
							 // We don't need the loop since the line of code below
							 // print's out the contents with the toString()
							 System.out.println(Arrays.toString(accountList.toArray()));
								 								 
							 break;
						 }
						 
						 case 5:
						 {
							 System.out.println("-------------------------");
							 System.out.println("Time to Approve / Deny accounts!");
							 						 							 
							 break;
						 }
						 default:
							 System.out.println("Enter 1-5 only.");
							 break;
						 	}
						 }
						 
					 } else if (choice2 == 5) {
						 //do logic for bankAdmin
						 System.out.println("Welcome Bank Admin! What would you like to do?");
						 // Write switch statement - can be used for bankAdmin below too
						 System.out.println("1: Withdraw  2: Deposit  3: Transfer  4: View Customer's Information");
						 System.out.println("5: Approve/Deny Applications  6: Cancel Accounts");
						 System.out.println("Enter your choice: ");
						 
						 int choice4 = Integer.parseInt(in.nextLine());
						 
						 switch (choice4) {
						 case 1:
						 {		// Copy and pasted from above
							 a = aDao.getAccountByUsername(username);
							 							 
							 System.out.println("Time to withdraw!");
							 System.out.println("-------------------------");
							 System.out.println("Your amount before withdrawing is: $" + a.getBalance() + ".00");
							 System.out.println("-------------------------");
							 System.out.println("Enter the amount you would like to withdaw: ");
							 
							 int withdrawAmount = Integer.parseInt(in.nextLine());

							 //Then try to withdraw the withdrawAmount from account a 
							 try {
								aServ.withdraw(a, withdrawAmount);  
							} catch (SQLException e) {
								e.printStackTrace();
							}
							 
							//Update the database itself
							 aDao.updateAccountWithdraw(a);
							 System.out.println("$" + withdrawAmount + " was withdrawn.");
							 System.out.println("Your amount after withdrawing is: $" + (a.getBalance() - withdrawAmount) + ".00");
							 
							break; 
						 }
						 
						 case 2:
						 {		// Copy and pasted from above
							 a = aDao.getAccountByUsername(username);
							 
							 System.out.println("Time to deposit!");
							 System.out.println("-------------------------");
							 System.out.println("Your amount before depositing is: $" + a.getBalance() + ".00");
							 System.out.println("-------------------------");
							 System.out.println("Enter the amount you would like to deposit: ");
							 
							 int depositAmount = Integer.parseInt(in.nextLine());
							 							 
							 //Then try to withdraw the withdrawAmount from account a 
							 try {
								 aServ.deposit(a, depositAmount);
							} catch (SQLException e) {
								e.printStackTrace();
							}							 
							 //Update the database itself
							 aDao.updateAccount(a);
							 System.out.println("-------------------------");
							 System.out.println("Your amount after depositing is: $" + (a.getBalance() + depositAmount) + ".00");
							
							 break;
							
						 }
						 
						 case 3:
						 {		// Copy and pasted from above
							 a = aDao.getAccountByUsername(username);
							 
							 System.out.println("Time to transfer!");
							 System.out.println("-------------------------");
							 System.out.println("Enter the username of the account to transfer to");
							 String usernameTo = in.nextLine();

							 System.out.println("Enter the amount you would like to transfer: ");
							 
							 int transferAmount = Integer.parseInt(in.nextLine());
							 aServ.transfer(a, usernameTo, transferAmount);
							 
							 System.out.println("-------------------------");
							 System.out.println("Your amount after transfering is: $" + (a.getBalance() - transferAmount) + ".00");

							 break;
						 }
						 
						 case 4:
						 {		
							 System.out.println("Revealing the customer's information");
							 System.out.println("-------------------------");
							 
							 List<Account> accountList = new ArrayList<Account>();
							 
							 accountList =  aDao.getAllAcounts();
							 
							 System.out.println("There are " + accountList.size() + " Customers");
							 System.out.println("-------------------------");
							 
						     System.out.println(Arrays.toString(accountList.toArray()));								 
								 
							 break;
						 }
						 
						 case 5:
						 {
							 System.out.println("-------------------------");
							 System.out.println("Time to Approve / Deny accounts!");
							 // Do approve / deny accounts logic here														 
							 break;
						 
						 }
						 
						 case 6:
						 {
							 //boolean finished = false;
							 List<Account> accountList = new ArrayList<Account>();
							 accountList = aDao.getAllAcounts();
							
							 System.out.println("-------------------------");
							 System.out.println("Time to cancel accounts!");							
							 System.out.println("There are " + accountList.size() + " accounts");
							 System.out.println("-------------------------");
							 System.out.println(Arrays.toString(accountList.toArray()));	
							 
							 System.out.println("Enter the username of the account you want to cancel: ");
							 
							 String usernameToCancel = in.nextLine();
							 
							 a = aDao.getAccountByUsername(usernameToCancel);
								 
								 // Perform the deletion on account a
							 aDao.deleteAccount(a);
							 
							 System.out.println("After cancelling " + usernameToCancel + " there are " + (accountList.size() - 1) + " accountss");
								 						 
							 }
							 
							 break;
						 }
					 } 
					 //This should execute once I finish the choice 2 if/else
					 System.out.println("-------------------------");
					// set done2 to true for the loop
					 done2 = true;
					 
					}
						else if (choice == 2) {
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
						a = aServ.createAccount(username);
						
						System.out.println("-------------------------");
						System.out.println("You may now sign in with the username: " + u.getUsername());
						System.out.println("-------------------------");
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("Sorry the username is already taken");
						System.out.println("Please try signing up with a different one");
					}
				} 
			 // set done2 to true for the loop
			 done2 = true;
			} //while (!done); // while loop ends on this block
			
		    //Out of main loop - Thank and exit
			System.out.println("-------------------------");
			System.out.println("Signed out...");
			System.out.println("-------------------------");
			System.out.println("Thanks for using JavaBank! We appreciate your business!");
			System.out.println("Only one transaction at a time is allowed.");
			System.out.println("Log in if you wish to make another transaction.");
			System.exit(0);
	
		}
	}
