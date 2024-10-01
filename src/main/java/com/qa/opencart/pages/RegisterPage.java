package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage 
{
	WebDriver driver;
	ElementUtil elementUtil;
	
	//Private By locators
	By firstName=By.id("input-firstname");
	By lastname=By.id("input-lastname");
	By emailID=By.id("input-email");
	By telephone=By.id("input-telephone");
	By password=By.id("input-password");
	By confirmPassword=By.id("input-confirm");
	By subscribeYes=By.xpath("//label[normalize-space()='Yes']");
	By subscribeNo=By.xpath("//label[normalize-space()='No']");
	By agreeRadioBtn=By.name("agree");
	By submitBtn=By.xpath("//input[@type='submit']");
	By successMesg=By.xpath("//div[@id='content']/h1");
	By logout=By.linkText("Logout");
	By registerLink=By.linkText("Register");
	
	//Page Class Constructor
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		
	}
	
	
	//Public Page Action/Methods
	public void fillRegisterPageForm(String fname,String lname,String eID,String tphone,String pwd,String selectNewsLetter)
	{
		elementUtil.waitAndDoSendKeys(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName,fname);
		elementUtil.dosendKeys(lastname,lname);
		elementUtil.dosendKeys(emailID,eID);
		elementUtil.dosendKeys(telephone,tphone);
		elementUtil.dosendKeys(password,pwd);
		elementUtil.dosendKeys(confirmPassword,pwd);
		
		if(selectNewsLetter.equalsIgnoreCase("yes"))
		{
			elementUtil.doClick(subscribeYes);
		}
		else if ((selectNewsLetter.equalsIgnoreCase("no"))) 
		{
			elementUtil.doClick(subscribeNo);
		}
	
		elementUtil.doClick(agreeRadioBtn);
		elementUtil.doClick(submitBtn);
	}
	
	public String logoutandNavigateToRegisterPageFromSuccessPage(String fname,String lname,String eID,String tphone,String pwd,String selectNewsLetter)
	{
		fillRegisterPageForm(fname,lname,eID,tphone,pwd,selectNewsLetter);
		
		String mesg=elementUtil.printText(successMesg);
		
		System.out.println(mesg);
		elementUtil.doClick(logout);
		WebElement register=elementUtil.explicitWaitForVisiblityOfElement(AppConstants.DEFAULT_SHORT_TIME_OUT, registerLink);
		elementUtil.doClick(register);
		return mesg;
	}
	
	

}
