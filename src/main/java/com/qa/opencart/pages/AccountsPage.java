package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage
{
	WebDriver driver;
	
	ElementUtil elementUtil;
	
	private By logoutLink=By.linkText("Logout");
	private By headers=By.xpath("//div[@id='content']/h2");
	private By search=By.cssSelector("div#search>input");
	private By searchButton=By.xpath("//div[@id='search']//button");
	
	//Page Class constructor
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		
	}
	
	//Page Actions/Methods
	
	public String accountPageTitle()
	{
		String title=elementUtil.getTitleContains(AppConstants.LOGIN_PAGE_ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println("Account Page title is: "+title);
		return title;
	}
	
	public boolean accountPageLogoutLinkDisplay()
	{
		return elementUtil.isitDisplayed(logoutLink);
	}
	
	public int accountPageHeadersCount()
	{
		int totalHeaders= elementUtil.WaitForElementsVisisbility(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Total number of Headers in account page are: "+totalHeaders);
		return totalHeaders;
	}
	
	public List<String> accountPageHeadersList()
	{
		List<WebElement> headersList=elementUtil.WaitForElementsVisisbility(headers,AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersListOfString=new ArrayList<String>();
		for(WebElement e:headersList)
		{
			String text=e.getText();
			headersListOfString.add(text);
		}
		return headersListOfString;
	}
	
	public SearchResultsPage SearchFromAccountPage(String searchKey)
	{
		elementUtil.waitAndClearOldText(search,AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		elementUtil.dosendKeys(search, searchKey);
		elementUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
	
	
	
}
