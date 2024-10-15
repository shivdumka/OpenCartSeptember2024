package com.qa.opencart.tests;

import com.qa.opencart.constants.AppConstants;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;


public class LoginPageTest extends BaseTest
{
	@Test	
	public void loginPageTitleTest()
	{
		String actualTitle=loginPage.loginPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageURLTest()
	{
		String actualUrl=loginPage.loginPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTIONAL_URL));
	}
	
	@Test
	public void loginPageforgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.loginPageforgotPwdLink());
	} 
	
	@Test
	public void loginPageLogoTest()
	{
		Assert.assertTrue(loginPage.loginPageLogo());
	}
	
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest()
	{
		//accPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		accPage=loginPage.login(prop.getProperty("username"), System.getProperty("password"));
		Assert.assertEquals(accPage.accountPageTitle(),AppConstants.LOGIN_PAGE_ACCOUNTS_PAGE_TITLE);
	}
	
}
