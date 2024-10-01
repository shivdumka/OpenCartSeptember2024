package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest
{
	@BeforeClass
	public void regPageInitialSetup()
	{
		regPage=loginPage.clickAndEnterRegisterPage();
	}
	
	public String emailGenerator()
	{
		return "tom"+System.currentTimeMillis()+"@gmail.com";
	}
	
//	@DataProvider(name="RegisterUser")
//	public Object[][] registerUser()
//	{
//		return new Object[][] 
//				{
//					{"tom","366",emailGenerator(),"234234234","12345678","Yes"},
//					{"tom","366",emailGenerator(),"234234234","12345678","No"},
//					{"tom","366",emailGenerator()+"m","234234234","12345678","No"}	
//				};
//	}
	
//	public void fillRegisterPageFormTest(String fname,String lname,String eID,String tphone,String pwd,String cfrmPwd)
//	{
//		String actualSuccessMesg=regPage.fillRegisterPageForm(fname, lname, eID, tphone, pwd, cfrmPwd);
//		Assert.assertEquals(actualSuccessMesg, AppConstants.USER_REGISTERED_SUCCESS_MESG);
//	}
	
//	@Test(dataProvider="RegisterUser")
//	public void logoutandNavigateToRegisterPageFromSuccessPageTest(String fname,String lname,String eID,String tphone,String pwd,String selectNewsLetter)
//	{
//		String actualSuccessMesg=regPage.logoutandNavigateToRegisterPageFromSuccessPage(fname, lname, eID, tphone, pwd, selectNewsLetter);
//		Assert.assertEquals(actualSuccessMesg, AppConstants.USER_REGISTERED_SUCCESS_MESG);
//	}
	
	@DataProvider(name="getRegData")
	public Object[][] getRegData()
	{
		return ExcelUtil.getTestData("register");
	}
	
	@Test(dataProvider="getRegData")
	public void logoutandNavigateToRegisterPageFromSuccessPageTest(String fname,String lname,String tphone,String pwd,String selectNewsLetter)
	{
		String actualSuccessMesg=regPage.logoutandNavigateToRegisterPageFromSuccessPage(fname, lname, emailGenerator(), tphone, pwd, selectNewsLetter);
		Assert.assertEquals(actualSuccessMesg, AppConstants.USER_REGISTERED_SUCCESS_MESG);
	}
}
