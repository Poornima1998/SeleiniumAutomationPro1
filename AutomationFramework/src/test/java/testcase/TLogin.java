package testcase;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.PLogin;
import utilities.ReadXLSdata;

public class TLogin extends BaseTest{
	
	@Test(dataProviderClass=ReadXLSdata.class,dataProvider="projectData")
	public void testLogin(String username, String password) throws InterruptedException {
		
		PLogin login = new PLogin();
		
		login.loginM(username, password);	
		
	}
	
	
}


