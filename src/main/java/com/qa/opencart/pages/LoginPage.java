package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage 
{
	private WebDriver driver;
	
	private ElementUtil elementUtil;
	
	//Private By locators
	
//	For git:
//cd "C:\SELNIUMJAVAECLIPSEWORKSPACE\FirstPOMSeriesSept2024"
	
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By logo=By.xpath("//div[@id='logo']");
	private By clickRegister=By.linkText("Register");
	
	//page class constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	//public page actions
	
	public String loginPageTitle()
	{
		String title=elementUtil.getTitleContains(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
		//String title=driver.getTitle();
		System.out.println("Login Page Title is: "+title);
		return title;
	}
	
	public String loginPageURL()
	{
		String url=elementUtil.getURLContains(AppConstants.LOGIN_PAGE_FRACTIONAL_URL,AppConstants.DEFAULT_SHORT_TIME_OUT);
		//String url=driver.getCurrentUrl();
		System.out.println("Login Page URL is: "+url);
		return url;
	}
	
	public boolean loginPageforgotPwdLink()
	{
		return elementUtil.isitDisplayed(forgotPwdLink);
		//return driver.findElement(forgotPwdLink).isDisplayed();
	} 
	
	public boolean loginPageLogo()
	{
		return elementUtil.isitDisplayed(logo);
	}
	
	public AccountsPage login(String usrname,String pswrd)
	{
		elementUtil.waitAndDoSendKeys(AppConstants.DEFAULT_SHORT_TIME_OUT,username,usrname);
		//driver.findElement(username).sendKeys(usrname);
		
		elementUtil.dosendKeys(password,pswrd);
		//driver.findElement(password).sendKeys(pswrd);
		
		elementUtil.doClick(loginBtn);
		//driver.findElement(loginBtn).click();
		
		
		//String accountPageTitle= elementUtil.getTitleContains(AppConstants.LOGIN_PAGE_ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_LONG_TIME_OUT);
		//--String accountPageTitle=driver.getTitle();
		
		//--System.out.println("Successfully Logged in and you landed in a new page whose title is: "+accountPageTitle);
		//return accountPageTitle;
		
		return new AccountsPage(driver);
	}
	
	public RegisterPage clickAndEnterRegisterPage()
	{
		elementUtil.doClick(clickRegister);
		return new RegisterPage(driver);
	}
	
	
	
}
