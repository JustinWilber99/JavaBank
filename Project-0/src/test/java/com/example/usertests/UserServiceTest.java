package com.example.usertests;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.User;
import com.example.services.UserService;

public class UserServiceTest {
	
	//We want to be able to mock the UserService and UserDao, so we don't actually hit the db
	//Inject Mocks because we are going to inject the mocked uDao functionality into user service
	@InjectMocks
	public UserService uServ;
	
	//Mock because we are going to mock the functionality of the user dao so we dont hit the database when testing
	@Mock
	public UserDao uDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidLogin() {
		User u1 = new User(1, "test", "user", "test@mail.com", "testuser", "testpass");
		
		when(uDao.getUserByUsername(anyString())).thenReturn(u1);
		
		User loggedIn = uServ.signIn("testuser", "testpass");
		
		assertEquals(u1.getId(), loggedIn.getId());
	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void testInvalidUsername() {
		User not = new User(0, "test", "user", "test@mail.com", "testuser", "testpass");
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		User loggedIn = uServ.signIn("test", "testpass");
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testInvalidPassword() {
		User not = new User(1, "test", "user", "test@mail.com", "testuser", "wrongpass");
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		uServ.signIn("testuser", "testpass");
	}
	
}
