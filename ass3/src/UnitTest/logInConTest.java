
package UnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controllers.*;
import Model.*;
public class logInConTest {
	public logInCon loginC = null;
	public ArrayList<User> userTest =new ArrayList<User>();

	@Before
	public void setUp() throws Exception {
		loginC = new logInCon();
		userTest.add(new User("newUser1","33333","abc@def",1));
		userTest.add(new User("newUser2","22222","abc1@def",0));
		userTest.add(new User("newUser3","11111","abc2@def",0));
		loginC.setUser(userTest);
	}
	@Test
	public void testPass(){
		String cPass = "22222";
		String cUser = "newUser2";
		//check if the password correct
		assertTrue(loginC.checkPassword(cUser, cPass));
		//check if the password is't correct	
		cPass = "111a";
		assertFalse(loginC.checkPassword(cUser, cPass));
	}
	
	@Test
	public void testuserConnect(){
		String userName="newUser3";
		//check if the user is not connected
		assertFalse(loginC.userConnect(userName));
		userName = "newUser1";
		//check if the user is connected
		assertTrue(loginC.userConnect(userName));
		
	}
	
	public void testuserExist(){
		String userName="newUser3";
		//check if the user is exist
		assertTrue(loginC.userExist(userName));
		userName = "somebody";
		//check if the user is not exist
		assertFalse(loginC.userExist(userName));
	}
	
	public void testchangeStatus(){
		String userName="newUser1";
		//check if the status is changed
		assertTrue(loginC.changeStatus(userName)==(userTest.get(0).getStatus()));
		//check if the status is't changed
		assertFalse(loginC.changeStatus(userName)==(1-userTest.get(1).getStatus()));
		
	}
	

}
