package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Account;
import com.example.utils.ConnectionUtil;
import com.example.logging.Logging;

public class AccountDaoDb implements AccountDao {

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	
	public AccountDaoDb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Account> getAllAcounts() {
     List<Account> accountList = new ArrayList<Account>();
     
     // Upper code works but not for seeing all of the accounts bc return types 
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			
			//Create a select statement
			String sql = "SELECT * FROM accounts";
			
			//Create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				//accountList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3)));
				Account account = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3));
				accountList.add(account);
			}
						
			con.setAutoCommit(true);
			return accountList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	@Override
	public Account getAccountByUsername(String username) {

		Account account = new Account();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accounts WHERE accounts.username = '" + username + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				account.setAccountId(rs.getInt(1));
				account.setBalance(rs.getInt(2));
				account.setUsername(rs.getString(3));
			}
			
			return account;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void createAccount(Account a) throws SQLException {
		try {
			Connection con = conUtil.getConnection();
			
			//We first need to set autocommit to false
			con.setAutoCommit(false);
			String sql = "call create_account(?,?)";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.setInt(1, a.getBalance());
			cs.setString(2, a.getUsername());
			
			cs.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
/*
	@Override
	public void updateAccount(Account a, Account a2) {
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = balance + ?, username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, a.getBalance());
			ps.setString(2, a.getUsername());
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
*/

	@Override
	public void deleteAccount(Account a) {

		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM accounts WHERE accounts.username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a.getUsername());
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateAccount(Account a) {
		try {
			Connection con = conUtil.getConnection();		 // commented out below bc key username already exists 
			String sql = "UPDATE accounts SET balance = balance - ?"; //, username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, a.getBalance());
			//ps.setString(2, a.getUsername());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return "AccountDaoDb [getAllAcounts()=" + getAllAcounts() + "]";
	}

	@Override
	public void updateAccount(Account a, Account a2) {
		// TODO Auto-generated method stub
		
	}


}
