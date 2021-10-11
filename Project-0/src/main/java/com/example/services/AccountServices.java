package com.example.services;

import java.sql.SQLException;

import com.example.dao.AccountDao;
import com.example.exceptions.AccountAlreadyExistsException;
import com.example.exceptions.AccountDoesNotExistException;
import com.example.logging.Logging;
import com.example.models.Account;

public class AccountServices {

	private AccountDao aDao;
	
	public AccountServices(AccountDao aDao) {
		this.aDao = aDao;
	}

	public Account findAccount(String username)
	{
		Account a = null;
		
			a = aDao.getAccountByUsername(username); 
			if(a.getAccountId()==0) {
				Logging.logger.warn("User tried opening account that doesnt exist");
				throw new AccountDoesNotExistException();
			}	
			else {
				Logging.logger.info("Account attatched to user was found");
				return a;
			}
	}
	
	public Account createAccount(String username) {
		
		Account a = new Account(username);
		
		try {
			aDao.createAccount(a);
			Logging.logger.info("New Account was created");
		} catch (SQLException e) {
			e.printStackTrace();
			Logging.logger.warn("Account created that already exists in the database");
			throw new AccountAlreadyExistsException();
		}
		
		return a;
	}
	
	
	public void withdraw(Account a, double withdraw) throws SQLException
	{
		double newBal = (a.getBalance()-withdraw);
		
		Account a2 = new Account(a.getAccountId(), newBal, a.getUsername());
		
		aDao.updateAccount(a2);
		Logging.logger.info("Withdrew from account successfully");		
	}
	
	public void deposit(Account a, double deposit) throws SQLException
	{
		double newBal = (a.getBalance()+deposit);
		
		Account a2 = new Account(a.getAccountId(), newBal, a.getUsername());
		
		aDao.updateAccount(a2);
		Logging.logger.info("Deposited into account successfully");		
	}
	/*
	public void transfer(Account a, String username, double transfer)
	{
		//find other persons account, check if exists. use username
		//update that account with the new balance
		//return account? idk
		
		Account transferA = aDao.getAccountByUsername(username);
		double newBal = (transferA.getBalance()+transfer);
		
		Account newAccount = Account(transferA.getAccountId(), newBal, transferA.getUsername());
		
		aDao.updateAccount(newAccount);
		
	}
	
	public void delete(Account a)
	{
		try {
		aDao.deleteAccount(a);
		Logging.logger.info("Account deleted");
		} catch(AccountDoesNotExistException e) {
			e.printStackTrace();
			Logging.logger.warn("Account deleted that didn't exist");

		}
		
		
	}
	*/
	
}
