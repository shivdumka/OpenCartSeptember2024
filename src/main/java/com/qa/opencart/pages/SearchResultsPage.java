package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage 
{
	private WebDriver driver;
	ElementUtil elementUtil;
	//private By locator
	private By searchResultsHeader=By.xpath("//div[@id='content']/h1");
	private By productList=By.xpath("//div[@class='product-thumb']");
	
	
	
	//Class constructor
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	
	
	//public Page Actions
	public String serachPageProductHeader()
	{
		String searchResultHeader=elementUtil.explicitWaitForVisiblityOfElement(AppConstants.DEFAULT_MEDIUM_TIME_OUT, searchResultsHeader).getText();
		System.out.println("Search Result Page header is: "+searchResultHeader);
		return searchResultHeader;
	}
	
	public int getProductSearchListCount()
	{
		int searchedProductListCount=elementUtil.WaitForElementsVisisbility(productList, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println(searchedProductListCount);
		return searchedProductListCount;
	}
	
	public ProductInfoPage selectProductFromList(String productName)
	{
		WebElement selectProduct=elementUtil.explicitWaitForVisiblityOfElement(AppConstants.DEFAULT_MEDIUM_TIME_OUT,By.linkText(productName));
		elementUtil.doClick(selectProduct);
		return new ProductInfoPage(driver);
	}
	
	
}
