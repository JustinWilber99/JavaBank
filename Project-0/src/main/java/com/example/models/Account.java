package com.example.models;

public class Account  {
	
	private int accountId;
	private double balance;
	private String username;
	
	public Account()
	{
		
	}
	
	public Account(int accountId, double balance, String username) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.username = username;
	}
	
	public Account(String username)
	{
		super();
		this.username = username;
		this.balance = 0.0;
	}
	
	public Account(double balance, String username) {
		super();
		this.balance = balance;
		this.username = username;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", username=" + username + "]";
	}
		
	
	
}
